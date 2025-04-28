package com.bilsora.usermanager.controller;

import static com.bilsora.usermanager.constants.APIEndPoints.API_ROLE;
import static com.bilsora.usermanager.constants.APIEndPoints.API_USER_MANAGER;

import com.bilsora.usermanager.dto.RoleResponse;
import com.bilsora.usermanager.exceptions.NotFoundException;
import com.bilsora.usermanager.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(API_USER_MANAGER + API_ROLE)
@RequiredArgsConstructor
public class RoleController {

  private final RoleService roleService;

  @GetMapping("/{roleName}")
  public RoleResponse getRoleByName(@PathVariable String roleName) throws NotFoundException {
    log.info("Fetching role by name: {}", roleName);

    return roleService.findByName(roleName);
  }

}
