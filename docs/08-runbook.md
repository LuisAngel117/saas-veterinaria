# Runbook (operacion local)

## Prerequisitos
- Git.
- PowerShell 7+.
- Stack objetivo para implementacion: Java 21, Node.js LTS, Postgres 17.

## DB local (Postgres 17)
Estado actual: pendiente de scripts operativos en repo.
- Regla: cuando se agreguen scripts reales, este documento y `docs/state/state.md` deben actualizarse en la misma tanda.

## Levantar backend
Estado actual: pendiente de implementacion de backend.
- No existen comandos canonicos en este repo aun.

## Levantar frontend
Estado actual: pendiente de implementacion de frontend.
- No existen comandos canonicos en este repo aun.

## Troubleshooting
- Si falla EOF: ejecutar `powershell -ExecutionPolicy Bypass -File scripts/verify/verify-docs-eof.ps1`.
- Si hay dudas de entorno/repositorio: ejecutar `powershell -ExecutionPolicy Bypass -File scripts/verify/preflight.ps1`.
- Si `git status --porcelain` no esta limpio, detener tanda y resolver antes de continuar.

## Scripts "verdad"
- `scripts/verify/verify-docs-eof.ps1`
- `scripts/verify/preflight.ps1`

<!-- EOF -->
