package com.saasveterinaria.auth;

import com.saasveterinaria.security.JwtPrincipal;
import com.saasveterinaria.security.JwtProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/2fa")
public class TwoFactorController {
  private final TwoFactorService twoFactorService;
  private final AuthService authService;
  private final JwtProperties jwtProperties;

  public TwoFactorController(TwoFactorService twoFactorService, AuthService authService, JwtProperties jwtProperties) {
    this.twoFactorService = twoFactorService;
    this.authService = authService;
    this.jwtProperties = jwtProperties;
  }

  @PostMapping("/enroll")
  public TwoFactorEnrollResponse enroll(@AuthenticationPrincipal JwtPrincipal principal) {
    TwoFactorService.EnrollResponse response = twoFactorService.enroll(principal.userId(), jwtProperties.getIssuer());
    TwoFactorEnrollResponse dto = new TwoFactorEnrollResponse();
    dto.setSecret(response.secret());
    dto.setOtpauthUrl(response.otpauthUrl());
    return dto;
  }

  @PostMapping("/confirm")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void confirm(@AuthenticationPrincipal JwtPrincipal principal,
                      @Valid @RequestBody TwoFactorConfirmRequest request) {
    twoFactorService.confirm(principal.userId(), request.getCode());
  }

  @PostMapping("/verify")
  public LoginResponse verify(@Valid @RequestBody TwoFactorVerifyRequest request) {
    TwoFactorService.VerifiedChallenge verified =
        twoFactorService.verifyChallenge(request.getChallengeId(), request.getCode());
    return authService.issueTokens(verified.user(), verified.branchId());
  }

  @PostMapping("/reset")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void reset(@AuthenticationPrincipal JwtPrincipal principal,
                    @Valid @RequestBody TwoFactorResetRequest request,
                    HttpServletRequest httpServletRequest) {
    String ipAddress = httpServletRequest.getRemoteAddr();
    String userAgent = httpServletRequest.getHeader("User-Agent");
    twoFactorService.reset(request.getUserId(), request.getReason(), principal, ipAddress, userAgent);
  }
}
