package com.saasveterinaria.audit;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditRetentionService {
  private static final int RETENTION_DAYS = 90;
  private final AuditEventRepository repository;

  public AuditRetentionService(AuditEventRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public long purgeDemoRetention() {
    Instant cutoff = Instant.now().minus(RETENTION_DAYS, ChronoUnit.DAYS);
    return repository.deleteByOccurredAtBefore(cutoff);
  }
}
