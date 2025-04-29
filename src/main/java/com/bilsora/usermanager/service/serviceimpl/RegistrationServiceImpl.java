package com.bilsora.usermanager.service.serviceimpl;

import com.bilsora.usermanager.constants.ExceptionErrorCode;
import com.bilsora.usermanager.constants.FieldConstant;
import com.bilsora.usermanager.dto.request.UserRegistrationRequest;
import com.bilsora.usermanager.exceptions.AlreadyExistsException;
import com.bilsora.usermanager.exceptions.NotFoundException;
import com.bilsora.usermanager.model.Users;
import com.bilsora.usermanager.repository.UserRepository;
import com.bilsora.usermanager.service.RegistrationService;
import com.bilsora.usermanager.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class RegistrationServiceImpl implements RegistrationService {

  private final UserRepository userRepository;
  private final RoleService roleService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Users registerUser(UserRegistrationRequest request) {
    log.info(
        "Registering user for username {} and role {}.", request.getUsername(), request.getRole());

    log.info("Checking if user already exists.");
    if (userRepository.existsById(request.getUsername())) {
      throw AlreadyExistsException.of(
          "User already exists",
          ExceptionErrorCode.EXCEPTION_ALREADY_EXISTS,
          new Object[] {FieldConstant.USERNAME, request.getUsername()});
    }

    log.info("Fetching the role for {}", request.getRole());
    var role =
        roleService
            .findByName(request.getRole())
            .orElseThrow(
                () ->
                    NotFoundException.of(
                        "Role not found",
                        ExceptionErrorCode.EXCEPTION_NOT_FOUND,
                        new Object[] {FieldConstant.ROLE, request.getRole()}));

    log.info("Encoding the password");
    String encodedPassword = passwordEncoder.encode(request.getPassword());

    log.info("Saving the user details");
    var user = new Users();
    user.setUsername(request.getUsername());
    user.setPassword(encodedPassword);
    user.setEmail(request.getEmail());
    user.setActive(true);
    user.setRole(role);

    Users savedUser = userRepository.save(user);

    log.info("User '{}' registered successfully.", savedUser.getUsername());

    return savedUser;
  }
}
