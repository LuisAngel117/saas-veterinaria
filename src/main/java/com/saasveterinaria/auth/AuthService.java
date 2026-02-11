package com.saasveterinaria.auth;

import com.saasveterinaria.branch.Branch;
import com.saasveterinaria.common.ApiException;
import com.saasveterinaria.common.ErrorCodes;
import com.saasveterinaria.security.JwtProperties;
import com.saasveterinaria.security.JwtService;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final UserAccountRepository userRepository;
  private final RefreshTokenService refreshTokenService;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final JwtProperties jwtProperties;

  public AuthService(UserAccountRepository userRepository,
                     RefreshTokenService refreshTokenService,
                     PasswordEncoder passwordEncoder,
                     JwtService jwtService,
                     JwtProperties jwtProperties) {
    this.userRepository = userRepository;
    this.refreshTokenService = refreshTokenService;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.jwtProperties = jwtProperties;
  }

  public LoginResponse login(LoginRequest request) {
    UserAccount user = userRepository.findByEmailIgnoreCase(request.getEmail())
        .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED,
            ErrorCodes.AUTH_INVALID_CREDENTIALS, "Invalid credentials"));

    if (!user.isActive() || !passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
      throw new ApiException(HttpStatus.UNAUTHORIZED, ErrorCodes.AUTH_INVALID_CREDENTIALS, "Invalid credentials");
    }

    List<Branch> branches = user.getBranches().stream().toList();
    if (branches.isEmpty()) {
      throw new ApiException(HttpStatus.FORBIDDEN, ErrorCodes.BRANCH_FORBIDDEN, "No branch assigned");
    }

    UUID selectedBranch = resolveBranch(request.getBranchId(), branches);

    List<String> roleCodes = user.getRoles().stream().map(Role::getCode).toList();
    String accessToken = jwtService.generateAccessToken(user.getId(), user.getEmail(), roleCodes, selectedBranch);

    RefreshTokenService.IssuedToken issued = refreshTokenService.issueToken(user, selectedBranch);

    LoginResponse response = new LoginResponse();
    response.setAccessToken(accessToken);
    response.setRefreshToken(issued.plainToken());
    response.setTokenType("Bearer");
    response.setExpiresInSeconds(jwtProperties.getAccessTtl().getSeconds());
    response.setBranchId(selectedBranch.toString());
    response.setUser(new LoginResponse.UserInfo(user.getId().toString(), user.getEmail(), roleCodes));
    return response;
  }

  public LoginResponse refresh(RefreshRequest request) {
    RefreshTokenService.IssuedToken rotated = refreshTokenService.rotateToken(request.getRefreshToken());
    UserAccount user = userRepository.findWithRolesById(rotated.token().getUser().getId())
        .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED,
            ErrorCodes.AUTH_REFRESH_INVALID, "Invalid refresh token"));
    UUID branchId = rotated.token().getBranch().getId();

    List<String> roleCodes = user.getRoles().stream().map(Role::getCode).toList();
    String accessToken = jwtService.generateAccessToken(user.getId(), user.getEmail(), roleCodes, branchId);

    LoginResponse response = new LoginResponse();
    response.setAccessToken(accessToken);
    response.setRefreshToken(rotated.plainToken());
    response.setTokenType("Bearer");
    response.setExpiresInSeconds(jwtProperties.getAccessTtl().getSeconds());
    response.setBranchId(branchId.toString());
    response.setUser(new LoginResponse.UserInfo(user.getId().toString(), user.getEmail(), roleCodes));
    return response;
  }

  public void logout(LogoutRequest request) {
    refreshTokenService.revokeToken(request.getRefreshToken());
  }

  private UUID resolveBranch(String branchIdRaw, List<Branch> branches) {
    if (branchIdRaw == null || branchIdRaw.isBlank()) {
      if (branches.size() == 1) {
        return branches.get(0).getId();
      }
      ApiException ex = new ApiException(HttpStatus.CONFLICT, ErrorCodes.BRANCH_REQUIRED,
          "Branch selection required");
      ex.withProperty("branches", branches.stream()
          .map(b -> new LoginResponse.BranchInfo(b.getId().toString(), b.getName()))
          .toList());
      throw ex;
    }

    UUID branchId;
    try {
      branchId = UUID.fromString(branchIdRaw);
    } catch (IllegalArgumentException ex) {
      throw new ApiException(HttpStatus.BAD_REQUEST, ErrorCodes.BRANCH_HEADER_INVALID, "Invalid branchId");
    }

    boolean allowed = branches.stream().anyMatch(b -> b.getId().equals(branchId));
    if (!allowed) {
      throw new ApiException(HttpStatus.FORBIDDEN, ErrorCodes.BRANCH_FORBIDDEN, "Branch not allowed");
    }

    return branchId;
  }
}
