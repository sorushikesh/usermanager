package com.bilsora.usermanager.service.serviceImpl;

import com.bilsora.usermanager.dto.UserRegistrationRequest;
import com.bilsora.usermanager.model.Role;
import com.bilsora.usermanager.model.User;
import com.bilsora.usermanager.repository.RoleRepository;
import com.bilsora.usermanager.repository.UserRepository;
import com.bilsora.usermanager.service.RegistrationService;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void registerUser(UserRegistrationRequest request) {
    if (userRepository.findByUsername(request.getUsername()).isPresent()) {
      throw new RuntimeException("Username already exists!");
    }

    User user =
        User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .tenantId(request.getTenantId())
            .enabled(Boolean.TRUE)
            .build();

    Set<Role> roles = new HashSet<>();
    for (String roleName : request.getRoles()) {
      Role role =
          roleRepository
              .findByName(roleName)
              .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
      roles.add(role);
    }

    user.setRoles(roles);
    userRepository.save(user);
  }
}
