package com.example.projectbase.service;

import com.example.projectbase.domain.dto.common.UserDetailImp;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.UserCreateDTO;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.domain.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface UserService {

  UserDto getUserById(String userId);

  PaginationResponseDto<UserDto> getCustomers(PaginationFullRequestDto request);

  UserEntity getCurrentUser();

  ResponseEntity<?> forgotPassWord(String userName);

  ResponseEntity<?> createNewUser(UserCreateDTO userDTO, BindingResult bindingResult);

  ResponseEntity<?> updateUser( UserCreateDTO userDTO, BindingResult bindingResult);


    void deleteUser(String id);
}
