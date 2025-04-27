package com.bilsora.usermanager.model;

import jakarta.persistence.*;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "app_user")
public class User {

  @Id
  private String username;

  private String password;

  private String tenantId;

  private String organizationId;
  private String subscriptionPlan;

  private boolean enabled = true;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_roles",
      joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles;

}
