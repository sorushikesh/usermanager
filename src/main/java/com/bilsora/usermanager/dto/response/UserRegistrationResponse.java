package com.bilsora.usermanager.dto.response;

import com.bilsora.usermanager.model.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationResponse {

  private String message;
  private Users users;
}
