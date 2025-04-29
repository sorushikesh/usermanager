package com.bilsora.usermanager.controller;

import com.bilsora.usermanager.constants.ExceptionErrorCode;
import com.bilsora.usermanager.constants.FieldConstant;
import com.bilsora.usermanager.dto.request.UserRequest;
import com.bilsora.usermanager.dto.response.UserResponse;
import com.bilsora.usermanager.exceptions.NotFoundException;
import com.bilsora.usermanager.service.UserService;
import com.bilsora.usermanager.util.UserManagerUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static com.bilsora.usermanager.constants.APIEndPoints.*;
import static com.bilsora.usermanager.constants.MessagesConstant.USER_NOT_FOUND;

@Slf4j
@RestController
@RequestMapping(API_USER_MANAGER + API_USER)
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class UserController {

  private final UserManagerUtil userManagerUtil;
  private final UserService userService;

  @PostMapping("/get-by-username")
  @ResponseStatus(HttpStatus.OK)
  public UserResponse getUserByUsername(@Valid @RequestBody UserRequest userRequest,
      @RequestHeader(value = "locale", required = false) String localeHeader) {

    Locale locale = userManagerUtil.resolveLocale(localeHeader);
    LocaleContextHolder.setLocale(locale);

    String userName = userRequest.getUsername();
    log.info("Fetching user details by username: {}", userName);

    var problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    return userService.fetchUserByUsername(userName)
        .map(user -> UserResponse.builder().users(user).build())
        .orElseThrow(() -> NotFoundException.of(USER_NOT_FOUND,
            ExceptionErrorCode.EXCEPTION_NOT_FOUND, new Object[] {FieldConstant.USER, userName}));
  }

  @DeleteMapping("/delete-by-username")
  @ResponseStatus(HttpStatus.OK)
  public UserResponse deleteUserByUsername(@Valid @RequestBody UserRequest userRequest,
      @RequestHeader(value = "locale", required = false) String localeHeader) {

    LocaleContextHolder.setLocale(userManagerUtil.resolveLocale(localeHeader));
    String userName = userRequest.getUsername();
    log.info("Deleting user details for username: {}", userName);

    return userService.deleteUserByUsername(userName).map(
        user -> UserResponse.builder().message("User deleted successfully.").users(user).build())
        .orElseThrow(() -> NotFoundException.of(USER_NOT_FOUND,
            ExceptionErrorCode.EXCEPTION_NOT_FOUND, new Object[] {FieldConstant.USER, userName}));
  }

  @PutMapping("/activate")
  @ResponseStatus(HttpStatus.OK)
  public UserResponse activateUser(@Valid @RequestBody UserRequest request,
      @RequestHeader(value = "locale", required = false) String localeHeader) {

    LocaleContextHolder.setLocale(userManagerUtil.resolveLocale(localeHeader));
    String username = request.getUsername();
    log.info("Activating user: {}", username);

    return userService.activateUser(username).map(
        user -> UserResponse.builder().users(user).message("User activated successfully").build())
        .orElseThrow(() -> NotFoundException.of(USER_NOT_FOUND,
            ExceptionErrorCode.EXCEPTION_NOT_FOUND, new Object[] {FieldConstant.USER, username}));
  }

}
