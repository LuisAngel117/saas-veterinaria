CREATE TABLE branch (
  id UUID PRIMARY KEY,
  name VARCHAR(100) NOT NULL UNIQUE,
  created_at TIMESTAMP NOT NULL
);

CREATE TABLE role (
  id UUID PRIMARY KEY,
  code VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_account (
  id UUID PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  is_active BOOLEAN NOT NULL,
  created_at TIMESTAMP NOT NULL
);

CREATE TABLE user_role (
  user_id UUID NOT NULL,
  role_id UUID NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES user_account(id),
  CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE user_branch (
  user_id UUID NOT NULL,
  branch_id UUID NOT NULL,
  PRIMARY KEY (user_id, branch_id),
  CONSTRAINT fk_user_branch_user FOREIGN KEY (user_id) REFERENCES user_account(id),
  CONSTRAINT fk_user_branch_branch FOREIGN KEY (branch_id) REFERENCES branch(id)
);

CREATE TABLE refresh_token (
  id UUID PRIMARY KEY,
  user_id UUID NOT NULL,
  branch_id UUID NOT NULL,
  token_hash VARCHAR(255) NOT NULL UNIQUE,
  expires_at TIMESTAMP NOT NULL,
  revoked_at TIMESTAMP NULL,
  rotated_from_token_id UUID NULL,
  created_at TIMESTAMP NOT NULL,
  CONSTRAINT fk_refresh_user FOREIGN KEY (user_id) REFERENCES user_account(id),
  CONSTRAINT fk_refresh_branch FOREIGN KEY (branch_id) REFERENCES branch(id),
  CONSTRAINT fk_refresh_rotated FOREIGN KEY (rotated_from_token_id) REFERENCES refresh_token(id)
);
