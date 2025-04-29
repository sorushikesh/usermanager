package com.bilsora.usermanager.service;

import com.bilsora.usermanager.dto.request.UserRegistrationRequest;
import com.bilsora.usermanager.model.Users;

/** Service interface for registering users. */
public interface RegistrationService {

  /**
   * Register user by details
   *
   * @param request UserRegistrationRequest details
   */
  Users registerUser(UserRegistrationRequest request);
}
