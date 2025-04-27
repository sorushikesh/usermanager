package com.bilsora.usermanager.controller;

import com.bilsora.usermanager.dto.UserRegistrationRequest;
import com.bilsora.usermanager.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class UserRegistrationController {

  private final RegistrationService registrationService;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request) {
    registrationService.registerUser(request);
    return ResponseEntity.ok("User registered successfully!");
  }
}
