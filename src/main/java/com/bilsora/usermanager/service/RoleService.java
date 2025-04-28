package com.bilsora.usermanager.service;

import com.bilsora.usermanager.dto.response.RoleResponse;

import java.util.List;

/**
 * Service interface for managing roles.
 */
public interface RoleService {

  /**
   * Find a role by its name.
   *
   * @param roleName the name of the role
   * @return the RoleResponse
   */
  RoleResponse findByName(String roleName);

  /**
   * Get all available roles.
   *
   * @return list of RoleResponse
   */
  List<RoleResponse> getAllRoles();
}
