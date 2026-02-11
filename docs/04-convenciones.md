# Convenciones

## 1) Naming (repo, archivos, paths)
- Linux-strict:
  - nombres minúsculos
  - sin espacios
  - kebab-case para docs/scripts
- Canónicos:
  - docs/log/log.md
  - docs/status/status.md
  - docs/changelog.md
- No duplicar por casing.

## 2) Naming (backend)
- Paquetes Java (inglés, minúsculas):
  - `com.saasveterinaria` (base)
- Clases/variables en inglés.
- Arquitectura por módulos:
  - `auth`, `security`, `branch`, `agenda`, `crm`, `clinic`, `catalog`, `inventory`, `billing`, `reports`, `audit`
- DTOs sufijo `Request` / `Response`.
- Exceptions de dominio específicas.

## 3) Naming (DB)
- Tablas snake_case en inglés.
- PK: `id` (UUID).
- Foreign keys: `<entity>_id` (UUID).
- Branch scoping: columna `branch_id` en tablas branch-scoped.
- Índices explícitos para:
  - búsqueda por branch
  - no-solape (room_id, vet_id, start/end)
  - campos únicos (pet_internal_code, etc.)

## 4) API
- Base path: `/api/v1`
- Recursos en plural.
- HTTP verbs:
  - GET list/detail
  - POST create
  - PUT update (completo) o PATCH (parcial) (preferir PATCH cuando aplique)
  - DELETE: evitar hard-delete; preferir `status=INACTIVE` (v1)
- Errores: Problem Details.
- Paginación estándar:
  - query: `page`, `size`, `sort`
  - respuesta: items + page metadata (definir en implementación)

## 5) Logs
- Backend logs: niveles INFO/WARN/ERROR con contexto:
  - `traceId` (si se usa)
  - `userId`, `role`, `branchId` cuando aplique
- Auditoría separada de logs (tabla audit_event).

## 6) Git / commits
- Prefijos:
  - Tandas docs: `T1: ...`, `T2: ...`
  - Sprints: `SPR-B001: ...`, `SPR-F001: ...`, `SPR-RC001: ...`
- Commits deben contener SOLO cambios del alcance.

## 7) Formateo / lint (objetivo)
- Backend:
  - formatter (Spotless recomendado) + checkstyle (opcional)
- Frontend:
  - eslint + prettier
- No se implementa hasta sprint de tooling, pero queda definido.

## 8) Timezone y fechas
- Zona negocio: America/Guayaquil.
- Evitar ambigüedad: almacenar en UTC o con offset consistente (definir en implementación).
- En UI mostrar fecha/hora local de negocio.

<!-- EOF -->
