# Arquitectura

## 1) Resumen de arquitectura (backend + frontend)
- Backend: monolito modular (Spring Boot) con capas:
  - web/api (controllers, DTOs, auth filters)
  - aplicación (use-cases, servicios de aplicación)
  - dominio (entidades, invariantes, reglas)
  - infraestructura (JPA repos, integraciones locales, storage adjuntos)
- Frontend: Next.js (TS) con:
  - rutas protegidas (guards)
  - cliente API con manejo de sesión/refresh
  - estado por sucursal (branch) y selector al login
  - UI en español (Tailwind + shadcn)
- DB: Postgres 17. Migraciones: Flyway.
- Sin dependencias externas en core (offline-first real).

## 2) Diagrama lógico (texto)
- Frontend (Next.js)
  - Auth UI: login + 2FA (si aplica) + selector sucursal
  - Módulos UI: Agenda / Clientes / Mascotas / Atenciones / Facturación / Inventario / Reportes / Admin
  - API client: agrega Authorization + X-Branch-Id cuando corresponda
- Backend (Spring Boot)
  - Módulo Autenticación/Seguridad
  - Módulo Scoping (branch)
  - Módulo Agenda
  - Módulo CRM (clientes/mascotas)
  - Módulo Clínica (atenciones SOAP + adjuntos)
  - Módulo Catálogo (servicios + BOM)
  - Módulo Inventario
  - Módulo Facturación
  - Módulo Reportes
  - Módulo Auditoría
- DB (Postgres)
  - tablas por módulo con `branch_id` donde aplique

## 3) Decisiones clave (referencias)
- Stack: ver `docs/decisions/adr-0001-stack.md`
- Arquitectura: ver `docs/decisions/adr-0002-arquitectura.md`
- Tenancy/scoping: ver `docs/decisions/adr-0003-tenancy-scoping.md`
- Seguridad/auth: ver `docs/decisions/adr-0004-seguridad-auth.md`
- Auditoría: ver `docs/decisions/adr-0005-auditoria.md`
- UX principios: ver `docs/decisions/adr-0006-ux-principios.md`
- Walking skeleton: ver `docs/decisions/adr-0007-walking-skeleton.md`

## 4) Tenancy & scoping (single-tenant + multi-sucursal)
- Single-tenant v1: no existe tenant_id.
- Multi-sucursal:
  - La sesión contiene `branch_id` en JWT como fuente de verdad.
  - En endpoints branch-scoped, se exige header `X-Branch-Id`.
  - Validación:
    - Si falta header: 400 (bad request: falta contexto requerido).
    - Si header != claim branch_id: 403 (forbidden: intento de acceder a otra sucursal).
- Datos branch-scoped:
  - Agenda, inventario, clientes/mascotas/atenciones/facturación (separados por `branch_id`).
- Datos globales (no branch-scoped, v1):
  - usuarios/roles/permisos (aunque el acceso se filtra por branch al operar).
  - configuración sensible (IVA default, feature flags).

## 5) Seguridad
### Auth
- Login con password (hash fuerte), devuelve:
  - access token JWT (1h)
  - refresh token (7d) con rotación
- Refresh endpoint rota refresh tokens y emite nuevo access.
- Logout invalida refresh token actual.
- Lockout: 4 intentos fallidos → 15 min.

### 2FA TOTP
- Solo ADMIN/SUPERADMIN.
- Flujos:
  - enrolar (generar secreto, mostrar QR/otpauth)
  - validar en login (si usuario requiere 2FA)
  - reset (solo SUPERADMIN/ADMIN autorizado) con auditoría

## 6) Convenciones de API (contrato)
### Naming
- Endpoints en inglés (consistencia técnica), UI en español.
- Versionado: `/api/v1/...`
- Recursos en plural: `/appointments`, `/clients`, `/pets`, `/encounters`, `/invoices`, `/inventory-items`, `/reports`

### Errores: Problem Details (RFC 7807)
- `application/problem+json`
- Campos mínimos: `type`, `title`, `status`, `detail`, `instance`
- Extensiones: `code` (string estable), `errors` (map field→message), `traceId`, `timestamp`
- Casos clave:
  - Validación input: 400 con `errors`
  - Auth requerida: 401
  - Permisos insuficientes o mismatch branch: 403
  - Recurso no encontrado: 404
  - Conflicto negocio (no-solape): 409
  - Integridad (ej. código interno duplicado): 409

## 7) Data: IDs, money, time
- IDs:
  - Preferir UUID (backend) para entidades principales.
  - Campos “código interno” (mascota) único por branch.
- Money:
  - BigDecimal (scale 2).
  - Moneda: USD (implícito en v1; si se requiere multi-moneda → RFC).
- Time:
  - timezone negocio: America/Guayaquil.
  - Guardar timestamps en UTC y presentar en zona negocio (o guardar con offset consistente); definir en implementación.

## 8) Adjuntos (offline-first)
- Almacenamiento local (filesystem) con referencia en DB:
  - tabla `attachment` con metadata + path.
- Límite: 10MB por archivo.
- Tipos: PDF, PNG, JPG.
- Seguridad:
  - validar MIME/extension
  - proteger descarga por auth + branch-scoping

## 9) Reglas clave de agenda (no-solape)
- Recurso compuesto: `room_id` + `vet_id`.
- Slot base: 30m; buffer configurable (default 10m).
- Conflicto:
  - Si hay overlap en sala o veterinario → 409 (conflict) salvo override.
- Override:
  - requiere permiso + reason + auditoría before/after; registrar flag en cita.

## 10) Inventario y consumo por BOM
- Productos y stock por branch.
- Movimientos:
  - IN (ingreso), OUT (egreso), ADJUST (ajuste), CONSUME (consumo por servicio/atención), OVERRIDE (si aplica).
- BOM:
  - servicio define componentes (producto + cantidad).
  - consumo se ejecuta al “cerrar atención” (recomendado) para alinear clínica→stock;
    si se decide consumir al “facturar” debe quedar en RFC/ADR.

## 11) Estrategia de pruebas
- Unit tests (reglas dominio y servicios).
- Integration tests (repos + scoping + no-solape + facturación).
- Smoke scripts (PowerShell):
  - healthcheck
  - auth + me
  - scoping check (sin/ con X-Branch-Id)
  - crear cita no-solape
  - iniciar atención + cerrar + generar factura + pago
- Release-candidate (RC): valida build/test y checks de docs/EOF.

## 12) Anti-desviación
- No inventar: todo lo no definido → RFC/ADR.
- Sprints bloqueados.
- DoR/DoD obligatorios.
- RTM + state snapshot se actualizan al cierre.

<!-- EOF -->
