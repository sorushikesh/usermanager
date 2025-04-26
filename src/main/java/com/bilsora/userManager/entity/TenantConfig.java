package com.bilsora.userManager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TenantConfig {
  @Id private String tenantId;
  private String url;
  private String username;
  private String password;
}
