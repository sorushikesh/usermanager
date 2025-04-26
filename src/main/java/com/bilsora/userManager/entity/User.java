package com.bilsora.userManager.entity;

import com.bilsora.userManager.constants.DataBaseConstant;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = DataBaseConstant.TABLE_USER)
public class User {

  @Id
  @Column(name = DataBaseConstant.COLUMN_USER_ID)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = DataBaseConstant.COLUMN_FIRST_NAME)
  private String firstName;

  @Column(name = DataBaseConstant.COLUMN_LAST_NAME)
  private String lastName;

  @Column(name = DataBaseConstant.COLUMN_EMAIL, unique = true)
  private String email;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(
      name = DataBaseConstant.COLUMN_ROLE_ID,
      referencedColumnName = DataBaseConstant.COLUMN_ROLE_ID)
  private Role role;
}
