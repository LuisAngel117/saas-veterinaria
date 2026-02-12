# RFC-0006 — Contrato config operativa (rooms + veterinarian_profile)

**Estado:** DRAFT  
**Fecha:** 2026-02-11  
**Autor:** Codex

## 1) Contexto
- BRD-REQ-006 exige configuracion operativa: sucursales, salas y perfiles veterinario.
- SPR-B005 requiere endpoints/servicios branch-scoped y seeds completos.
- Actualmente no existe contrato de endpoints/payloads ni permisos ROOM/VET definidos en docs.

## 2) Problema
Sin contrato de API y permisos, SPR-B005 no puede implementarse sin inventar alcance.

## 3) Opciones
- Opcion A: endpoints REST separados por recurso (rooms, veterinarian-profiles) con CRUD basico + activacion/desactivacion.
- Opcion B: endpoints agrupados bajo /api/v1/config con payloads mixtos.

## 4) Decision propuesta
Adoptar Opcion A (REST por recurso) por consistencia con convenciones `/api/v1` y recursos en plural.

### 4.1 Permisos nuevos (propuestos)
- ROOM_VIEW, ROOM_CREATE, ROOM_EDIT
- VET_PROFILE_VIEW, VET_PROFILE_CREATE, VET_PROFILE_EDIT

### 4.2 Endpoints rooms (branch-scoped)
- GET /api/v1/rooms
  - Permiso: ROOM_VIEW
  - Query opcional: includeInactive (default false)
  - 200: lista [{id,name,isActive,createdAt}]
- GET /api/v1/rooms/{roomId}
  - Permiso: ROOM_VIEW
  - 200/404
- POST /api/v1/rooms
  - Permiso: ROOM_CREATE
  - Request: {name}
  - 201: {id,name,isActive,createdAt}
  - 409: ROOM_NAME_TAKEN (unique branch_id + name)
- PATCH /api/v1/rooms/{roomId}
  - Permiso: ROOM_EDIT
  - Request: {name?, isActive?}
  - 200/404/409 (ROOM_NAME_TAKEN)

### 4.3 Endpoints veterinarian profiles (branch-scoped)
- GET /api/v1/veterinarian-profiles
  - Permiso: VET_PROFILE_VIEW
  - Query opcional: includeInactive (default false)
  - 200: lista [{id,branchId,userId,licenseNumber,isActive,createdAt}]
- GET /api/v1/veterinarian-profiles/{id}
  - Permiso: VET_PROFILE_VIEW
  - 200/404
- POST /api/v1/veterinarian-profiles
  - Permiso: VET_PROFILE_CREATE
  - Request: {userId, licenseNumber?}
  - 201: {id,branchId,userId,licenseNumber,isActive,createdAt}
  - 404: USER_NOT_FOUND
  - 409: VET_PROFILE_ALREADY_EXISTS (unique branch_id + user_id)
- PATCH /api/v1/veterinarian-profiles/{id}
  - Permiso: VET_PROFILE_EDIT
  - Request: {licenseNumber?, isActive?}
  - 200/404/409 (VET_PROFILE_ALREADY_EXISTS si se permite cambio de userId; si no se permite, no exponer userId)

### 4.4 Reglas de scoping
- Branch-scoped estricto:
  - Requiere header X-Branch-Id
  - Valida contra claim branch_id del JWT (400/403 segun contrato)

### 4.5 Seeds (SPR-B005)
- Por cada branch demo:
  - 2 rooms: "Consulta 1", "Consulta 2"
  - 1 veterinarian_profile asociado a usuario VETERINARIO demo

## 5) Impacto
- Impacto tecnico: nuevos controllers/services/repos + migraciones de room y veterinarian_profile.
- Impacto UX: UI podra listar/crear salas y perfiles vet.
- Impacto seguridad: permisos nuevos para rooms/vet profiles.
- Impacto datos/migraciones: tablas room y veterinarian_profile con unique(branch_id,name) y unique(branch_id,user_id).

## 6) Plan de implementacion
1) Aceptar RFC y actualizar docs/10-permisos.md con permisos nuevos.
2) Implementar endpoints y validaciones en SPR-B005.
3) Actualizar seeds demo.
4) Actualizar RTM/status/log/state con evidencia.

## 7) Riesgos
- Sin permisos definidos se rompe DoR.
- Sin unique constraints se rompe no-solape futuro de agenda.

## 8) Archivos a cambiar (si se aprueba)
- docs/10-permisos.md (agregar ROOM_* y VET_PROFILE_*).
- docs/sprints/spr-b005.md (referenciar permisos y endpoints definidos).
- src/main/java/com/saasveterinaria/room/**
- src/main/java/com/saasveterinaria/vet/**
- src/main/resources/db/migration/* (room + veterinarian_profile)
- docs/status/status.md, docs/log/log.md, docs/traceability/rtm.md, docs/state/state.md

<!-- EOF -->
