package com.bilsora.usermanager.controller;

import com.bilsora.usermanager.dto.request.UserRegistrationRequest;
import com.bilsora.usermanager.dto.response.UserRegistrationResponse;
import com.bilsora.usermanager.model.Users;
import com.bilsora.usermanager.service.RegistrationService;
import com.bilsora.usermanager.util.UserManagerUtil;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.bilsora.usermanager.constants.APIEndPoints.*;

@Slf4j
@RestController
@RequestMapping(API_USER_MANAGER + API_REGISTER)
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class RegistrationController {

  private final RegistrationService registrationService;
  private final UserManagerUtil userManagerUtil;

  @PostMapping(API_USER + "/{tenantId}")
  @ResponseStatus(HttpStatus.CREATED)
  public UserRegistrationResponse registerUser(
      @RequestHeader(value = "locale", required = false) String localeHeader,
      @PathVariable String tenantId, @RequestBody UserRegistrationRequest registrationRequest) {

    Locale locale = userManagerUtil.resolveLocale(localeHeader);
    LocaleContextHolder.setLocale(locale);

    log.info("Register the user for username {}", registrationRequest.getUsername());

    Users user = registrationService.registerUser(registrationRequest);

    return UserRegistrationResponse.builder().message("User registered successfully").users(user)
        .build();
  }
}
