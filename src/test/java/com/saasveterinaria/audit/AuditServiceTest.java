package com.saasveterinaria.audit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saasveterinaria.auth.PermissionCodes;
import com.saasveterinaria.common.ApiException;
import com.saasveterinaria.common.ErrorCodes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuditServiceTest {

  @Mock
  private AuditEventRepository repository;

  private AuditService auditService;

  @BeforeEach
  void setUp() {
    auditService = new AuditService(repository, new ObjectMapper());
  }

  @Test
  void requireReasonRejectsWhenMissing() {
    ApiException ex = assertThrows(ApiException.class,
        () -> auditService.requireReason(PermissionCodes.INVOICE_ANNUL, "short"));
    assertEquals(ErrorCodes.REASON_REQUIRED, ex.getCode());
  }

  @Test
  void requireReasonAllowsWhenNotRequired() {
    assertDoesNotThrow(() -> auditService.requireReason(PermissionCodes.AUTH_LOGIN, null));
  }
}
