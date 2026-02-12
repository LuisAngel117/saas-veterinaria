package com.saasveterinaria.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class TwoFactorVerifyRequest {
  @NotBlank
  private String challengeId;

  @NotBlank
  @Pattern(regexp = "\\d{6}")
  private String code;

  public String getChallengeId() {
    return challengeId;
  }

  public void setChallengeId(String challengeId) {
    this.challengeId = challengeId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
