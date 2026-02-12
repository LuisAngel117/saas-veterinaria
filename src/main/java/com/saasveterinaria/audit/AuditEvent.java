package com.saasveterinaria.audit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "audit_event")
public class AuditEvent {
  @Id
  private UUID id;

  @Column(name = "occurred_at", nullable = false)
  private Instant occurredAt;

  @Column(name = "actor_user_id", nullable = false)
  private UUID actorUserId;

  @Column(name = "actor_role", nullable = false)
  private String actorRole;

  @Column(name = "branch_id")
  private UUID branchId;

  @Column(name = "action_code", nullable = false)
  private String actionCode;

  @Column(name = "entity_type", nullable = false)
  private String entityType;

  @Column(name = "entity_id")
  private UUID entityId;

  @Column(name = "reason")
  private String reason;

  @Column(name = "before_json")
  private String beforeJson;

  @Column(name = "after_json")
  private String afterJson;

  @Column(name = "ip_address")
  private String ipAddress;

  @Column(name = "user_agent")
  private String userAgent;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Instant getOccurredAt() {
    return occurredAt;
  }

  public void setOccurredAt(Instant occurredAt) {
    this.occurredAt = occurredAt;
  }

  public UUID getActorUserId() {
    return actorUserId;
  }

  public void setActorUserId(UUID actorUserId) {
    this.actorUserId = actorUserId;
  }

  public String getActorRole() {
    return actorRole;
  }

  public void setActorRole(String actorRole) {
    this.actorRole = actorRole;
  }

  public UUID getBranchId() {
    return branchId;
  }

  public void setBranchId(UUID branchId) {
    this.branchId = branchId;
  }

  public String getActionCode() {
    return actionCode;
  }

  public void setActionCode(String actionCode) {
    this.actionCode = actionCode;
  }

  public String getEntityType() {
    return entityType;
  }

  public void setEntityType(String entityType) {
    this.entityType = entityType;
  }

  public UUID getEntityId() {
    return entityId;
  }

  public void setEntityId(UUID entityId) {
    this.entityId = entityId;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getBeforeJson() {
    return beforeJson;
  }

  public void setBeforeJson(String beforeJson) {
    this.beforeJson = beforeJson;
  }

  public String getAfterJson() {
    return afterJson;
  }

  public void setAfterJson(String afterJson) {
    this.afterJson = afterJson;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }
}
