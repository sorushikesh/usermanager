package com.bilsora.usermanager.service;

import com.bilsora.usermanager.model.Users;

import java.util.Optional;

/** Service interface for user. */
public interface UserService {

  /**
   * Find user by username
   *
   * @param username String username
   * @return User
   */
  Optional<Users> fetchUserByUsername(String username);

  /**
   * Delete user by username
   *
   * @param username String username
   * @return User
   */
  Optional<Users> deleteUserByUsername(String username);

  /**
   * Activate user
   *
   * @param username String username
   * @return User
   */
  Optional<Users> activateUser(String username);

}
