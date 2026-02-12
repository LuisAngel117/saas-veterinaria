package com.saasveterinaria.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  private final JwtProperties properties;
  private final SecretKey key;

  public JwtService(JwtProperties properties) {
    this.properties = properties;
    this.key = buildKey(properties.getSecret());
  }

  public String generateAccessToken(UUID userId, String email, List<String> roles, List<String> permissions,
                                    UUID branchId) {
    Instant now = Instant.now();
    Instant exp = now.plus(properties.getAccessTtl());
    return Jwts.builder()
        .setIssuer(properties.getIssuer())
        .setSubject(userId.toString())
        .setIssuedAt(Date.from(now))
        .setExpiration(Date.from(exp))
        .claim("email", email)
        .claim("roles", roles)
        .claim("permissions", permissions)
        .claim("branch_id", branchId.toString())
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public JwtPrincipal parseAccessToken(String token) {
    Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    Claims claims = jws.getBody();
    UUID userId = UUID.fromString(claims.getSubject());
    String email = claims.get("email", String.class);
    @SuppressWarnings("unchecked")
    List<String> roles = claims.get("roles", List.class);
    List<String> permissions = claims.get("permissions", List.class);
    if (roles == null) {
      roles = List.of();
    }
    if (permissions == null) {
      permissions = List.of();
    }
    UUID branchId = UUID.fromString(claims.get("branch_id", String.class));
    return new JwtPrincipal(userId, email, roles, permissions, branchId);
  }

  private static SecretKey buildKey(String secret) {
    byte[] raw;
    if (secret.startsWith("base64:")) {
      raw = Decoders.BASE64.decode(secret.substring("base64:".length()));
    } else {
      raw = secret.getBytes(StandardCharsets.UTF_8);
    }
    if (raw.length < 32) {
      throw new IllegalArgumentException("JWT secret must be at least 32 bytes");
    }
    return Keys.hmacShaKeyFor(raw);
  }
}
