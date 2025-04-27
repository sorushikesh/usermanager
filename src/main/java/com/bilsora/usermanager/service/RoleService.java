package com.bilsora.usermanager.service;

import com.bilsora.usermanager.dto.RoleCreationRequest;

public interface RoleService {

  void createRole(RoleCreationRequest request);
}
