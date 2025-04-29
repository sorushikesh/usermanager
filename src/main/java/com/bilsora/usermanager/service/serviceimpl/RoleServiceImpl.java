package com.bilsora.usermanager.service.serviceimpl;

import com.bilsora.usermanager.dto.response.RoleResponse;
import com.bilsora.usermanager.exceptions.NotFoundException;
import com.bilsora.usermanager.model.Role;
import com.bilsora.usermanager.repository.RoleRepository;
import com.bilsora.usermanager.service.RoleService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  public Optional<Role> findByName(String roleName) throws NotFoundException {

    log.info("Searching for role {}", roleName);
    return roleRepository.findByName(roleName);
  }

  @Override
  public List<RoleResponse> getAllRoles() {
    return roleRepository.findAll().stream()
        .map(role -> new RoleResponse(role.getId(), role.getName()))
        .toList();
  }
}
