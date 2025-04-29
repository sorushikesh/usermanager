package com.bilsora.usermanager.controller;

import static com.bilsora.usermanager.constants.APIEndPoints.API_ROLE;
import static com.bilsora.usermanager.constants.APIEndPoints.API_USER_MANAGER;

import com.bilsora.usermanager.constants.ExceptionErrorCode;
import com.bilsora.usermanager.constants.FieldConstant;
import com.bilsora.usermanager.dto.request.RoleNameRequest;
import com.bilsora.usermanager.dto.response.RoleResponse;
import com.bilsora.usermanager.exceptions.NotFoundException;
import com.bilsora.usermanager.service.RoleService;
import com.bilsora.usermanager.util.UserManagerUtil;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(API_USER_MANAGER + API_ROLE)
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class RoleController {

  private final RoleService roleService;
  private final UserManagerUtil userManagerUtil;

  @PostMapping("/get-by-name")
  @ResponseStatus(HttpStatus.OK)
  public RoleResponse getRoleByName(@Valid @RequestBody RoleNameRequest roleNameRequest,
      @RequestHeader(value = "locale", required = false) String localeHeader) {

    Locale locale = userManagerUtil.resolveLocale(localeHeader);
    LocaleContextHolder.setLocale(locale);

    String roleName = roleNameRequest.getRoleName();
    log.info("Fetching role by name: {}", roleName);

    var problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    return roleService.findByName(roleName)
        .map(role -> new RoleResponse(role.getId(), role.getName()))
        .orElseThrow(() -> NotFoundException.of("Role not found",
            ExceptionErrorCode.EXCEPTION_NOT_FOUND, new Object[] {FieldConstant.ROLE, roleName}));
  }

  @GetMapping("/roles")
  @ResponseStatus(HttpStatus.OK)
  public List<RoleResponse> getAllRoles(
      @RequestHeader(value = "locale", required = false) String localeHeader) {

    Locale locale = userManagerUtil.resolveLocale(localeHeader);
    LocaleContextHolder.setLocale(locale);

    log.info("Fetching the list of role");
    return roleService.getAllRoles();
  }
}
