# Runbook (operación local)

Este runbook define el objetivo operativo. Algunos comandos/scripts serán implementados en sprints; mientras tanto se mantiene como contrato.

## 1) Prerequisitos
- Git
- Java 21 (Temurin recomendado)
- Node.js LTS (20+ recomendado)
- Postgres 17 (instalable local)
- PowerShell 7 (recomendado en Windows; en Linux usar pwsh)
- (Opcional) Docker (NO en v1 inicial)

## 2) Variables de entorno (objetivo)
Backend (ejemplo, se definirá en implementación):
- `SPRING_PROFILES_ACTIVE=local`
- `DB_HOST=localhost`
- `DB_PORT=5432`
- `DB_NAME=saas_veterinaria`
- `DB_USER=...`
- `DB_PASSWORD=...`
- `JWT_SECRET=...`
- `ATTACHMENTS_DIR=./.data/attachments`
Frontend:
- `NEXT_PUBLIC_API_BASE_URL=http://localhost:8080`

## 3) Crear DB local (Postgres 17)
Objetivo:
- Base de datos `saas_veterinaria`
- Usuario con permisos

Ejemplo (manual):
- Crear usuario y DB desde psql/pgAdmin.

## 4) Migraciones
- Flyway migrará automáticamente al levantar backend (según configuración futura).
- Comando “verdad” esperado (cuando exista):
  - `./mvnw test`
  - `./mvnw spring-boot:run`

## 5) Levantar backend (objetivo)
- Puerto objetivo: 8080
- Endpoints esperados:
  - `/actuator/health`
  - `/api/v1/auth/login`
  - `/api/v1/me`

## 6) Levantar frontend (objetivo)
- Puerto objetivo: 3000
- Comandos “verdad” esperados:
  - `npm ci`
  - `npm run build`
  - `npm run dev`

## 7) Smoke tests (objetivo)
Se implementarán scripts en `scripts/smoke/`:
- `smoke-auth.ps1`: health + login + me
- `smoke-scoping.ps1`: endpoints branch-scoped exigen X-Branch-Id
- `smoke-core-flow.ps1`: cita → atención → factura → pago → reporte

## 8) Troubleshooting típico
- Error conexión DB: revisar credenciales/puerto.
- Error CORS: verificar `NEXT_PUBLIC_API_BASE_URL`.
- Error “missing X-Branch-Id”: asegúrate de enviar header en requests branch-scoped.
- Error “overlap conflict”: revisar sala/vet y slot.

## 9) Scripts “verdad” (actuales)
- Verificador docs EOF:
  - `pwsh -ExecutionPolicy Bypass -File .\scripts\verify\verify-docs-eof.ps1`
- Preflight:
  - `pwsh -ExecutionPolicy Bypass -File .\scripts\verify\preflight.ps1`

<!-- EOF -->
