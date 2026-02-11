# Bitácora (append-only)

Formato por entrada:
- Fecha/hora (America/Guayaquil)
- Sprint/Tanda
- Qué cambió (bullets)
- Comandos ejecutados
- Output (pegar aquí)
- Resultado

## 2026-02-11T00:00:00-05:00 (America/Guayaquil)
- Sprint/Tanda: T1
- Qué cambió:
  - Bootstrap estructura docs/scripts (placeholders canónicos)
  - Se agregó verificador EOF y preflight
- Comandos ejecutados:
  - TBD (lo llenará Codex al aplicar)
- Output:
  - PEGAR OUTPUT AQUÍ
- Resultado:
  - IN_PROGRESS (se marcará READY_FOR_VALIDATION al aplicar y validar scripts)

## 2026-02-11T00:15:00-05:00 (America/Guayaquil)
- Sprint/Tanda: T2
- Qué cambió:
  - Se completó Brief (visión, alcance, no-objetivos, glosario)
  - Se definió BRD con IDs `BRD-REQ-###` y reglas críticas
  - Se definió arquitectura (scoping, errores, datos, adjuntos, agenda, inventario, facturación)
  - Se definió seguridad (auth/2FA/lockout/auditoría)
  - Se definió dominio (entidades + invariantes + seeds demo)
  - Se definió UX/UI (pantallas, flujos, permisos, validaciones)
  - Se definió runbook objetivo (Windows/Linux) y stage-release
  - Se definió matriz de permisos
  - Se creó RTM inicial y state snapshot actualizado
  - Se crearon masters BACK/FRONT en DRAFT (a congelar luego)
- Comandos ejecutados:
  - pwsh -ExecutionPolicy Bypass -File .\scripts\verify\verify-docs-eof.ps1
  - pwsh -ExecutionPolicy Bypass -File .\scripts\verify\preflight.ps1
- Output:
  - PEGAR OUTPUT AQUÍ
- Resultado:
  - READY_FOR_VALIDATION (al finalizar la aplicación y pasar scripts)

## 2026-02-11T00:30:00-05:00 (America/Guayaquil)
- Sprint/Tanda: T3
- Qué cambió:
  - Se definió índice canónico (orden de lectura y reglas)
  - Se definieron Quality Gates DoR/DoD
  - Se añadieron ADRs (stack/arquitectura/scoping/seguridad/auditoría/ux/walking-skeleton)
  - Se añadió plantilla RFC
  - Se añadió AGENTS.md (conducta del agente)
  - Se actualizó project-lock (repo/path reales)
  - Se corrigió coherencia agenda: estados exactos + check-in separado (sin estado extra)
- Comandos ejecutados:
  - pwsh -ExecutionPolicy Bypass -File .\scripts\verify\verify-docs-eof.ps1
  - pwsh -ExecutionPolicy Bypass -File .\scripts\verify\preflight.ps1
- Output:
  - PEGAR OUTPUT AQUÍ
- Resultado:
  - READY_FOR_VALIDATION

## 2026-02-11T00:40:00-05:00 (America/Guayaquil)
- Sprint/Tanda: T4
- Qué cambió:
  - BRD con BRD-REQ estables
  - RTM inicial mapeada a sprints propuestos
  - State snapshot actualizado
  - Masters BACK/FRONT en DRAFT listos para aceptación
- Comandos ejecutados:
  - pwsh -ExecutionPolicy Bypass -File .\scripts\verify\verify-docs-eof.ps1
  - pwsh -ExecutionPolicy Bypass -File .\scripts\verify\preflight.ps1
- Output:
  - PEGAR OUTPUT AQUÍ
- Resultado:
  - READY_FOR_VALIDATION

## 2026-02-11T08:21:55-05:00 (America/Guayaquil)
- Sprint/Tanda: AUDIT-BACK-001
- Que cambio:
  - Se genero resumen tecnico completo del plan maestro BACK.
  - Se auditaron huecos e inconsistencias con base exclusiva en .md del repo.
  - Se actualizo status y changelog para la tanda de auditoria.
- Comandos ejecutados:
  - git status --porcelain
  - git config user.name; git config user.email
  - git remote -v
  - git rev-parse --abbrev-ref HEAD
  - pwsh -ExecutionPolicy Bypass -File .\\scripts\\verify\\verify-docs-eof.ps1
  - pwsh -ExecutionPolicy Bypass -File .\\scripts\\verify\\preflight.ps1
  - lecturas obligatorias de docs + ADRs + RFCs
- Output:
  - PEGAR OUTPUT AQUÍ
- Resultado:
  - READY_FOR_VALIDATION

## 2026-02-11T08:34:03-05:00 (America/Guayaquil)
- Sprint/Tanda: AUDIT-FRONT-001
- Que cambio:
  - Se genero resumen tecnico completo del plan maestro FRONT.
  - Se auditaron huecos e inconsistencias usando solo .md del repo.
  - Se actualizo status y changelog para la tanda de auditoria FRONT.
- Comandos ejecutados:
  - git status --porcelain
  - git config user.name; git config user.email
  - git remote -v
  - git rev-parse --abbrev-ref HEAD
  - pwsh -ExecutionPolicy Bypass -File .\\scripts\\verify\\verify-docs-eof.ps1
  - pwsh -ExecutionPolicy Bypass -File .\\scripts\\verify\\preflight.ps1
  - lecturas obligatorias de docs + ADRs + RFCs
- Output:
  - PEGAR OUTPUT AQUÍ
- Resultado:
  - READY_FOR_VALIDATION

## 2026-02-11T09:00:00-05:00 (America/Guayaquil)
- Sprint/Tanda: T5
- Que cambio:
  - Se crearon RFC-0002 y RFC-0003 para formalizar normalizaciones contractuales.
  - Se normalizaron estados de agenda a IN_ATTENTION y de factura a ANNULLED.
  - Se consolidó project-lock en `docs/project-lock.md` y se eliminó `project-lock.md` de raíz.
  - Se registró aceptación del plan maestro BACK en `docs/sprints/spr-master-back.md` y `docs/state/state.md`.
  - Se actualizó changelog y status para T5.
- Comandos ejecutados:
  - git status --porcelain
  - git config user.name; git config user.email
  - git remote -v
  - git rev-parse --abbrev-ref HEAD
  - pwsh -ExecutionPolicy Bypass -File .\\scripts\\verify\\verify-docs-eof.ps1
  - pwsh -ExecutionPolicy Bypass -File .\\scripts\\verify\\preflight.ps1
  - rg -n "IN_SERVICE|\\bVOID\\b|ANNULED|project-lock\\.md" AGENTS.md docs/00-indice.md docs/project-lock.md docs/02-brd.md docs/06-dominio-parte-a.md docs/06-dominio-parte-b.md docs/10-permisos.md docs/sprints/spr-master-back.md docs/state/state.md docs/traceability/rtm.md docs/changelog.md docs/status/status.md docs/log/log.md docs/rfcs/rfc-0002-normalizacion-estados-agenda-y-factura.md docs/rfcs/rfc-0003-normalizacion-project-lock-path.md
- Output:
  - PEGAR OUTPUT AQUÍ
- Resultado:
  - READY_FOR_VALIDATION

<!-- EOF -->
