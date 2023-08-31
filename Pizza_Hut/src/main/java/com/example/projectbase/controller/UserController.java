package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.common.UserDetailImp;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.request.UserCreateDTO;
import com.example.projectbase.security.CurrentUser;
import com.example.projectbase.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestApiV1
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Tag(name = "user-controller-admin")
  @Operation(summary = "API get user")
  @GetMapping(UrlConstant.User.GET_USER)
  public ResponseEntity<?> getUserById(@PathVariable String userId) {
    return VsResponseUtil.success(userService.getUserById(userId));
  }

  @Tags({@Tag(name = "user-controller-admin"), @Tag(name = "user-controller")})
  @Operation(summary = "API get current user login")
  @GetMapping(UrlConstant.User.GET_CURRENT_USER)
  public ResponseEntity<?> getCurrentUser() {
    return VsResponseUtil.success(userService.getCurrentUser());
  }

  @Tag(name = "user-controller-admin")
  @Operation(summary = "API get all customer")
  @GetMapping(UrlConstant.User.GET_USERS)
  public ResponseEntity<?> getCustomers(@Valid @ParameterObject PaginationFullRequestDto requestDTO) {
    return VsResponseUtil.success(userService.getCustomers(requestDTO));
  }

  @PostMapping("/forgot-password")
  public ResponseEntity<?> forgotPassWord(@RequestParam String userName){
    return VsResponseUtil.success(userService.forgotPassWord(userName));
  }

  @PostMapping("/new-user")
  public ResponseEntity<?> createUser (@Valid @RequestBody UserCreateDTO userDTO, BindingResult bindingResult) {
    return VsResponseUtil.success(userService.createNewUser(userDTO, bindingResult));
  }

  @PutMapping
  public ResponseEntity<?> updateUser (@Valid @ModelAttribute UserCreateDTO userDTO, BindingResult bindingResult) {
    return VsResponseUtil.success(userService.updateUser(userDTO, bindingResult));
  }

  @DeleteMapping
  public ResponseEntity<?> deleteUser(@RequestParam String id){
      userService.deleteUser(id);
      return VsResponseUtil.success("Xóa thành công!");
  }

}
