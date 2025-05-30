package com.bilsora.usermanager.service.serviceimpl;

import com.bilsora.usermanager.model.Users;
import com.bilsora.usermanager.repository.UserRepository;
import com.bilsora.usermanager.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public Optional<Users> fetchUserByUsername(String username) {

    log.info("Fetching user details for username {}", username);
    return userRepository.findByUsername(username);
  }

  @Override
  @Transactional
  public Optional<Users> deleteUserByUsername(String username) {
    log.info("Deleting user details for username {}", username);
    Optional<Users> userOpt = userRepository.findByUsername(username);
    userOpt.ifPresent(userRepository::delete);
    return userOpt;
  }

  @Override
  @Transactional
  public Optional<Users> activateUser(String username) {
    log.info("Activating user for username {}", username);
    Optional<Users> userOpt = userRepository.findByUsername(username);
    userOpt.ifPresent(user -> {
      log.info("User details found for username {}", username);
      user.setActive(true);
      userRepository.save(user);
      log.info("User {} activated successfully", username);
    });
    return userOpt;
  }


}
