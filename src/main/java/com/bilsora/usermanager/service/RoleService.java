package com.bilsora.usermanager.service;

import com.bilsora.usermanager.dto.response.RoleResponse;
import com.bilsora.usermanager.model.Role;
import java.util.List;
import java.util.Optional;

/** Service interface for managing roles. */
public interface RoleService {

  /**
   * Find a role by its name.
   *
   * @param roleName the name of the role
   * @return the RoleResponse
   */
  Optional<Role> findByName(String roleName);

  /**
   * Get all available roles.
   *
   * @return list of RoleResponse
   */
  List<RoleResponse> getAllRoles();
}
