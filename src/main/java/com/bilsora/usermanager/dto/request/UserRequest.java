package com.bilsora.usermanager.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {

  @NotBlank(message = "Username must not be blank")
  private String userName;
}
