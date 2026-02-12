CREATE TABLE audit_event (
  id UUID PRIMARY KEY,
  occurred_at TIMESTAMP NOT NULL,
  actor_user_id UUID NOT NULL,
  actor_role VARCHAR(100) NOT NULL,
  branch_id UUID NULL,
  action_code VARCHAR(100) NOT NULL,
  entity_type VARCHAR(100) NOT NULL,
  entity_id UUID NULL,
  reason TEXT NULL,
  before_json TEXT NULL,
  after_json TEXT NULL,
  ip_address VARCHAR(100) NULL,
  user_agent VARCHAR(255) NULL
);

CREATE INDEX idx_audit_event_actor ON audit_event(actor_user_id);
CREATE INDEX idx_audit_event_action ON audit_event(action_code);
