package com.bilsora.usermanager.service.serviceImpl;

import com.bilsora.usermanager.dto.RoleCreationRequest;
import com.bilsora.usermanager.model.Role;
import com.bilsora.usermanager.repository.RoleRepository;
import com.bilsora.usermanager.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  public void createRole(RoleCreationRequest request) {
    if (roleRepository.findByName(request.getRoleName()).isPresent()) {
      throw new RuntimeException("Role already exists: " + request.getRoleName());
    }

    Role role = new Role(request.getRoleName());

    roleRepository.save(role);
  }
}
