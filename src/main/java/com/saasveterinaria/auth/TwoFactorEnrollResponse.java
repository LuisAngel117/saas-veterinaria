package com.saasveterinaria.auth;

public class TwoFactorEnrollResponse {
  private String secret;
  private String otpauthUrl;

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getOtpauthUrl() {
    return otpauthUrl;
  }

  public void setOtpauthUrl(String otpauthUrl) {
    this.otpauthUrl = otpauthUrl;
  }
}
