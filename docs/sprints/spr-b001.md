# SPR-B001 — Walking Skeleton Backend: Auth + Branch Scoping + Seeds + OpenAPI + Problem Details

**Estado:** BLOQUEADO (no editar; cambios solo por RFC/ADR/CHANGELOG)  
**Stage:** 1  
**Duración objetivo:** 45–90 min (referencial)  
**Tipo:** Backend  

## 1) Objetivo

Cerrar (BACK) los requisitos base del “walking skeleton” con ejecución local y contratos estables:

- **BRD-REQ-001:** Auth (login + refresh + logout) con JWT access/refresh + rotación de refresh.
- **BRD-REQ-002:** Scoping seguro por sucursal (branch): `branch_id` en JWT como fuente de verdad + `X-Branch-Id` obligatorio en endpoints branch-scoped y validado vs claim.
- **BRD-REQ-020:** Seeds/demo local mínimo (usuarios/roles + sucursales) para probar flujo en 2–3 min.
- **BRD-REQ-021:** OpenAPI/Swagger actualizado para endpoints implementados.
- **BRD-REQ-022:** Estándar de errores API: Problem Details (RFC 7807) consistente.

Resultado esperado del sprint:
- Backend levanta en local.
- Se puede ejecutar un smoke manual que pruebe: health → login → me → scoping 400/403/200.
- Queda evidencia y trazabilidad listas para **READY_FOR_VALIDATION** (nunca DONE por Codex).

## 2) Alcance

### Incluye

**Contratos canónicos (obligatorio respetar `docs/project-lock.md`):**
- appointment_statuses: `RESERVED|CONFIRMED|IN_ATTENTION|CLOSED|CANCELLED`
- invoice_statuses: `PENDING|PAID|ANNULLED`
- Acción anulación: `ANNUL (anular)`

**Funcionalidad (MVP backend):**
1) Autenticación:
   - Login (email + password) → emite access+refresh (si branch resuelta).
   - Refresh (rotación refresh) → emite nuevos tokens e invalida el refresh anterior.
   - Logout → invalida refresh token.
   - Lockout por intentos fallidos: 4 intentos fallidos → lock temporal 15 min.

2) Selección de sucursal al login (branch selection):
   - Si usuario pertenece a **más de 1 branch** y NO se especifica branch en login → retornar Problem Details indicando que se requiere selección y devolviendo lista de branches permitidas.
   - Si usuario pertenece a 1 branch → branch se asume automáticamente.

3) Scoping en API (branch-scoped):
   - Endpoints marcados como branch-scoped exigen:
     - Header `X-Branch-Id` presente → si falta: **400** Problem Details.
     - `X-Branch-Id` debe coincidir con `branch_id` del JWT → si mismatch: **403** Problem Details.
   - Endpoints no branch-scoped NO requieren `X-Branch-Id`.

4) Seeds demo mínimos (v1):
   - Crear branches demo (mínimo 2).
   - Crear usuarios demo (SUPERADMIN/ADMIN/RECEPCION/VETERINARIO) con password demo.
   - Asignación user→branch:
     - ADMIN asignado a 2 branches (para probar selección).
     - RECEPCION y VETERINARIO al menos a 1 branch.
     - SUPERADMIN puede acceder a todas (o también a 2; definir y documentar).
   - Todo seed debe ser idempotente.

5) OpenAPI/Swagger:
   - Documentar endpoints implementados en este sprint (schemas request/response y errores Problem Details).
   - Incluir security scheme (Bearer JWT) y ejemplos de error 400/403/409.

6) Problem Details (RFC 7807) estándar:
   - Respuestas de error deben seguir `application/problem+json`.
   - Incluir extensiones mínimas: `code`, `traceId`, y si aplica `errors` (validación).

### Excluye

- 2FA TOTP (BRD-REQ-004).
- CRUD de usuarios/roles/permisos (más allá del seed mínimo).
- Dominio clínico/agenda/inventario/facturación completo (solo infra de auth+scoping).
- Multi-tenant (prohibido en v1).
- Integraciones online-only.

## 3) Pre-check (obligatorio para Codex)

Antes de implementar, Codex DEBE:

- Verificar git status limpio.
- Verificar git identity (user.name/email) presente.
- Verificar remote coincide con `docs/project-lock.md`.
- Verificar branch actual (esperado: main).
- Verificar que existe este sprint: `docs/sprints/spr-b001.md`.
- Leer obligatoriamente:
  - `docs/state/state.md`
  - `docs/quality/definition-of-ready.md`
  - `docs/quality/definition-of-done.md`
  - `AGENTS.md` (si existe)
  - `docs/project-lock.md`
  - `docs/02-brd.md` + `docs/03-arquitectura.md` + `docs/05-seguridad.md` + `docs/10-permisos.md`

GATE DoR:
- Si DoR falla (TBD bloqueantes / contradicción / falta de decisión), Codex NO implementa:
  - crea RFC si aplica,
  - registra bloqueo en LOG,
  - marca sprint como BLOCKED en STATUS,
  - y detiene.

## 4) Entregables

### Código (backend)
- Implementación auth + scoping + Problem Details + seeds + OpenAPI, respetando arquitectura y convenciones del repo.
- Migraciones DB (Flyway) para:
  - branches
  - users
  - user_branch (o equivalente)
  - refresh_tokens (rotación / revocación)
  - auth lockout (campos en user o tabla separada; según convención existente)
- Configuración local (sin servicios externos).

### Scripts
- `scripts/smoke/spr-b001.ps1` (o equivalente) con smoke mínimo reproducible:
  - health
  - login
  - me
  - scoping 400 (sin header) / 403 (mismatch) / 200 (match)

### Docs
- `docs/log/log.md` (append-only): entrada de ejecución SPR-B001 con outputs pegables.
- `docs/status/status.md`: SPR-B001 → READY_FOR_VALIDATION (con hash commit) al finalizar.
- `docs/traceability/rtm.md`: mapear BRD-REQ-001/002/020/021/022 a SPR-B001 + evidencia commit.
- `docs/state/state.md`: snapshot post-sprint + next step recomendado.

## 5) Instrucciones de implementación (cerradas)

> Nota: NO ampliar scope. Si detectas un hueco de contrato, crea RFC/ADR y detén si bloquea.

### 5.1 Estructura y baseline
1) Identificar ruta real del backend según repo (ej. `/backend`) y usar el wrapper existente (`./mvnw`).
2) Confirmar package base desde `pom.xml`/arquitectura existente.  
   - Prohibido inventar package base.

### 5.2 Modelo mínimo de datos (DB)
Crear/usar entidades mínimas:

- Branch:
  - id (UUID)
  - name (text)
  - is_active (bool)
  - created_at (timestamp)

- User:
  - id (UUID)
  - email (unique)
  - password_hash (bcrypt)
  - role (enum/text: SUPERADMIN/ADMIN/RECEPCION/VETERINARIO)
  - is_active (bool)
  - failed_login_attempts (int)
  - locked_until (timestamp nullable)
  - created_at (timestamp)

- UserBranch:
  - user_id, branch_id (unique pair)

- RefreshToken:
  - id (UUID)
  - user_id (UUID)
  - branch_id (UUID) (token queda “scoped” al branch)
  - token_hash (hash del refresh; NO guardar refresh en texto plano)
  - issued_at, expires_at
  - revoked_at (nullable)
  - replaced_by_token_id (nullable) (para rotación)
  - created_at

### 5.3 Contrato JWT
- Access token:
  - exp: 1h
  - claims mínimos:
    - sub = user_id
    - role
    - branch_id (UUID) — fuente de verdad para scoping
- Refresh token:
  - exp: 7d
  - rotación: cada refresh invalida el anterior y emite uno nuevo.

### 5.4 Endpoints mínimos (definir e implementar exactamente)
**Auth**
1) `POST /api/auth/login`
   - Request:
     - email, password
     - branchId (opcional)
   - Casos:
     A) credenciales inválidas → 401 Problem Details (code=AUTH_INVALID_CREDENTIALS)
     B) usuario locked → 423 Problem Details (code=AUTH_LOCKED) con locked_until
     C) credenciales válidas + usuario con >1 branch + branchId ausente → 409 Problem Details:
        - code=BRANCH_SELECTION_REQUIRED
        - branches: [{id,name}]
     D) credenciales válidas + branchId presente pero no permitido → 403 Problem Details (code=BRANCH_FORBIDDEN)
     E) credenciales válidas + branch resuelta → 200:
        - accessToken, refreshToken, tokenType=Bearer, expiresInSeconds

2) `POST /api/auth/refresh`
   - Request: refreshToken
   - 200: nuevos accessToken + refreshToken
   - 401: refresh inválido/revocado/expirado (code=AUTH_REFRESH_INVALID)

3) `POST /api/auth/logout`
   - Request: refreshToken (o header Authorization si convención del repo)
   - 204: invalida refresh

**Usuario**
4) `GET /api/me`
   - Auth required (access token)
   - 200: user_id, email, role, branch_id

**Branch-scoped “probe”**
5) `GET /api/branches/current`
   - Auth required (access token)
   - Branch-scoped:
     - requiere `X-Branch-Id`
     - valida vs claim `branch_id`
   - 200: branch_id, name
   - 400: falta header (code=SCOPE_BRANCH_REQUIRED)
   - 403: mismatch (code=SCOPE_BRANCH_MISMATCH)

**Health**
6) `GET /actuator/health` (o la ruta de health que ya use el repo)
   - 200 OK

### 5.5 Scoping middleware (regla obligatoria)
- Implementar un filtro/interceptor que:
  - Determine si el endpoint es branch-scoped (por anotación o convención de ruta `/api/**` y whitelist).
  - Exija `X-Branch-Id` en branch-scoped.
  - Compare con claim `branch_id`.
  - Produzca Problem Details 400/403 según corresponda.

### 5.6 Problem Details estándar (RFC 7807)
Para errores, responder siempre:
- Content-Type: `application/problem+json`
- Campos mínimos:
  - type (URL o URN estable del error)
  - title
  - status
  - detail
  - instance (path)
- Extensiones:
  - code (string estable)
  - traceId (string)
  - errors (map opcional para validación)

### 5.7 OpenAPI/Swagger
- Publicar Swagger UI en ruta estándar del repo (según dependencia existente).
- Documentar:
  - Seguridad Bearer
  - Schemas: LoginRequest, LoginResponse, ProblemDetails (con extensiones), RefreshRequest, MeResponse, BranchCurrentResponse
  - Respuestas 400/401/403/409/423 en endpoints relevantes.

### 5.8 Seeds demo (idempotentes)
Crear seeds (Flyway o runner) con:
- Branch A: “Sucursal Centro”
- Branch B: “Sucursal Norte”
- Usuarios (email/password fijo; password hasheado):
  - superadmin@demo.local / Demo1234!
  - admin@demo.local / Demo1234!
  - recepcion@demo.local / Demo1234!
  - vet@demo.local / Demo1234!
- Asignación:
  - ADMIN → A y B (obligatorio)
  - RECEPCION → A
  - VETERINARIO → A
  - SUPERADMIN → A y B (o todas; documentar la decisión dentro del seed)

## 6) Criterios de aceptación (AC)

Checklist verificable:

- [ ] Backend levanta local sin dependencias externas.
- [ ] Seeds cargan y son idempotentes (no duplican en re-run).
- [ ] `POST /api/auth/login`:
  - [ ] 401 con Problem Details para credenciales inválidas.
  - [ ] 423 para usuario locked.
  - [ ] 409 branch selection requerido cuando aplica, con lista de branches.
  - [ ] 200 emite access+refresh cuando branch resuelta.
- [ ] `POST /api/auth/refresh` rota refresh (el anterior queda revocado) y emite nuevos tokens.
- [ ] `POST /api/auth/logout` revoca refresh.
- [ ] `GET /api/me` retorna branch_id desde claim.
- [ ] `GET /api/branches/current`:
  - [ ] sin `X-Branch-Id` → 400 (Problem Details code=SCOPE_BRANCH_REQUIRED)
  - [ ] con header mismatch → 403 (code=SCOPE_BRANCH_MISMATCH)
  - [ ] con header match → 200
- [ ] OpenAPI/Swagger muestra endpoints y security scheme.
- [ ] Errores siguen `application/problem+json` y contienen `code` y `traceId`.
- [ ] RTM actualizado para BRD-REQ-001/002/020/021/022 con evidencia commit.
- [ ] STATUS = READY_FOR_VALIDATION (nunca DONE) + LOG append con outputs.

## 7) Smoke test manual (usuario)

> Nota: el usuario pegará outputs en LOG. No inventar outputs.

Comandos sugeridos (ajustar a rutas reales del repo):

1) Levantar backend:
- `cd <backend_root>`
- `./mvnw test`
- `./mvnw spring-boot:run`

2) Smoke por PowerShell (manual):
- Health:
  - `curl.exe -sS http://localhost:8080/actuator/health`
- Login (sin branchId, usuario con múltiples branches):
  - `curl.exe -sS -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d "{\"email\":\"admin@demo.local\",\"password\":\"Demo1234!\"}"`
  - Esperado: 409 Problem Details con branches
- Login (con branchId):
  - `curl.exe -sS -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d "{\"email\":\"admin@demo.local\",\"password\":\"Demo1234!\",\"branchId\":\"<UUID_DE_BRANCH>\"}"`
  - Guardar accessToken y branch_id del claim.
- Me:
  - `curl.exe -sS http://localhost:8080/api/me -H "Authorization: Bearer <ACCESS>"`
- Branch current (sin header):
  - `curl.exe -sS http://localhost:8080/api/branches/current -H "Authorization: Bearer <ACCESS>"`
  - Esperado: 400
- Branch current (mismatch):
  - `curl.exe -sS http://localhost:8080/api/branches/current -H "Authorization: Bearer <ACCESS>" -H "X-Branch-Id: <OTRO_UUID>"`
  - Esperado: 403
- Branch current (match):
  - `curl.exe -sS http://localhost:8080/api/branches/current -H "Authorization: Bearer <ACCESS>" -H "X-Branch-Id: <UUID_DEL_CLAIM>"`
  - Esperado: 200

3) Smoke automatizado (si se implementa):
- `pwsh -ExecutionPolicy Bypass -File .\scripts\smoke\spr-b001.ps1`

Evidencia:
- Pegar outputs en `docs/log/log.md` bajo la entrada SPR-B001.

## 8) Comandos verdad

Backend:
- `./mvnw test`
- `./mvnw spring-boot:run`

Frontend: N/A (este sprint es backend).

Si algún comando no existe en el repo, marcar N/A con razón y crear RFC (no inventar).

## 9) DoD

- [ ] Todos los AC completos.
- [ ] `docs/log/log.md` actualizado (append-only) con comandos + outputs (o placeholders).
- [ ] `docs/status/status.md` actualizado a READY_FOR_VALIDATION con hash commit.
- [ ] `docs/traceability/rtm.md` actualizado para BRD-REQ-001/002/020/021/022.
- [ ] `docs/state/state.md` snapshot actualizado.
- [ ] Cumple `docs/quality/definition-of-done.md`.

## 10) Si hay huecos/contradicciones

- Prohibido editar este sprint para “arreglarlo”.
- Crear RFC en `docs/rfcs/` y (si afecta contrato/arquitectura) ADR en `docs/decisions/`.
- Actualizar `docs/changelog.md`.
- Si bloquea implementación: marcar BLOCKED en STATUS y detener.

<!-- EOF -->
