package com.saasveterinaria.auth;

import com.saasveterinaria.common.ApiException;
import com.saasveterinaria.common.ErrorCodes;
import com.saasveterinaria.security.JwtProperties;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {
  private final RefreshTokenRepository repository;
  private final JwtProperties jwtProperties;

  public RefreshTokenService(RefreshTokenRepository repository, JwtProperties jwtProperties) {
    this.repository = repository;
    this.jwtProperties = jwtProperties;
  }

  public IssuedToken issueToken(UserAccount user, UUID branchId) {
    String plain = generateToken();
    RefreshToken token = new RefreshToken();
    token.setId(UUID.randomUUID());
    token.setUser(user);
    token.setBranch(user.getBranches().stream()
        .filter(b -> b.getId().equals(branchId))
        .findFirst()
        .orElseThrow());
    token.setTokenHash(hash(plain));
    token.setCreatedAt(Instant.now());
    token.setExpiresAt(Instant.now().plus(jwtProperties.getRefreshTtl()));
    repository.save(token);
    return new IssuedToken(token, plain);
  }

  public IssuedToken rotateToken(String plainToken) {
    RefreshToken existing = repository.findByTokenHash(hash(plainToken))
        .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, ErrorCodes.AUTH_REFRESH_INVALID,
            "Invalid refresh token"));

    if (existing.getRevokedAt() != null || existing.getExpiresAt().isBefore(Instant.now())) {
      throw new ApiException(HttpStatus.UNAUTHORIZED, ErrorCodes.AUTH_REFRESH_INVALID, "Invalid refresh token");
    }

    String newPlain = generateToken();
    RefreshToken rotated = new RefreshToken();
    rotated.setId(UUID.randomUUID());
    rotated.setUser(existing.getUser());
    rotated.setBranch(existing.getBranch());
    rotated.setTokenHash(hash(newPlain));
    rotated.setCreatedAt(Instant.now());
    rotated.setExpiresAt(Instant.now().plus(jwtProperties.getRefreshTtl()));
    rotated.setRotatedFrom(existing);

    existing.setRevokedAt(Instant.now());
    repository.save(existing);
    repository.save(rotated);

    return new IssuedToken(rotated, newPlain);
  }

  public void revokeToken(String plainToken) {
    repository.findByTokenHash(hash(plainToken))
        .ifPresent(token -> {
          if (token.getRevokedAt() == null) {
            token.setRevokedAt(Instant.now());
            repository.save(token);
          }
        });
  }

  private String generateToken() {
    byte[] bytes = new byte[32];
    java.security.SecureRandom random = new java.security.SecureRandom();
    random.nextBytes(bytes);
    return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
  }

  private String hash(String token) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hashed = digest.digest(token.getBytes(StandardCharsets.UTF_8));
      return Base64.getUrlEncoder().withoutPadding().encodeToString(hashed);
    } catch (NoSuchAlgorithmException ex) {
      throw new IllegalStateException("SHA-256 not available", ex);
    }
  }

  public record IssuedToken(RefreshToken token, String plainToken) {}
}
