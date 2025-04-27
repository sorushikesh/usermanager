package com.bilsora.usermanager.service;

import com.bilsora.usermanager.dto.UserRegistrationRequest;

public interface RegistrationService {
  void registerUser(UserRegistrationRequest request);
}
