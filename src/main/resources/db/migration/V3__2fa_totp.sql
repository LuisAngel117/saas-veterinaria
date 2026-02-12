ALTER TABLE user_account ADD COLUMN totp_enabled BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE user_account ADD COLUMN totp_secret VARCHAR(128);
ALTER TABLE user_account ADD COLUMN totp_verified_at TIMESTAMP NULL;

CREATE TABLE auth_2fa_challenge (
  id UUID PRIMARY KEY,
  user_id UUID NOT NULL,
  branch_id UUID NOT NULL,
  expires_at TIMESTAMP NOT NULL,
  used_at TIMESTAMP NULL,
  created_at TIMESTAMP NOT NULL,
  CONSTRAINT fk_2fa_user FOREIGN KEY (user_id) REFERENCES user_account(id),
  CONSTRAINT fk_2fa_branch FOREIGN KEY (branch_id) REFERENCES branch(id)
);

CREATE INDEX idx_auth_2fa_user ON auth_2fa_challenge(user_id);
CREATE INDEX idx_auth_2fa_branch ON auth_2fa_challenge(branch_id);
