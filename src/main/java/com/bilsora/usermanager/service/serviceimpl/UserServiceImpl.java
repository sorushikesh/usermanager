package com.bilsora.usermanager.service.serviceimpl;

import com.bilsora.usermanager.model.Users;
import com.bilsora.usermanager.repository.UserRepository;
import com.bilsora.usermanager.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
