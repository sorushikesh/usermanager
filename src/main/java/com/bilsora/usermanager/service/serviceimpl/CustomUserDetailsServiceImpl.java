package com.bilsora.usermanager.service.serviceimpl;

import com.bilsora.usermanager.constants.ExceptionErrorCode;
import com.bilsora.usermanager.constants.FieldConstant;
import com.bilsora.usermanager.exceptions.NotFoundException;
import com.bilsora.usermanager.model.Users;
import com.bilsora.usermanager.repository.UserRepository;
import com.bilsora.usermanager.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    log.info("Authenticating user: {}", username);
    Users appUser = userRepository.findByUsername(username)
        .orElseThrow(() -> NotFoundException.of("User not found",
            ExceptionErrorCode.EXCEPTION_NOT_FOUND, new Object[] {FieldConstant.USER, username}));

    return User.builder().username(appUser.getUsername()).password(appUser.getPassword())
        .disabled(!appUser.isActive())
        .authorities(new SimpleGrantedAuthority(appUser.getRole().getName())).build();
  }
}
