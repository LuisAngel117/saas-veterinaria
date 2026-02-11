# Permisos (matriz estable)

## 1) Roles (v1)
- SUPERADMIN
- ADMIN
- RECEPCION
- VETERINARIO

## 2) Permisos (códigos) por módulo

### Auth / sesión
- AUTH_LOGIN
- AUTH_REFRESH
- AUTH_LOGOUT
- AUTH_2FA_ENROLL
- AUTH_2FA_RESET

### Branch (scoping)
- BRANCH_SELECT
- BRANCH_VIEW

### Agenda
- AGENDA_VIEW
- AGENDA_CREATE
- AGENDA_EDIT
- AGENDA_CONFIRM
- AGENDA_CANCEL
- AGENDA_CHECKIN
- AGENDA_START_SERVICE
- AGENDA_CLOSE
- AGENDA_OVERRIDE_OVERLAP (reason required)

### Clientes / Mascotas
- CLIENT_VIEW
- CLIENT_CREATE
- CLIENT_EDIT
- PET_VIEW
- PET_CREATE
- PET_EDIT

### Clínica / Atenciones / SOAP / Adjuntos
- ENCOUNTER_VIEW
- ENCOUNTER_CREATE
- ENCOUNTER_EDIT
- ENCOUNTER_CLOSE
- ENCOUNTER_REOPEN (reason required)
- ENCOUNTER_EDIT_CLOSED (reason required)
- ATTACHMENT_UPLOAD
- ATTACHMENT_VIEW

### Catálogo servicios
- SERVICE_VIEW
- SERVICE_CREATE
- SERVICE_EDIT
- SERVICE_BOM_EDIT

### Inventario
- INVENTORY_VIEW
- INVENTORY_PRODUCT_CREATE
- INVENTORY_PRODUCT_EDIT
- INVENTORY_MOVEMENT_IN
- INVENTORY_MOVEMENT_OUT
- INVENTORY_ADJUST (reason required)
- INVENTORY_OVERRIDE_NEGATIVE (reason required)

### Facturación
- INVOICE_VIEW
- INVOICE_CREATE
- INVOICE_EDIT
- INVOICE_PRICE_OVERRIDE (reason required)
- PAYMENT_CREATE
- INVOICE_ANNUL (reason required)

### Reportes
- REPORT_VIEW
- REPORT_EXPORT

### Admin / Seguridad
- USER_VIEW
- USER_CREATE
- USER_EDIT
- ROLE_ASSIGN
- SETTINGS_TAX_UPDATE (SUPERADMIN, reason required)
- FEATURE_FLAG_UPDATE (SUPERADMIN, reason required)
- AUDIT_VIEW

## 3) Matriz rol → permisos (v1)
| Permiso | SUPERADMIN | ADMIN | RECEPCION | VETERINARIO |
|---|:---:|:---:|:---:|:---:|
| AGENDA_VIEW | ✅ | ✅ | ✅ | ✅ |
| AGENDA_CREATE/EDIT/CONFIRM/CANCEL | ✅ | ✅ | ✅ | ❌ |
| AGENDA_CHECKIN | ✅ | ✅ | ✅ | ❌ |
| AGENDA_START_SERVICE / CLOSE | ✅ | ✅ | ❌ | ✅ |
| AGENDA_OVERRIDE_OVERLAP | ✅ | ✅ | ❌ | ❌ |
| CLIENT_* / PET_* (view/create/edit) | ✅ | ✅ | ✅ | ✅ (solo view/edit según política futura; v1: ✅ view, ✅ edit limitado) |
| ENCOUNTER_CREATE/EDIT/CLOSE | ✅ | ✅ | ❌ | ✅ |
| ENCOUNTER_REOPEN / EDIT_CLOSED | ✅ | ✅ | ❌ | (✅ solo si se asigna explícito) |
| ATTACHMENT_* | ✅ | ✅ | ❌ | ✅ |
| SERVICE_* | ✅ | ✅ | ❌ | ❌ |
| INVENTORY_* | ✅ | ✅ | ❌ | ❌ |
| INVOICE_CREATE / PAYMENT_CREATE | ✅ | ✅ | ✅ | ❌ |
| INVOICE_ANNUL / PRICE_OVERRIDE | ✅ | ✅ | ❌ | ❌ |
| REPORT_VIEW/EXPORT | ✅ | ✅ | ✅ (básico) | ✅ (clínico básico) |
| USER_* / ROLE_ASSIGN | ✅ | ✅ | ❌ | ❌ |
| SETTINGS_TAX_UPDATE / FEATURE_FLAG_UPDATE | ✅ | ❌ | ❌ | ❌ |
| AUDIT_VIEW | ✅ | ✅ | ❌ | ❌ |

Nota: ajustes finos por rol pueden requerir RFC si cambian flujos core.

## 4) Acciones sensibles: reason required
- AGENDA_OVERRIDE_OVERLAP
- ENCOUNTER_REOPEN
- ENCOUNTER_EDIT_CLOSED
- INVENTORY_ADJUST
- INVENTORY_OVERRIDE_NEGATIVE
- INVOICE_PRICE_OVERRIDE
- INVOICE_ANNUL
- SETTINGS_TAX_UPDATE
- FEATURE_FLAG_UPDATE

## 5) Auditoría obligatoria
Toda acción sensible debe escribir audit_event con before/after y reason.

<!-- EOF -->
