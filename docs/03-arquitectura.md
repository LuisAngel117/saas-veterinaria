# Arquitectura

## Resumen
Arquitectura objetivo: monolito modular backend + frontend web, con contratos estables, scoping por sucursal y trazabilidad fuerte para evitar desviaciones.

## Diagrama logico (texto)
- Frontend (Next.js) consume API backend.
- Backend (Spring Boot) se organiza por capas: web/api -> aplicacion -> dominio -> infraestructura.
- Persistencia en Postgres 17 con migraciones Flyway.
- Auditoria transversal en modulos sensibles.
- RTM/STATUS/LOG cierran evidencia de avance.

## Tenancy & scoping
- V1 single-tenant multi-sucursal.
- `branch_id` en JWT es fuente de verdad.
- Endpoints branch-scoped requieren `X-Branch-Id` y validacion contra claim.
- Reglas de error: falta header 400, mismatch 403.

## Seguridad
- Auth con JWT access/refresh.
- Lockout por intentos fallidos.
- 2FA TOTP para ADMIN/SUPERADMIN.
- Acciones sensibles con reason requerido + auditoria before/after.

## Convenciones de API
- Errores en formato Problem Details (RFC 7807).
- Endpoints branch-scoped documentan header obligatorio.
- Contratos de request/response versionados con OpenAPI.

## Data (migraciones, IDs, money)
- Flyway como unica via de cambios de esquema.
- IDs tecnicos estables por entidad (UUID o bigint autoincremental segun modulo, a definir en implementacion).
- Moneda y montos con precision decimal (sin float).
- IVA global configurable con auditoria.

## Estrategia de pruebas y smoke
- Trazabilidad inicial via `docs/traceability/rtm.md`.
- Verificacion documental obligatoria: `verify-docs-eof` + `preflight`.
- Smoke tecnico minimo prioriza walking skeleton E2E (auth + scoping + crear cita).

## Anti-desviacion
- Sprints masters permanecen en DRAFT hasta validacion de planning.
- Cambios de alcance/contrato solo via ADR/RFC/CHANGELOG.
- No marcar DONE sin validacion local del usuario.

<!-- EOF -->
