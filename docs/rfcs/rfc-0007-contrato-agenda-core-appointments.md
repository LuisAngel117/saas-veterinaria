# RFC-0007 — Contrato agenda core (appointments + no-solape + override)

**Estado:** DRAFT  
**Fecha:** 2026-02-11  
**Autor:** Codex

## 1) Contexto
- BRD-REQ-007 exige CRUD de citas + no-solape sala+vet + override con reason+auditoria.
- SPR-B006 requiere endpoints/payloads claros para implementar sin inventar.
- No existe contrato canonico de API para appointments.

## 2) Problema
Sin contrato de endpoints, payloads y errores, SPR-B006 no puede ejecutarse.

## 3) Opciones
- Opcion A: REST /api/v1/appointments (list/get/create/update) con overlap check y override.
- Opcion B: endpoints agrupados por agenda/weekly-view con payloads mixtos.

## 4) Decision propuesta
Adoptar Opcion A (REST por recurso) por consistencia con convenciones `/api/v1`.

### 4.1 Permisos (ya existentes)
- AGENDA_VIEW
- AGENDA_CREATE
- AGENDA_EDIT
- AGENDA_OVERRIDE_OVERLAP (reason required)

### 4.2 Endpoints appointments (branch-scoped)
- GET /api/v1/appointments
  - Permiso: AGENDA_VIEW
  - Query sugerida: from, to (datetime ISO), roomId, vetUserId, clientId, petId, status
  - 200: lista [{id,branchId,roomId,vetUserId,clientId,petId,status,startAt,endAt,bufferMinutes,overrideOverlap,notes,createdAt}]

- GET /api/v1/appointments/{id}
  - Permiso: AGENDA_VIEW
  - 200/404

- POST /api/v1/appointments
  - Permiso: AGENDA_CREATE
  - Request:
    {roomId, vetUserId, clientId, petId, startAt, endAt, bufferMinutes?, notes?, overrideOverlap?, overrideReason?}
  - Regla: status inicial = RESERVED
  - 201: recurso creado
  - 400: validacion
  - 409: AGENDA_OVERLAP (si solapa y overrideOverlap != true)

- PATCH /api/v1/appointments/{id}
  - Permiso: AGENDA_EDIT
  - Request parcial: {roomId?, vetUserId?, clientId?, petId?, startAt?, endAt?, bufferMinutes?, notes?, overrideOverlap?, overrideReason?}
  - 200/404
  - 409: AGENDA_OVERLAP (si solapa y overrideOverlap != true)

### 4.3 Reglas de solape
- Recurso compuesto: room_id + vet_user_id.
- Se considera overlap si otra cita activa (status != CANCELLED) intersecta rango [startAt,endAt].
- Override requiere:
  - Permiso AGENDA_OVERRIDE_OVERLAP
  - reason (10-500 chars)
  - audit_event before/after

### 4.4 Errores (Problem Details)
- 400 VALIDATION_ERROR
- 403 BRANCH_SCOPE_MISMATCH
- 409 AGENDA_OVERLAP
- 404 APPOINTMENT_NOT_FOUND

### 4.5 Estados
- Estados canonicos: RESERVED / CONFIRMED / IN_ATTENTION / CLOSED / CANCELLED.
- En SPR-B006 solo se crea/edita en estado RESERVED. Transiciones (confirm/cancel/check-in/start/close) se dejan a SPR-B007.

## 5) Impacto
- Impacto tecnico: controllers/services/repos + migraciones de appointment + indices de overlap.
- Impacto UX: habilita agenda base para semana.
- Impacto seguridad: enforcement de AGENDA_OVERRIDE_OVERLAP con reason+auditoria.
- Impacto datos/migraciones: tabla appointment con branch_id, indices room_id/vet_user_id + startAt.

## 6) Plan de implementacion
1) Aceptar RFC.
2) Implementar endpoints/validaciones en SPR-B006.
3) Agregar overlap check y audit_event.
4) Actualizar RTM/status/log/state con evidencia.

## 7) Riesgos
- Sin contrato de overlap se rompe consistencia de agenda.
- Sin enforcement de reason se viola seguridad.

## 8) Archivos a cambiar (si se aprueba)
- docs/sprints/spr-b006.md (referenciar contrato, si aplica)
- src/main/java/com/saasveterinaria/agenda/**
- src/main/resources/db/migration/* (appointment + indices)
- docs/status/status.md, docs/log/log.md, docs/traceability/rtm.md, docs/state/state.md

<!-- EOF -->
