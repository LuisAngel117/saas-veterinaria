package com.saasveterinaria.common;

public final class ErrorCodes {
  public static final String AUTH_INVALID_CREDENTIALS = "AUTH_INVALID_CREDENTIALS";
  public static final String AUTH_REFRESH_INVALID = "AUTH_REFRESH_INVALID";
  public static final String AUTH_INVALID_TOKEN = "AUTH_INVALID_TOKEN";
  public static final String AUTH_2FA_REQUIRED = "AUTH_2FA_REQUIRED";
  public static final String AUTH_2FA_INVALID_CODE = "AUTH_2FA_INVALID_CODE";
  public static final String AUTH_2FA_ALREADY_ENABLED = "AUTH_2FA_ALREADY_ENABLED";
  public static final String AUTH_2FA_NOT_ENROLLED = "AUTH_2FA_NOT_ENROLLED";
  public static final String AUTH_2FA_CHALLENGE_INVALID = "AUTH_2FA_CHALLENGE_INVALID";
  public static final String AUTH_2FA_NOT_ENABLED = "AUTH_2FA_NOT_ENABLED";
  public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
  public static final String BRANCH_REQUIRED = "BRANCH_REQUIRED";
  public static final String BRANCH_FORBIDDEN = "BRANCH_FORBIDDEN";
  public static final String BRANCH_HEADER_MISSING = "BRANCH_HEADER_MISSING";
  public static final String BRANCH_HEADER_INVALID = "BRANCH_HEADER_INVALID";
  public static final String BRANCH_SCOPE_MISMATCH = "BRANCH_SCOPE_MISMATCH";

  private ErrorCodes() {}
}
