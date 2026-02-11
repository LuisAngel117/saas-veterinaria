# SPR-F004 - Acciones de cita (confirm/cancel/check-in/start/close)

**Estado:** BLOCKED (master FRONT en DRAFT; no ejecutar hasta aceptacion explicita del usuario)  
**Stage:** 4  
**Duracion objetivo:** 45-90 min (referencial)  
**Tipo:** Frontend  

## 1) Objetivo

Cerrar exactamente el alcance de SPR-F004 segun docs/sprints/spr-master-front.md, sin ampliar scope y manteniendo UX vendible en espanol.

- **Cierra (master):** BRD-REQ-008
- **Entrega (master):** botones por estado, confirmaciones, captura de motivos (cancel), auditoría reason cuando aplique (override).
- **Excluye (aplicado):** No especificado en master FRONT (aplica no ampliar scope).

**BRD-REQ objetivo (detalle):**
- BRD-REQ-008: Acciones de cita: confirmar/cancelar/check-in/iniciar atención/cerrar (transiciones válidas).

## 2) Cierre de alcance (sin inventar)

### Incluye
- botones por estado.
- confirmaciones.
- captura de motivos (cancel).
- auditoría reason cuando aplique (override).
- Solo lo definido por master FRONT + BRD + UX docs para este sprint.

### Excluye
- No especificado en master FRONT (aplica no ampliar scope).
- Cualquier funcionalidad fuera de Entrega del master FRONT.

## 3) Contexto tecnico alineado

**Pantallas/areas UI involucradas:**
- Acciones por estado en cita
- Dialogos confirmar/cancelar
- Check-in/start/close controls

**Permisos minimos (docs/10-permisos.md):**
- AGENDA_CONFIRM
- AGENDA_CANCEL
- AGENDA_CHECKIN
- AGENDA_START_SERVICE
- AGENDA_CLOSE
- AGENDA_OVERRIDE_OVERLAP

**Acciones sensibles (reason required):**
- AGENDA_OVERRIDE_OVERLAP (reason required)

**Dependencia backend real para integrar:**
- SPR-B007
- Validar contrato en docs/handoff/handoff-back-to-front.md antes de implementar.

## 4) Pre-check obligatorio (DoR)

- Verificar identidad repo (project-lock, remote, branch).
- Verificar existencia de este sprint: docs/sprints/spr-f004.md
- Confirmar aceptacion explicita de master FRONT antes de ejecutar implementacion.
- Verificar handoff backend->frontend actualizado (no-TBD) para endpoints y errores del sprint.
- Verificar reglas de scoping (X-Branch-Id), permisos y Problem Details del flujo.
- Si falta contrato backend/handoff/permisos: abrir RFC y dejar BLOCKED.

## 5) Entregables esperados

### Frontend
- botones por estado.
- confirmaciones.
- captura de motivos (cancel).
- auditoría reason cuando aplique (override).
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
- [ ] Implementado: botones por estado.
- [ ] Implementado: confirmaciones.
- [ ] Implementado: captura de motivos (cancel).
- [ ] Implementado: auditoría reason cuando aplique (override).
- [ ] BRD-REQ objetivo cubierto: BRD-REQ-008
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

2) Validar flujo principal de SPR-F004
- Botones visibles segun estado y permiso.
- Cancelacion requiere motivo cuando corresponda.
- Check-in se refleja como evento sin crear estado nuevo.
- Override solicita reason y muestra feedback de auditoria.

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
- **Sprint previo de continuidad:** SPR-F003
- **Backend minimo para integrar (RTM):** SPR-B007

## 10) Regla anti-desvio

- No inventar endpoints, payloads, permisos o transiciones fuera de master/BRD/handoff/docs canonicos.
- Si el contrato backend cambia: RFC/ADR + changelog antes de continuar.
- Si bloquea implementacion (handoff/TBD): mantener BLOCKED y documentar motivo.

<!-- EOF -->