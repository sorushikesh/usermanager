package com.bilsora.userManager.entity;

import com.bilsora.userManager.constants.DataBaseConstant;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = DataBaseConstant.TABLE_ROLES)
public class Role {

  @Id
  @Column(name = DataBaseConstant.COLUMN_ROLE_ID)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "role_name")
  private String roleName;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"))
  @Column(name = "permission")
  private List<String> permissions;
}
