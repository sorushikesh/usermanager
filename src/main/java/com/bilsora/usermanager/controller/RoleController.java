package com.bilsora.usermanager.controller;

import com.bilsora.usermanager.dto.RoleCreationRequest;
import com.bilsora.usermanager.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class RoleController {

  private final RoleService roleService;

  @PostMapping("/create-role")
  public ResponseEntity<String> createRole(@RequestBody RoleCreationRequest request) {
    roleService.createRole(request);
    return ResponseEntity.ok("Role created successfully!");
  }
}
