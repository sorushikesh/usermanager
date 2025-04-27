package com.bilsora.usermanager.security;

import com.bilsora.usermanager.model.User;
import com.bilsora.usermanager.repository.UserRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

@Configuration
@RequiredArgsConstructor
public class TokenCustomizer {

  private final UserRepository userRepository;

  @Bean
  public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
    return context -> {
      if (context.getTokenType().getValue().equals("access_token")) {
        var principal = context.getPrincipal();
        var authorities =
            principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        String username = principal.getName();
        User user =
            userRepository
                .findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found for token customization"));

        context.getClaims().claim("authorities", authorities);
        context.getClaims().claim("tenantId", user.getTenantId());
        context.getClaims().claim("organizationId", user.getOrganizationId());
        context.getClaims().claim("subscriptionPlan", user.getSubscriptionPlan());
        context.getClaims().claim("email", user.getUsername() + "@bilsora.com"); // Example
      }
    };
  }
}
