# RFC-0003 — Normalizacion de ruta canonica para project-lock

## 1) Problema
La gobernanza referenciaba `docs/project-lock.md`, pero el lock activo estaba en `project-lock.md` en la raiz del repo. Esto generaba ambiguedad de fuente de verdad.

## 2) Decision
- Ruta canonica del lock: `docs/project-lock.md`.
- No se permiten duplicados del lock en la raiz.
- Si existe `project-lock.md` en raiz, se consolida su contenido en `docs/project-lock.md` y se elimina el archivo raiz.

## 3) Impacto (archivos actualizados exactamente)
- `docs/project-lock.md`
- `project-lock.md` (raiz, eliminado)
- `docs/00-indice.md`
- `AGENTS.md`

## 4) Plan de aplicacion
1. Consolidar lock en `docs/project-lock.md`.
2. Verificar campos requeridos por plantilla:
   - `project_name`
   - `project_domain`
   - `repo_url`
   - `local_path`
   - `primary_branch`
   - `timezone`
   - `ui_language`
   - `code_language`
   - `created_at`
3. Eliminar `project-lock.md` de raiz para evitar duplicidad.
4. Confirmar que `AGENTS.md` y `docs/00-indice.md` apuntan a `docs/project-lock.md`.
5. Ejecutar validaciones de docs y preflight.

## 5) Verificacion
- `pwsh -ExecutionPolicy Bypass -File .\scripts\verify\verify-docs-eof.ps1`
- `pwsh -ExecutionPolicy Bypass -File .\scripts\verify\preflight.ps1`

<!-- EOF -->
