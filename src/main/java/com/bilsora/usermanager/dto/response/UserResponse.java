package com.bilsora.usermanager.dto.response;

import com.bilsora.usermanager.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

  private String username;
  private String password;
  private boolean active;
  private Role role;
}
