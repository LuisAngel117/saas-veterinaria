package com.saasveterinaria.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "app.security.jwt")
@Validated
public class JwtProperties {
  @NotBlank
  private String issuer;

  @NotNull
  private Duration accessTtl;

  @NotNull
  private Duration refreshTtl;

  @NotBlank
  private String secret;

  private boolean refreshRotate = true;

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public Duration getAccessTtl() {
    return accessTtl;
  }

  public void setAccessTtl(Duration accessTtl) {
    this.accessTtl = accessTtl;
  }

  public Duration getRefreshTtl() {
    return refreshTtl;
  }

  public void setRefreshTtl(Duration refreshTtl) {
    this.refreshTtl = refreshTtl;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public boolean isRefreshRotate() {
    return refreshRotate;
  }

  public void setRefreshRotate(boolean refreshRotate) {
    this.refreshRotate = refreshRotate;
  }
}
