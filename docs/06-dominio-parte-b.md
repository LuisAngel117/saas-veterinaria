# Dominio — Parte B (clínica + catálogo + inventario + facturación + auditoría)

## 1) Estados y transiciones (alto nivel)

### appointment.status
- RESERVED → CONFIRMED → IN_ATTENTION → CLOSED
- CANCELLED puede ocurrir desde RESERVED/CONFIRMED (segun politica)
- Reglas:
  - check-in requiere recepcion y registra `check_in_at` + `checked_in_by_user_id`
  - check-in NO cambia `appointment.status`
  - IN_ATTENTION inicia veterinario y representa inicio de atencion
  - CLOSED cierra flujo clinico

### encounter.status (atención)
- DRAFT → OPEN → CLOSED
- CLOSED bloquea edición salvo reapertura (permiso).

### invoice.status
- PENDING → PAID
- ANNULLED desde PENDING/PAID (según regla; v1 permitir con reason)

## 2) Entidades clínicas y administrativas (tabla mínima)

### encounter (atención) (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| appointment_id | UUID | opcional |
| client_id | UUID | requerido |
| pet_id | UUID | requerido |
| vet_user_id | UUID | requerido |
| status | text | DRAFT/OPEN/CLOSED |
| opened_at | timestamp | opcional |
| closed_at | timestamp | opcional |
| created_at | timestamp | requerido |

### soap_note (1:1 con encounter en v1) (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| encounter_id | UUID | requerido, unique |
| s_reason | text | requerido |
| s_anamnesis | text | requerido |
| o_weight_kg | numeric | opcional |
| o_temperature_c | numeric | opcional |
| o_findings | text | requerido |
| o_vitals_extra | text | opcional |
| a_primary_dx | text | requerido |
| a_secondary_dx | text | opcional |
| p_treatment_plan | text | requerido |
| p_indications | text | requerido |
| p_followup_date | date | opcional |
| p_notes | text | opcional |
| is_closed_snapshot | boolean | default false |

### soap_template (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| service_id | UUID | opcional (por servicio) |
| name | text | requerido |
| template_json | json | requerido (estructura editable) |
| is_active | boolean | default true |

### attachment (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| encounter_id | UUID | requerido |
| file_name | text | requerido |
| mime_type | text | requerido |
| size_bytes | bigint | <= 10MB |
| storage_path | text | requerido |
| uploaded_by_user_id | UUID | requerido |
| uploaded_at | timestamp | requerido |

### service (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| name | text | requerido, unique(branch_id, name) |
| base_price | numeric | requerido |
| suggested_duration_minutes | int | opcional |
| is_active | boolean | default true |

### service_bom_item (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| service_id | UUID | requerido |
| product_id | UUID | requerido |
| quantity | numeric | requerido (>0) |

### product (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| name | text | requerido |
| sku | text | opcional |
| unit_id | UUID | requerido |
| min_stock | numeric | default 0 |
| avg_cost | numeric | default 0 |
| is_active | boolean | default true |

### unit (catálogo global o por branch; v1: global)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| code | text | requerido, único (UNIT/ML/TABLET) |
| name | text | requerido |

### stock_balance (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| product_id | UUID | requerido, unique(branch_id, product_id) |
| qty_on_hand | numeric | requerido |
| updated_at | timestamp | requerido |

### stock_movement (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| product_id | UUID | requerido |
| movement_type | text | IN/OUT/ADJUST/CONSUME |
| quantity | numeric | requerido |
| unit_cost | numeric | opcional (para IN/ADJUST) |
| reason | text | requerido en ADJUST/override |
| reference_type | text | opcional (ENCOUNTER/INVOICE) |
| reference_id | UUID | opcional |
| created_by_user_id | UUID | requerido |
| created_at | timestamp | requerido |

### invoice (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| encounter_id | UUID | requerido (1:1 en v1) |
| status | text | PENDING/PAID/ANNULLED |
| subtotal | numeric | requerido |
| discount_total | numeric | requerido |
| tax_rate | numeric | requerido |
| tax_total | numeric | requerido |
| total | numeric | requerido |
| created_at | timestamp | requerido |
| annulled_reason | text | requerido si ANNULLED |

### invoice_item (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| invoice_id | UUID | requerido |
| item_type | text | SERVICE/PRODUCT |
| service_id | UUID | opcional |
| product_id | UUID | opcional |
| description | text | requerido |
| quantity | numeric | requerido |
| unit_price | numeric | requerido |
| discount | numeric | requerido |
| line_total | numeric | requerido |
| price_override | boolean | default false |
| price_override_reason | text | requerido si price_override=true |

### payment (branch-scoped)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| branch_id | UUID | requerido |
| invoice_id | UUID | requerido |
| method | text | CASH/CARD/TRANSFER/OTHER |
| amount | numeric | requerido |
| paid_at | timestamp | requerido |
| reference | text | opcional |

### settings_global (global)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK (single row) |
| default_tax_rate | numeric | default 0.15 |
| updated_by_user_id | UUID | requerido |
| updated_at | timestamp | requerido |
| update_reason | text | requerido |

### feature_flag (global)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| key | text | requerido, único |
| enabled | boolean | requerido |
| description | text | opcional |

### audit_event (global o branch-scoped; v1: global con branch_id opcional)
| Campo | Tipo | Reglas |
|---|---|---|
| id | UUID | PK |
| occurred_at | timestamp | requerido |
| actor_user_id | UUID | requerido |
| actor_role | text | requerido |
| branch_id | UUID | opcional |
| action_code | text | requerido |
| entity_type | text | requerido |
| entity_id | UUID | opcional |
| reason | text | opcional/requerido según acción |
| before_json | json | opcional |
| after_json | json | opcional |
| ip_address | text | opcional |
| user_agent | text | opcional |

## 3) Invariantes y constraints
- encounter:
  - si appointment_id existe, debe pertenecer a mismo branch y mismo client/pet (validación negocio).
- invoice:
  - 1 factura por encounter (v1), unique(encounter_id).
- stock:
  - qty_on_hand no puede ser negativa salvo override explícito (permiso+reason+audit).
- price override:
  - si unit_price difiere del base_price del servicio o lista → requiere reason y audit.

## 4) Eventos auditables (mínimo)
- auth: login/logout/refresh/lockout
- agenda: create/update/confirm/cancel/check-in/start-attention/close-attention/override solape
- clinic: create/update/close/reopen
- billing: create/update/pay/annul/price override/IVA change
- inventory: IN/OUT/ADJUST/CONSUME/override stock

## 5) Seeds demo (mínimo)
- Unidades: UNIT, ML, TABLET
- Productos: “Amoxicilina 500mg”, “Jeringa”, “Suero”
- Servicios: “Consulta general”, “Vacunación”
- BOM: Vacunación consume “Jeringa” y “Suero” (ejemplo)
- 1 encounter cerrable por veterinario y facturable
- 1 invoice con pago parcial para probar

<!-- EOF -->
