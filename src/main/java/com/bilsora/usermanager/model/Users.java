package com.bilsora.usermanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users {

  @Id
  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  private boolean active = true;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "role_id", referencedColumnName = "id")
  private Role role;
}
