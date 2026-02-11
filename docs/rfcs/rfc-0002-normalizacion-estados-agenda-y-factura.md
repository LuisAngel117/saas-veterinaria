# RFC-0002 — Normalizacion de estados de agenda y factura

## 1) Problema
Se detecto inconsistencia contractual entre documentos:
- Agenda: coexistian `IN_SERVICE` y `IN_ATTENTION`.
- Factura: coexistian `VOID`, `ANNULED` y acciones `ANNUL`.

Estas diferencias bloquean implementacion y validacion sin suposiciones.

## 2) Opcion elegida (decision canonica)
- `appointment.status` canonico:
  - `RESERVED / CONFIRMED / IN_ATTENTION / CLOSED / CANCELLED`
  - Se elimina `IN_SERVICE`.
- `invoice.status` canonico:
  - `PENDING / PAID / ANNULLED`
  - Se elimina `VOID`.
- Verbo y permisos de anulación:
  - Se mantiene `ANNUL / ANULAR` en acciones/permisos.

## 3) Impacto (archivos actualizados exactamente)
- `docs/02-brd.md`
- `docs/06-dominio-parte-a.md`
- `docs/06-dominio-parte-b.md`
- `docs/10-permisos.md`
- `docs/sprints/spr-master-back.md`

## 4) Plan de aplicacion
1. Reemplazar `IN_SERVICE` por `IN_ATTENTION` donde aplique al contrato de cita.
2. Reemplazar `VOID` y `ANNULED` por `ANNULLED` donde aplique al contrato de factura.
3. Mantener acciones/permisos `INVOICE_ANNUL` y terminologia "anular" coherente.
4. Verificar consistencia documental con scripts de verificacion.

## 5) Verificacion
- `pwsh -ExecutionPolicy Bypass -File .\scripts\verify\verify-docs-eof.ps1`
- `pwsh -ExecutionPolicy Bypass -File .\scripts\verify\preflight.ps1`

<!-- EOF -->
