package com.bilsora.usermanager.controller;

import com.bilsora.usermanager.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usermanager/role")
@RequiredArgsConstructor
public class RoleController {

  private final RoleService roleService;

}
