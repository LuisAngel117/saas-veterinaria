# Dominio — Parte A (modelo de datos: identidad + CRM + agenda)

## 1) Entidades (tabla de campos mínimos)

### branch
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| name | text | requerido, único global |
| is_active | boolean | default true |
| created_at | timestamp | requerido |

### user
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| email | text | requerido, único |
| display_name | text | requerido |
| password_hash | text | requerido |
| is_active | boolean | default true |
| requires_2fa | boolean | default false |
| created_at | timestamp | requerido |

### role
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| code | text | requerido, único (SUPERADMIN/ADMIN/RECEPCION/VETERINARIO) |
| name | text | requerido |

### permission
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| code | text | requerido, único (ej: AGENDA_CREATE) |
| name | text | requerido |
| module | text | requerido |

### user_role (M:N)
| Campo | Tipo | Reglas |
|---|---|---|
| user_id | UUID | FK user |
| role_id | UUID | FK role |
| unique(user_id, role_id) | | |

### room (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| name | text | requerido, unique(branch_id, name) |
| is_active | boolean | default true |

### veterinarian_profile (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| user_id | UUID | requerido (user) |
| license_number | text | opcional |
| is_active | boolean | default true |
| unique(branch_id, user_id) | | |

### client (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| full_name | text | requerido |
| identification | text | opcional |
| phone | text | requerido |
| email | text | opcional |
| address | text | opcional |
| notes | text | opcional |
| tags | text[]/json | opcional |
| created_at | timestamp | requerido |

### client_consent (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| client_id | UUID | requerido |
| consent_data_processing | boolean | requerido |
| consent_reminders | boolean | requerido |
| consent_text_version | text | requerido |
| recorded_by_user_id | UUID | requerido |
| recorded_at | timestamp | requerido |
| privacy_note | text | opcional |

### pet (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| client_id | UUID | requerido |
| internal_code | text | requerido, unique(branch_id, internal_code) |
| name | text | requerido |
| species | text | requerido |
| breed | text | opcional |
| sex | text | requerido (MALE/FEMALE/UNKNOWN) |
| birth_date | date | opcional |
| age_text | text | opcional (si no hay birth_date) |
| weight_kg | numeric | opcional |
| neutered | boolean | opcional |
| alerts | text | opcional (alergias/alertas) |
| history_notes | text | opcional (antecedentes) |
| created_at | timestamp | requerido |

### appointment (cita) (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| room_id | UUID | requerido |
| vet_user_id | UUID | requerido (user) |
| client_id | UUID | requerido |
| pet_id | UUID | requerido |
| status | text | requerido (RESERVED/CONFIRMED/CHECKED_IN/IN_SERVICE/CLOSED/CANCELLED) |
| start_at | timestamp | requerido |
| end_at | timestamp | requerido |
| buffer_minutes | int | default 10 |
| override_overlap | boolean | default false |
| override_reason | text | requerido si override_overlap=true |
| cancel_reason | text | requerido si status=CANCELLED |
| notes | text | opcional |
| created_at | timestamp | requerido |

Notas:
- `status` en DB puede usar códigos internos; UI mostrará español.
- Se recomienda index: (branch_id, room_id, start_at), (branch_id, vet_user_id, start_at).

## 2) Relaciones
- user M:N role vía user_role.
- branch 1:N rooms, clients, pets, appointments.
- client 1:N pets.
- appointment pertenece a: branch, room, vet_user_id(user), client, pet.

## 3) Reglas críticas (invariantes)
- Scoping: toda entidad branch-scoped tiene branch_id y se valida contra JWT.
- No-solape (sala+veterinario):
  - para una cita nueva/modificada, no debe existir otra cita activa que overlap en:
    - mismo room_id, o
    - mismo vet_user_id
  - excepto si `override_overlap=true` con permiso + reason.

- Intervalos:
  - start_at < end_at
  - duración base 30 min (v1) y end_at = start_at + 30m (+ buffer no altera end_at; buffer afecta disponibilidad).

## 4) Seeds demo (mínimo)
- 2 sucursales: “Sucursal Centro”, “Sucursal Norte”
- 2 salas por sucursal: “Consulta 1”, “Consulta 2”
- Usuarios demo (ver BRD-REQ-067) y asignación de roles
- 2 clientes por sucursal + 2 mascotas cada uno
- 3 citas precreadas para probar calendario (sin overlap)

<!-- EOF -->
