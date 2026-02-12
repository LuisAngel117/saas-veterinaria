package com.saasveterinaria.audit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saasveterinaria.auth.PermissionCodes;
import com.saasveterinaria.common.ApiException;
import com.saasveterinaria.common.ErrorCodes;
import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
  private static final int REASON_MIN_LENGTH = 10;
  private static final int REASON_MAX_LENGTH = 500;
  private static final Set<String> REASON_REQUIRED_CODES = Set.of(
      PermissionCodes.AGENDA_OVERRIDE_OVERLAP,
      PermissionCodes.ENCOUNTER_REOPEN,
      PermissionCodes.ENCOUNTER_EDIT_CLOSED,
      PermissionCodes.INVENTORY_ADJUST,
      PermissionCodes.INVENTORY_OVERRIDE_NEGATIVE,
      PermissionCodes.INVOICE_PRICE_OVERRIDE,
      PermissionCodes.INVOICE_ANNUL,
      PermissionCodes.SETTINGS_TAX_UPDATE,
      PermissionCodes.FEATURE_FLAG_UPDATE,
      PermissionCodes.AUTH_2FA_RESET
  );

  private final AuditEventRepository repository;
  private final ObjectMapper objectMapper;

  public AuditService(AuditEventRepository repository, ObjectMapper objectMapper) {
    this.repository = repository;
    this.objectMapper = objectMapper;
  }

  public void requireReason(String actionCode, String reason) {
    if (!REASON_REQUIRED_CODES.contains(actionCode)) {
      return;
    }
    String normalized = normalizeReason(reason);
    if (normalized == null
        || normalized.length() < REASON_MIN_LENGTH
        || normalized.length() > REASON_MAX_LENGTH) {
      throw new ApiException(HttpStatus.BAD_REQUEST, ErrorCodes.REASON_REQUIRED,
          "Reason required for action " + actionCode);
    }
  }

  public AuditEvent record(UUID actorUserId,
                           String actorRole,
                           UUID branchId,
                           String actionCode,
                           String entityType,
                           UUID entityId,
                           String reason,
                           Object before,
                           Object after,
                           String ipAddress,
                           String userAgent) {
    requireReason(actionCode, reason);
    AuditEvent event = new AuditEvent();
    event.setId(UUID.randomUUID());
    event.setOccurredAt(Instant.now());
    event.setActorUserId(actorUserId);
    event.setActorRole(actorRole);
    event.setBranchId(branchId);
    event.setActionCode(actionCode);
    event.setEntityType(entityType);
    event.setEntityId(entityId);
    event.setReason(normalizeReason(reason));
    event.setBeforeJson(toJson(before));
    event.setAfterJson(toJson(after));
    event.setIpAddress(ipAddress);
    event.setUserAgent(userAgent);
    return repository.save(event);
  }

  private String toJson(Object value) {
    if (value == null) {
      return null;
    }
    if (value instanceof String str) {
      return str;
    }
    if (value instanceof Map) {
      return writeJson(value);
    }
    return writeJson(value);
  }

  private String writeJson(Object value) {
    try {
      return objectMapper.writeValueAsString(value);
    } catch (Exception ex) {
      return String.valueOf(value);
    }
  }

  private String normalizeReason(String reason) {
    if (reason == null) {
      return null;
    }
    String trimmed = reason.trim();
    return trimmed.isEmpty() ? null : trimmed;
  }
}
