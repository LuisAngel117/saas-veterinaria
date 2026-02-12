package com.saasveterinaria.audit;

import java.time.Instant;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditEventRepository extends JpaRepository<AuditEvent, UUID> {
  long deleteByOccurredAtBefore(Instant cutoff);
}
