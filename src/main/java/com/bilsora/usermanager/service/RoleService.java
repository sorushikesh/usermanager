package com.bilsora.usermanager.service;

import com.bilsora.usermanager.dto.response.RoleResponse;
import com.bilsora.usermanager.exceptions.NotFoundException;

public interface RoleService {

  RoleResponse findByName(String roleName) throws NotFoundException;
}
