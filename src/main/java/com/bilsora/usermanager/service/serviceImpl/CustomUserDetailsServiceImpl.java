package com.bilsora.usermanager.service.serviceImpl;

import com.bilsora.usermanager.model.Users;
import com.bilsora.usermanager.repository.UserRepository;
import com.bilsora.usermanager.service.CustomUserDetailsService;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Users appUsers =
        userRepository
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

    Set<SimpleGrantedAuthority> authorities =
        appUsers.getRoles().stream()
            .flatMap(
                role ->
                    Stream.concat(
                        Stream.of(new SimpleGrantedAuthority(role.getName())),
                        role.getPermissions().stream()
                            .map(p -> new SimpleGrantedAuthority(p.getName()))))
            .collect(Collectors.toSet());

    return org.springframework.security.core.userdetails.User.builder()
        .username(appUsers.getUsername())
        .password(appUsers.getPassword())
        .authorities(authorities)
        .disabled(!appUsers.isEnabled())
        .build();
  }
}
