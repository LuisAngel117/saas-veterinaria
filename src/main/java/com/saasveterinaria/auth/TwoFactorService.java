package com.saasveterinaria.auth;

import com.saasveterinaria.audit.AuditService;
import com.saasveterinaria.branch.Branch;
import com.saasveterinaria.branch.BranchRepository;
import com.saasveterinaria.common.ApiException;
import com.saasveterinaria.common.ErrorCodes;
import com.saasveterinaria.security.JwtPrincipal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TwoFactorService {
  private static final int CHALLENGE_TTL_MINUTES = 5;
  private final UserAccountRepository userRepository;
  private final TwoFactorChallengeRepository challengeRepository;
  private final BranchRepository branchRepository;
  private final TotpService totpService;
  private final AuditService auditService;

  public TwoFactorService(UserAccountRepository userRepository,
                          TwoFactorChallengeRepository challengeRepository,
                          BranchRepository branchRepository,
                          TotpService totpService,
                          AuditService auditService) {
    this.userRepository = userRepository;
    this.challengeRepository = challengeRepository;
    this.branchRepository = branchRepository;
    this.totpService = totpService;
    this.auditService = auditService;
  }

  @Transactional
  public EnrollResponse enroll(UUID userId, String issuer) {
    UserAccount user = userRepository.findById(userId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorCodes.USER_NOT_FOUND, "User not found"));
    if (user.isTotpEnabled()) {
      throw new ApiException(HttpStatus.CONFLICT, ErrorCodes.AUTH_2FA_ALREADY_ENABLED, "2FA already enabled");
    }
    String secret = totpService.generateSecret();
    user.setTotpSecret(secret);
    user.setTotpEnabled(false);
    user.setTotpVerifiedAt(null);
    userRepository.save(user);

    String otpauthUrl = totpService.buildOtpAuthUrl(issuer, user.getEmail(), secret);
    return new EnrollResponse(secret, otpauthUrl);
  }

  @Transactional
  public void confirm(UUID userId, String code) {
    UserAccount user = userRepository.findById(userId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorCodes.USER_NOT_FOUND, "User not found"));
    if (user.getTotpSecret() == null || user.getTotpSecret().isBlank()) {
      throw new ApiException(HttpStatus.CONFLICT, ErrorCodes.AUTH_2FA_NOT_ENROLLED, "2FA not enrolled");
    }
    if (!totpService.verifyCode(user.getTotpSecret(), code, Instant.now())) {
      throw new ApiException(HttpStatus.BAD_REQUEST, ErrorCodes.AUTH_2FA_INVALID_CODE, "Invalid 2FA code");
    }
    user.setTotpEnabled(true);
    user.setTotpVerifiedAt(Instant.now());
    userRepository.save(user);
  }

  @Transactional
  public TwoFactorChallenge createChallenge(UserAccount user, UUID branchId) {
    Branch branch = branchRepository.findById(branchId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorCodes.BRANCH_FORBIDDEN, "Branch not found"));
    TwoFactorChallenge challenge = new TwoFactorChallenge();
    challenge.setId(UUID.randomUUID());
    challenge.setUser(user);
    challenge.setBranch(branch);
    challenge.setCreatedAt(Instant.now());
    challenge.setExpiresAt(Instant.now().plus(CHALLENGE_TTL_MINUTES, ChronoUnit.MINUTES));
    return challengeRepository.save(challenge);
  }

  @Transactional
  public VerifiedChallenge verifyChallenge(String challengeIdRaw, String code) {
    UUID challengeId;
    try {
      challengeId = UUID.fromString(challengeIdRaw);
    } catch (IllegalArgumentException ex) {
      throw new ApiException(HttpStatus.BAD_REQUEST, ErrorCodes.AUTH_2FA_CHALLENGE_INVALID, "Invalid challenge");
    }

    TwoFactorChallenge challenge = challengeRepository.findById(challengeId)
        .orElseThrow(() -> new ApiException(HttpStatus.CONFLICT, ErrorCodes.AUTH_2FA_CHALLENGE_INVALID,
            "Challenge not found or expired"));

    if (challenge.getUsedAt() != null || challenge.getExpiresAt().isBefore(Instant.now())) {
      throw new ApiException(HttpStatus.CONFLICT, ErrorCodes.AUTH_2FA_CHALLENGE_INVALID,
          "Challenge not found or expired");
    }

    UserAccount user = userRepository.findWithRolesById(challenge.getUser().getId())
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorCodes.USER_NOT_FOUND, "User not found"));

    if (!user.isTotpEnabled() || user.getTotpSecret() == null) {
      throw new ApiException(HttpStatus.CONFLICT, ErrorCodes.AUTH_2FA_NOT_ENABLED, "2FA not enabled");
    }

    if (!totpService.verifyCode(user.getTotpSecret(), code, Instant.now())) {
      throw new ApiException(HttpStatus.BAD_REQUEST, ErrorCodes.AUTH_2FA_INVALID_CODE, "Invalid 2FA code");
    }

    challenge.setUsedAt(Instant.now());
    challengeRepository.save(challenge);

    return new VerifiedChallenge(user, challenge.getBranch().getId());
  }

  @Transactional
  public void reset(UUID targetUserId, String reason, JwtPrincipal actor, String ipAddress, String userAgent) {
    UserAccount target = userRepository.findById(targetUserId)
        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorCodes.USER_NOT_FOUND, "User not found"));

    if (!target.isTotpEnabled()) {
      throw new ApiException(HttpStatus.CONFLICT, ErrorCodes.AUTH_2FA_NOT_ENABLED, "2FA not enabled");
    }

    String actorRole = String.join(",", actor.roles());
    target.setTotpEnabled(false);
    target.setTotpSecret(null);
    target.setTotpVerifiedAt(null);
    userRepository.save(target);
    auditService.record(
        actor.userId(),
        actorRole,
        actor.branchId(),
        PermissionCodes.AUTH_2FA_RESET,
        "user_account",
        target.getId(),
        reason,
        java.util.Map.of("totp_enabled", true),
        java.util.Map.of("totp_enabled", false),
        ipAddress,
        userAgent);
  }

  public record EnrollResponse(String secret, String otpauthUrl) {}

  public record VerifiedChallenge(UserAccount user, UUID branchId) {}
}
