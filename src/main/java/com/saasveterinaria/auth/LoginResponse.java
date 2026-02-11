package com.saasveterinaria.auth;

import java.util.List;

public class LoginResponse {
  private String accessToken;
  private String refreshToken;
  private String tokenType;
  private long expiresInSeconds;
  private String branchId;
  private UserInfo user;
  private List<BranchInfo> branches;

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public long getExpiresInSeconds() {
    return expiresInSeconds;
  }

  public void setExpiresInSeconds(long expiresInSeconds) {
    this.expiresInSeconds = expiresInSeconds;
  }

  public String getBranchId() {
    return branchId;
  }

  public void setBranchId(String branchId) {
    this.branchId = branchId;
  }

  public UserInfo getUser() {
    return user;
  }

  public void setUser(UserInfo user) {
    this.user = user;
  }

  public List<BranchInfo> getBranches() {
    return branches;
  }

  public void setBranches(List<BranchInfo> branches) {
    this.branches = branches;
  }

  public record UserInfo(String id, String email, List<String> roleCodes) {}
  public record BranchInfo(String id, String name) {}
}
