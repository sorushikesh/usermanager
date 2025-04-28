package com.bilsora.usermanager.controller;

import static com.bilsora.usermanager.constants.APIEndPoints.API_ROLE;
import static com.bilsora.usermanager.constants.APIEndPoints.API_USER_MANAGER;

import com.bilsora.usermanager.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(API_USER_MANAGER + API_ROLE)
@RequiredArgsConstructor
public class RoleController {

  private final RoleService roleService;
}
