package com.bilsora.usermanager.security;

import com.bilsora.usermanager.model.Permission;
import com.bilsora.usermanager.model.Role;
import com.bilsora.usermanager.model.Users;
import com.bilsora.usermanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class TokenCustomizer {

  private final UserRepository userRepository;

  @Bean
  public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
    return context -> {
      if (context.getTokenType().getValue().equals("access_token")) {
        var principal = context.getPrincipal();
        String username = principal.getName();
        Users users = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

        Set<String> roles = users.getRoles().stream()
            .map(Role::getName)
            .collect(Collectors.toSet());

        Set<String> permissions = users.getRoles().stream()
            .flatMap(role -> role.getPermissions().stream())
            .map(Permission::getName)
            .collect(Collectors.toSet());

        context.getClaims().claim("tenantId", users.getTenantId());
        context.getClaims().claim("organizationId", users.getOrganizationId());
        context.getClaims().claim("roles", roles);
        context.getClaims().claim("permissions", permissions);
        context.getClaims().claim("email", users.getUsername() + "@gmail.com");
      }
    };
  }
}
