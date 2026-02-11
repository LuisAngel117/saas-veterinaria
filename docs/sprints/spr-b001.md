# PATH: docs/sprints/spr-b001.md

# SPR-B001 — Walking Skeleton BACK: Auth + Branch Scoping + Problem Details + Seeds + Smoke

**Estado:** BLOQUEADO (no editar; cambios solo por RFC/ADR/CHANGELOG)  
**Stage:** 1  
**Duración objetivo:** 45–90 min (referencial)  
**Tipo:** Backend  

## 1) Objetivo

Entregar el **walking skeleton backend** mínimo, ejecutable y verificable en local, que cierre **BRD-REQ-001/002/020/021/022**:

- **Auth** (login + refresh + logout) con JWT access/refresh y **rotación refresh**. (BRD-REQ-001)
- **Scoping seguro por sucursal**: `branch_id` en JWT (fuente de verdad) + `X-Branch-Id` obligatorio en endpoints branch-scoped, validado contra claim (400/403). (BRD-REQ-002)
- **Seeds demo** (usuarios/roles y 1+ sucursal) para correr el flujo en 2–3 minutos sin configuración manual. (BRD-REQ-020)
- **OpenAPI/Swagger** actualizado para endpoints implementados. (BRD-REQ-021)
- **Errores estándar** con **Problem Details (RFC 7807)** + códigos internos consistentes. (BRD-REQ-022)
- **Smoke script** mínimo para auth+scoping, y evidencia en LOG/STATUS/RTM para quedar READY_FOR_VALIDATION.

**Requisitos objetivo (trazabilidad):** BRD-REQ-001, BRD-REQ-002, BRD-REQ-020, BRD-REQ-021, BRD-REQ-022.

## 2) Alcance

### Incluye
- Backend Spring Boot (Java 21) operando con Postgres 17 y Flyway ya configurados en el repo.
- Modelo mínimo de datos para:
  - `branch` (sucursal)
  - `user_account` (usuario)
  - `role` + asignación a usuario
  - `user_branch` (permisos de acceso a sucursal)
  - `refresh_token` persistido (rotación y revocación)
- Endpoints mínimos:
  1) `POST /api/auth/login`
  2) `POST /api/auth/refresh`
  3) `POST /api/auth/logout`
  4) `GET /api/me` (**branch-scoped**) para validar scoping.
- Regla de selección de branch “al iniciar sesión” (sin inventar flujo extra):
  - `POST /api/auth/login` acepta `branchId` opcional.
  - Si credenciales OK y `branchId` **faltante**:
    - Si usuario tiene **1** branch permitido → emitir tokens con ese branch.
    - Si usuario tiene **>1** branch permitido → responder **409 Problem Details** (`code=BRANCH_REQUIRED`) incluyendo lista de branches permitidos (id, name) para que UI seleccione y reintente con `branchId`.
  - Si credenciales OK y `branchId` presente pero **no permitido** → **403 Problem Details** (`code=BRANCH_FORBIDDEN`).
- Implementación de scoping:
  - En endpoints **branch-scoped** (en este sprint: `GET /api/me`):
    - Si falta header `X-Branch-Id` → **400** (`code=BRANCH_HEADER_MISSING`)
    - Si `X-Branch-Id` != `branch_id` del JWT → **403** (`code=BRANCH_SCOPE_MISMATCH`)
- Problem Details (RFC 7807) como formato único de error para 4xx/5xx del API.
- OpenAPI/Swagger habilitado y accesible en local.
- Scripts:
  - `scripts/smoke/sprint-b001.ps1` (auth + scoping)
- Actualización de:
  - `docs/log/log.md` (append-only)
  - `docs/status/status.md`
  - `docs/traceability/rtm.md`
  - `docs/state/state.md` (snapshot post-sprint)

### Excluye
- 2FA TOTP (eso es SPR-B003 o posterior).
- Auditoría before/after (eso es sprint dedicado).
- Agenda/CRM/Clínica/Inventario/Facturación: **NO** se implementan aquí.
- UI/Frontend: **NO** (esta pista es BACK).
- Multi-tenant (v1 es single-tenant).

## 3) Pre-check (obligatorio para Codex)

1) `git status --porcelain`
   - Debe estar limpio o **DETENER**.
2) `git config user.name` y `git config user.email`
   - Deben existir o **DETENER**.
3) `git remote -v`
   - Debe coincidir con `docs/project-lock.md -> repo_url` o **DETENER**.
4) `git rev-parse --abbrev-ref HEAD`
   - Debe ser `main` o **DETENER**.
5) Verificación docs:
   - `pwsh -ExecutionPolicy Bypass -File .\scripts\verify\verify-docs-eof.ps1` (debe pasar)
   - `pwsh -ExecutionPolicy Bypass -File .\scripts\verify\preflight.ps1` (debe pasar)
6) Lectura obligatoria (en orden):
   - `AGENTS.md`
   - `docs/project-lock.md`
   - `docs/00-indice.md`
   - `docs/state/state.md`
   - `docs/quality/definition-of-ready.md`
   - `docs/quality/definition-of-done.md`
   - `docs/02-brd.md` (secciones Auth/Scoping/Problem Details/Seeds)
   - `docs/03-arquitectura.md`
   - `docs/04-convenciones.md`
   - `docs/05-seguridad.md`

Si DoR falla (Definition of Ready) → marcar sprint BLOCKED (status/log) y detener sin implementar.

## 4) Entregables

### Backend (código)
- Auth JWT access/refresh + refresh rotation + logout (revocación).
- Scoping branch seguro aplicado a endpoint `GET /api/me`.
- Problem Details centralizado (handler) + códigos internos.
- OpenAPI/Swagger para endpoints de auth y `/api/me`.
- Seeds demo: usuarios/roles/sucursal/esquema mínimo.

### DB / Migraciones (Flyway)
- Migraciones para tablas mínimas mencionadas (sin extras).

### Scripts
- `scripts/smoke/sprint-b001.ps1` ejecutable en PowerShell (Windows).
  - Debe dejar output usable para pegar en `docs/log/log.md`.

### Docs (gobernanza)
- `docs/log/log.md` (entrada append-only de SPR-B001)
- `docs/status/status.md` (fila SPR-B001 en READY_FOR_VALIDATION al final)
- `docs/traceability/rtm.md` (mapear BRD-REQ-001/002/020/021/022 → SPR-B001 + evidencia)
- `docs/state/state.md` (snapshot actualizado y próximo sprint recomendado)

## 5) Instrucciones de implementación (cerradas)

> Nota: **NO inventar paquetes**. Detectar el package base desde la clase `*Application` existente y mantenerlo.  
> Código (clases/variables) en **inglés**.

### 5.1 Dependencias / Config base
1) Confirmar que el backend ya usa:
   - Spring Boot 3.x
   - Java 21
   - Postgres driver
   - Flyway
2) Agregar (si no existe) soporte OpenAPI:
   - `springdoc-openapi-starter-webmvc-ui` (versión compatible con Boot 3).
3) Configurar properties (sin inventar nombres raros):
   - `app.security.jwt.issuer`
   - `app.security.jwt.access-ttl` (1h)
   - `app.security.jwt.refresh-ttl` (7d)
   - `app.security.jwt.secret` (para firma; usar env var en runbook)
   - `app.security.jwt.refresh-rotate=true`
   - `app.scoping.branch.header-name=X-Branch-Id`

### 5.2 Modelo de datos (mínimo)
Crear migraciones Flyway (nombres incrementales según convención del repo) para tablas:

- `branch`:
  - `id` (uuid o bigint; **elige consistente con repo**; recomendado UUID si el repo ya lo usa)
  - `name`
  - timestamps mínimos (`created_at`)
- `role`:
  - `id`
  - `code` (SUPERADMIN/ADMIN/RECEPCION/VETERINARIO)
- `user_account`:
  - `id`
  - `email` (unique)
  - `password_hash`
  - `is_active`
  - `created_at`
- `user_role` (join)
- `user_branch` (join)
- `refresh_token`:
  - `id`
  - `user_id`
  - `branch_id`
  - `token_hash` (NO guardar token en claro; almacenar hash SHA-256)
  - `expires_at`
  - `revoked_at` (nullable)
  - `rotated_from_token_id` (nullable)
  - `created_at`

Reglas mínimas:
- refresh token válido si:
  - no expirado
  - `revoked_at` null
- al refrescar:
  - revocar el refresh anterior (`revoked_at=now`)
  - crear nuevo refresh con `rotated_from_token_id = anterior`

### 5.3 Seeds demo (BRD-REQ-020)
Insertar en migración/seeder (según convención del repo):
- 1 branch “Matriz” (id conocido para smoke; si UUID, capturarlo en script por endpoint o leerlo del login response).
- Roles: SUPERADMIN/ADMIN/RECEPCION/VETERINARIO.
- Usuarios (emails exactos del contrato) con password hasheado:
  - `superadmin@demo.local / Demo1234!`
  - `admin@demo.local / Demo1234!`
  - `recepcion@demo.local / Demo1234!`
  - `vet@demo.local / Demo1234!`
- Asignación:
  - cada user → su role
  - cada user → branch “Matriz”
- **Importante:** Mantener credenciales demo fijas tal cual.

### 5.4 Auth API (BRD-REQ-001 + BRD-REQ-002)
Implementar endpoints:

#### POST /api/auth/login
Request JSON:
- `email` (string)
- `password` (string)
- `branchId` (string/uuid o number según tipo) **opcional**

Comportamiento:
1) Validar credenciales:
   - inválidas → 401 Problem Details (`code=AUTH_INVALID_CREDENTIALS`)
2) Obtener branches permitidos del usuario.
3) Si `branchId` es null:
   - si allowedBranches.size == 1 → usar ese branch y emitir tokens
   - si allowedBranches.size > 1 → 409 Problem Details (`code=BRANCH_REQUIRED`) con propiedad extra:
     - `branches: [{id, name}, ...]`
4) Si `branchId` presente:
   - si no está en allowedBranches → 403 (`code=BRANCH_FORBIDDEN`)
   - si está permitido → emitir tokens con claim `branch_id = branchId`

Response 200:
- `accessToken`
- `refreshToken`
- `tokenType` = "Bearer"
- `expiresInSeconds`
- `user: { id, email, roleCodes: [...], branchId }`
- `branches` (opcional, solo si lo necesitas; **no requerido** si ya resolviste branch)

#### POST /api/auth/refresh
Request JSON:
- `refreshToken`

Comportamiento:
- Validar hash y estado en DB.
- Si inválido/expirado/revocado → 401 (`code=AUTH_REFRESH_INVALID`)
- Rotar refresh token (revocar anterior, crear nuevo).
- Emitir nuevo access + refresh.

Response 200:
- mismo formato de tokens que login (incluye `branchId` del refresh token).

#### POST /api/auth/logout
Request JSON:
- `refreshToken`
Comportamiento:
- Revocar refresh token si existe.
- Responder 204 o 200 simple.

### 5.5 JWT claims y Security
- El access token debe incluir al menos:
  - `sub` (user id)
  - `email`
  - `roles` (list)
  - `branch_id` (seleccionado en login)
  - `iat`, `exp`, `iss`
- Configurar Spring Security:
  - Permitir público:
    - `/api/auth/**`
    - `/actuator/health` (si existe)
    - `/v3/api-docs/**`, `/swagger-ui/**`
  - Todo lo demás requiere auth.

### 5.6 Branch Scoping (BRD-REQ-002)
Implementar scoping en endpoint branch-scoped `GET /api/me`:

#### GET /api/me
- Requiere Authorization Bearer (access token)
- Es **branch-scoped**
Reglas:
- Si falta header `X-Branch-Id` → 400 (`code=BRANCH_HEADER_MISSING`)
- Si header != claim `branch_id` → 403 (`code=BRANCH_SCOPE_MISMATCH`)
- Si OK → 200 con:
  - `user { id, email, roleCodes }`
  - `branch { id }` (y name si quieres)

**Implementación recomendada (cerrada):**
- Crear un filtro/Interceptor de scoping aplicable SOLO a rutas branch-scoped definidas por allowlist:
  - Para este sprint, aplicar a `/api/me` solamente (para evitar sobre-alcance).
- Extraer claim `branch_id` del JWT ya parseado.
- Comparar con header `X-Branch-Id`.

### 5.7 Problem Details (BRD-REQ-022)
- Centralizar manejo de errores:
  - Usar `application/problem+json`
  - Campos RFC 7807: `type`, `title`, `status`, `detail`, `instance`
  - Agregar `code` (string) como propiedad extra.
  - Para validaciones, agregar `errors` (lista de {field, message}).
- Asegurar que los casos de 400/401/403/409 del sprint responden Problem Details consistente.

### 5.8 OpenAPI/Swagger (BRD-REQ-021)
- Exponer swagger UI.
- Documentar endpoints implementados con schemas de request/response y ejemplos mínimos.
- Documentar:
  - header `X-Branch-Id` requerido en `/api/me`
  - códigos de respuesta y Problem Details.

### 5.9 Tests (mínimos)
Agregar tests de integración (o web tests) para:
- Login sin branchId con 1 branch → 200 tokens.
- Login sin branchId con >1 branch (si puedes simular con seed adicional en test) → 409 BRANCH_REQUIRED.
- `/api/me`:
  - sin header → 400
  - con header mismatch → 403
  - con header match → 200

> Mantener tests rápidos; no añadir frameworks extra.

### 5.10 Scripts smoke (PowerShell)
Crear `scripts/smoke/sprint-b001.ps1` con:
1) Healthcheck (si existe `/actuator/health`)
2) Login (recepcion) → obtener tokens
3) GET `/api/me`:
   - sin `X-Branch-Id` → esperar 400
   - con `X-Branch-Id` correcto → 200
   - con `X-Branch-Id` incorrecto → 403
4) Refresh token → obtener nuevos tokens
5) Logout → revocar refresh token
6) Refresh con token revocado → 401

El script debe imprimir claramente OK/FAIL por bloque.

## 6) Criterios de aceptación (AC)

- [ ] Flyway crea tablas mínimas (`branch`, `user_account`, `role`, `user_role`, `user_branch`, `refresh_token`) y el backend arranca con DB limpia.
- [ ] Seeds demo creados con credenciales exactas (4 usuarios) y al menos 1 branch.
- [ ] `POST /api/auth/login`:
  - [ ] 401 `AUTH_INVALID_CREDENTIALS` en credenciales inválidas (Problem Details).
  - [ ] 200 tokens en credenciales válidas y branch resuelto.
  - [ ] 409 `BRANCH_REQUIRED` (Problem Details) si branchId faltante y user tiene >1 branch (si se prueba).
  - [ ] 403 `BRANCH_FORBIDDEN` si branchId no permitido.
- [ ] `POST /api/auth/refresh` rota refresh token (anterior revocado, nuevo emitido).
- [ ] `POST /api/auth/logout` revoca refresh token.
- [ ] `GET /api/me`:
  - [ ] 400 `BRANCH_HEADER_MISSING` si falta `X-Branch-Id`
  - [ ] 403 `BRANCH_SCOPE_MISMATCH` si no coincide con claim `branch_id`
  - [ ] 200 si coincide
- [ ] Todos los errores anteriores usan `application/problem+json` con RFC 7807 + propiedad `code`.
- [ ] Swagger UI disponible y documenta estos endpoints + header `X-Branch-Id` en `/api/me`.
- [ ] `scripts/smoke/sprint-b001.ps1` existe y pasa contra backend en ejecución.
- [ ] `docs/log/log.md` tiene entrada append-only del sprint con placeholders/output.
- [ ] `docs/status/status.md` tiene fila SPR-B001 en `READY_FOR_VALIDATION` con hash commit.
- [ ] `docs/traceability/rtm.md` mapea BRD-REQ-001/002/020/021/022 a SPR-B001 con evidencia.
- [ ] `docs/state/state.md` actualizado con resumen y próximo sprint recomendado.
- [ ] `verify-docs-eof.ps1` y `preflight.ps1` pasan al final.

## 7) Smoke test manual (usuario)

### Backend up
1) Levantar backend (según runbook):
- Windows (si existe wrapper): `.\mvnw.cmd spring-boot:run`
- Linux/macOS: `./mvnw spring-boot:run`

2) Ejecutar smoke:
- `pwsh -ExecutionPolicy Bypass -File .\scripts\smoke\sprint-b001.ps1`

3) Evidencia:
- Pegar el output completo en `docs/log/log.md` bajo la entrada SPR-B001.

## 8) Comandos verdad

- Backend tests:
  - Windows: `.\mvnw.cmd test`
  - Linux/macOS: `./mvnw test`
- Backend run:
  - Windows: `.\mvnw.cmd spring-boot:run`
  - Linux/macOS: `./mvnw spring-boot:run`

Si el repo usa otros comandos (Gradle, etc.), **usar los existentes** y documentar en LOG.

## 9) DoD

Además de AC:
- [ ] Cumple `docs/quality/definition-of-done.md`
- [ ] `docs/status/status.md` queda en `READY_FOR_VALIDATION` (NUNCA DONE)
- [ ] `docs/log/log.md` contiene comandos ejecutados + output o placeholder
- [ ] Repo queda compilable y `git status` limpio

## 10) Si hay huecos/contradicciones

- Si aparece contradicción con docs (BRD/Arquitectura/Lock):
  - Crear RFC en `docs/rfcs/`
  - Actualizar `docs/changelog.md`
  - Detener si bloquea (marcar BLOCKED en status + log)
- Prohibido “inventar” endpoints adicionales fuera de este sprint.

<!-- EOF -->
