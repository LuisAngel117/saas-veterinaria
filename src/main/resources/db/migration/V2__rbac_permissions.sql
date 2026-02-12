CREATE TABLE permission (
  id UUID PRIMARY KEY,
  code VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE role_permission (
  role_id UUID NOT NULL,
  permission_id UUID NOT NULL,
  PRIMARY KEY (role_id, permission_id),
  CONSTRAINT fk_role_permission_role FOREIGN KEY (role_id) REFERENCES role(id),
  CONSTRAINT fk_role_permission_permission FOREIGN KEY (permission_id) REFERENCES permission(id)
);
