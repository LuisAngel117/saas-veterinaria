# SPR-F003 - Agenda semana + crear/editar cita (sin solape)

**Estado:** BLOCKED (master FRONT en DRAFT; no ejecutar hasta aceptacion explicita del usuario)  
**Stage:** 3  
**Duracion objetivo:** 45-90 min (referencial)  
**Tipo:** Frontend  

## 1) Objetivo

Cerrar exactamente el alcance de SPR-F003 segun docs/sprints/spr-master-front.md, sin ampliar scope y manteniendo UX vendible en espanol.

- **Cierra (master):** BRD-REQ-007, BRD-REQ-025
- **Entrega (master):** vista semanal, formulario crear/editar, validaciones, manejo 409 (conflicto) con UX clara.
- **Excluye (aplicado):** No especificado en master FRONT (aplica no ampliar scope).

**BRD-REQ objetivo (detalle):**
- BRD-REQ-007: Agenda: CRUD de citas + vista semana + no-solape por Sala+Veterinario + override (permiso+reason+audit).
- BRD-REQ-025: UX vendible: permisos visibles, empty states, validación de formularios, mensajes claros.

## 2) Cierre de alcance (sin inventar)

### Incluye
- vista semanal.
- formulario crear/editar.
- validaciones.
- manejo 409 (conflicto) con UX clara.
- Solo lo definido por master FRONT + BRD + UX docs para este sprint.

### Excluye
- No especificado en master FRONT (aplica no ampliar scope).
- Cualquier funcionalidad fuera de Entrega del master FRONT.

## 3) Contexto tecnico alineado

**Pantallas/areas UI involucradas:**
- Agenda semanal
- Formulario crear/editar cita
- Feedback conflicto 409

**Permisos minimos (docs/10-permisos.md):**
- AGENDA_VIEW
- AGENDA_CREATE
- AGENDA_EDIT

**Acciones sensibles (reason required):**
- Sin nuevas acciones sensibles en este sprint (mantener reglas globales vigentes).

**Dependencia backend real para integrar:**
- SPR-B006
- Validar contrato en docs/handoff/handoff-back-to-front.md antes de implementar.

## 4) Pre-check obligatorio (DoR)

- Verificar identidad repo (project-lock, remote, branch).
- Verificar existencia de este sprint: docs/sprints/spr-f003.md
- Confirmar aceptacion explicita de master FRONT antes de ejecutar implementacion.
- Verificar handoff backend->frontend actualizado (no-TBD) para endpoints y errores del sprint.
- Verificar reglas de scoping (X-Branch-Id), permisos y Problem Details del flujo.
- Si falta contrato backend/handoff/permisos: abrir RFC y dejar BLOCKED.

## 5) Entregables esperados

### Frontend
- vista semanal.
- formulario crear/editar.
- validaciones.
- manejo 409 (conflicto) con UX clara.
- Guardas de rutas/acciones por permiso y branch cuando aplique.
- Mensajeria UX en espanol con errores claros (incluye Problem Details).

### Calidad/validacion
- Casos positivos y negativos por cada item de Entrega.
- Validar estados de carga, vacio, error y no autorizado.

### Trazabilidad/documentacion
- Actualizar docs/log/log.md (append-only) con comandos y output al ejecutar sprint.
- Actualizar docs/status/status.md a READY_FOR_VALIDATION al cierre (nunca DONE por Codex).
- Actualizar docs/traceability/rtm.md para BRD-REQ del sprint con evidencia.
- Actualizar docs/state/state.md con snapshot y siguiente sprint.

## 6) Criterios de aceptacion (AC)

- [ ] Scope del sprint coincide exactamente con Entrega del master FRONT.
- [ ] Implementado: vista semanal.
- [ ] Implementado: formulario crear/editar.
- [ ] Implementado: validaciones.
- [ ] Implementado: manejo 409 (conflicto) con UX clara.
- [ ] BRD-REQ objetivo cubierto: BRD-REQ-007, BRD-REQ-025
- [ ] Scoping de branch aplicado en cliente API para flujos branch-scoped.
- [ ] Permisos UI aplicados (ocultar/deshabilitar/proteger ruta segun corresponda).
- [ ] Errores Problem Details mapeados a mensajes utiles en espanol.
- [ ] DoR validado antes de iniciar.
- [ ] DoD validado antes de READY_FOR_VALIDATION.

## 7) Smoke manual sugerido (usuario)

1) Levantar frontend local
- npm ci
- npm run build
- npm run dev

2) Validar flujo principal de SPR-F003
- Crear cita valida desde calendario semanal.
- Intentar solape y mostrar mensaje de conflicto 409 comprensible.
- Editar cita respetando branch activo y validaciones de fecha/hora.

3) Evidencia
- Pegar outputs y evidencia en docs/log/log.md bajo la entrada del sprint.

## 8) Comandos verdad

Frontend:
- npm ci
- npm run build
- npm run dev

Documentacion:
- pwsh -ExecutionPolicy Bypass -File .\scripts\verify\verify-docs-eof.ps1
- pwsh -ExecutionPolicy Bypass -File .\scripts\verify\preflight.ps1

## 9) Dependencias y continuidad

- **Dependencias (master FRONT):** No listadas en master FRONT.
- **Sprint previo de continuidad:** SPR-F002
- **Backend minimo para integrar (RTM):** SPR-B006

## 10) Regla anti-desvio

- No inventar endpoints, payloads, permisos o transiciones fuera de master/BRD/handoff/docs canonicos.
- Si el contrato backend cambia: RFC/ADR + changelog antes de continuar.
- Si bloquea implementacion (handoff/TBD): mantener BLOCKED y documentar motivo.

<!-- EOF -->