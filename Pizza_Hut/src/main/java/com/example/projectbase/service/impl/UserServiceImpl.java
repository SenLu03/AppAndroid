package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.SortByDataConstant;
import com.example.projectbase.converter.UserConverter;
import com.example.projectbase.domain.dto.common.UserDetailImp;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.UserCreateDTO;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.domain.entity.CartEntity;
import com.example.projectbase.domain.entity.UserEntity;
import com.example.projectbase.domain.mapper.UserMapper;
import com.example.projectbase.exception.UnauthorizedException;
import com.example.projectbase.repository.CartRepository;
import com.example.projectbase.sendMessage.email.MailService;
import com.example.projectbase.exception.AlreadyExistsException;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.service.UserService;
import com.example.projectbase.util.BindingResultUtils;
import com.example.projectbase.util.PaginationUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UserServiceImpl implements UserService {

  private ExecutorService executorService = Executors.newFixedThreadPool(5);

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  private final MailService mailService;

  private final PasswordEncoder passwordEncoder;
  @Autowired
  private  CartRepository cartRepository;

  @Autowired
  private UserConverter userConverter;

  @Autowired
  private CustomUserDetailsServiceImpl userDetailServiceImp;

  @Value("${spring.mail.username}")
  private String gmail;


  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, MailService mailService, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.mailService = mailService;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDto getUserById(String userId) {
    UserEntity userEntity = userRepository.findById(userId)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{userId}));
    return userMapper.toUserDto(userEntity);
  }

  @Override
  public PaginationResponseDto<UserDto> getCustomers(PaginationFullRequestDto request) {
    //Pagination
    Pageable pageable = PaginationUtil.buildPageable(request, SortByDataConstant.USER);
    //Create Output
    return new PaginationResponseDto<>(null, null);
  }

  @Override
  public UserEntity getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetailImp userDetailImp = new UserDetailImp();
    if(authentication.getPrincipal()=="anonymousUser"){
      throw new AlreadyExistsException("Need to login first!");
    }
    else if (authentication != null && authentication.isAuthenticated()) {
      userDetailImp = (UserDetailImp) authentication.getPrincipal();
    } // Đoạn này có thể dùng hàm get current user
    UserEntity userEntity=userRepository.findByUsername(userDetailImp.getUsername()).get();
    return userEntity;
  }


//  @Override
//  public ResponseEntity<?> forgotPassWord(String userName) {
//    Optional<UserEntity> user = userRepository.findByUsername(userName);
//    if (!user.isPresent()) {
//      throw new UsernameNotFoundException(String.format("User with username : %s not found ", userName));
//    }
//    UserEntity userEntity = user.get();
//    String passWord = RandomStringUtils.randomAlphanumeric(5);
//    mailService.sendMail(gmail, "Mật khẩu mới của bạn là: " + passWord);
//    userEntity.setPassword(passwordEncoder.encode(passWord));
//    return ResponseEntity.ok(userConverter.converEntityToDTO(userRepository.save(userEntity)));
//  }

  @Override
  public ResponseEntity<?> forgotPassWord(String userName) {
    Optional<UserEntity> user = userRepository.findByUsername(userName);
    if (!user.isPresent()) {
      throw new AlreadyExistsException(String.format("User with username : %s not found ", userName));
    }
    UserEntity userEntity = user.get();
    String passWord = RandomStringUtils.randomAlphanumeric(5);

    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
      mailService.sendPassword(userEntity.getEmail(), String.format(ErrorMessage.User.INF_NEW_PASSWORD, passWord));
    }, executorService);

    // Thực hiện các tác vụ khác

    userEntity.setPassword(passwordEncoder.encode(passWord));
    return ResponseEntity.ok(userConverter.converEntityToDTO(userRepository.save(userEntity)));
  }//    // Thực hiện việc gửi mail bất đồng bộ trong một task riêng biệt

  @Override
  public ResponseEntity<?> createNewUser(@Valid UserCreateDTO userDTO,
                                         BindingResult bindingResult) {
    BindingResultUtils.bindResult(bindingResult);
    Optional<UserEntity> userUserName = userRepository.findByUsername(userDTO.getUsername());
    if(userUserName.isPresent() ){
      throw new AlreadyExistsException("User already exists with username " + userDTO.getUsername());
    }
    UserEntity userEntity = userRepository.findByEmail(userDTO.getEmail());
    if(userEntity != null) {
      throw new AlreadyExistsException("User already exists with username " + userDTO.getEmail());
    }
    userEntity = userRepository.findByPhone(userDTO.getPhoneNumber());
    if(userEntity != null){
      throw new AlreadyExistsException("User already exists with phone " + userDTO.getPhoneNumber());
    }
    UserEntity userEntitySave = userConverter.converDTOToEntity(userDTO);
    CartEntity cartEntity=new CartEntity();
    cartRepository.save(cartEntity);
    userEntitySave.setCartEntity(cartEntity);
    return ResponseEntity.ok(userConverter.converEntityToDTO(userRepository.save(userEntitySave)));
  }

  @Override
  public ResponseEntity<?> updateUser(@Valid UserCreateDTO userDTO, BindingResult bindingResult) {
    BindingResultUtils.bindResult(bindingResult);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetailImp userDetailImp = new UserDetailImp();
    if (authentication != null && authentication.isAuthenticated()) {
        userDetailImp = (UserDetailImp) authentication.getPrincipal();
    } // Đoạn này có thể dùng hàm get current user
      // Từ dto -> entity
      UserEntity existUserEntity = userConverter.converDTOToEntity(userDTO);
      existUserEntity.setId(userDetailImp.getId());
      existUserEntity.setUsername(userDetailImp.getUsername());
      UserEntity userEntity = userRepository.save(existUserEntity);
      return  ResponseEntity.ok(userConverter.converEntityToDTO(userEntity));
  }

  @Override
  public void deleteUser(String id) {
      userRepository.deleteById(id);

  }
}
