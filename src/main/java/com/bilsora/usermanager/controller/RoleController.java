package com.bilsora.usermanager.controller;

import static com.bilsora.usermanager.constants.APIEndPoints.API_ROLE;
import static com.bilsora.usermanager.constants.APIEndPoints.API_USER_MANAGER;

import com.bilsora.usermanager.dto.response.RoleResponse;
import com.bilsora.usermanager.service.RoleService;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(API_USER_MANAGER + API_ROLE)
@RequiredArgsConstructor
public class RoleController {

  private final RoleService roleService;

  @GetMapping("/{roleName}")
  @ResponseStatus(HttpStatus.OK)
  public RoleResponse getRoleByName(
      @PathVariable String roleName,
      @RequestHeader(value = "locale", required = false) String localeHeader) {

    Locale locale = resolveLocale(localeHeader);
    LocaleContextHolder.setLocale(locale);
    log.info("Fetching role by name: {}", roleName);

    return roleService.findByName(roleName);
  }

  private Locale resolveLocale(String localeHeader) {
    if (localeHeader == null || localeHeader.isBlank()) {
      return Locale.ENGLISH;
    }
    return Locale.forLanguageTag(localeHeader);
  }
}
