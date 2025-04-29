package com.bilsora.usermanager.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleNameRequest {

  @NotBlank(message = "Role name must not be blank")
  private String roleName;
}
