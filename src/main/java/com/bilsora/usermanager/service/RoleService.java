package com.bilsora.usermanager.service;

import com.bilsora.usermanager.dto.RoleResponse;

public interface RoleService {

  RoleResponse findByName(String roleName);
}
