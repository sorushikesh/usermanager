package com.bilsora.usermanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
