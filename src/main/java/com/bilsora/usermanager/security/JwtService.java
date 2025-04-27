package com.bilsora.usermanager.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  public String generateAccessToken(UserDetails userDetails, String tenantId) {
    String authorities =
        userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

    return Jwts.builder()
        .subject(userDetails.getUsername())
        .claim("authorities", authorities)
        .claim("tenantId", tenantId)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
        .signWith(key)
        .compact();
  }

  public Key getKey() {
    return key;
  }
}
