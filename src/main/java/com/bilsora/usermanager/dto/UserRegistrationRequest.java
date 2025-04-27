package com.bilsora.usermanager.dto;

import java.util.Set;
import lombok.Data;

@Data
public class UserRegistrationRequest {
  private String username;
  private String password;
  private String tenantId;
  private Set<String> roles;
}
