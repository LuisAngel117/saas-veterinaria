# Seguridad

## 1) Auth flows (v1)
### Login
- Input: username/email + password
- Si credenciales válidas y usuario NO requiere 2FA:
  - responde access JWT (1h) + refresh (7d)
- Si requiere 2FA:
  - estado intermedio (challenge) y se valida TOTP antes de emitir tokens

### Refresh
- Input: refresh token
- Output: nuevo access + nuevo refresh (rotación)
- Invalida refresh anterior

### Logout
- Invalida refresh token actual (server-side)

## 2) Password policy
- Longitud mínima: 10
- Reglas: mayúscula, minúscula, número, símbolo
- Hash seguro (BCrypt/Argon2; decisión de implementación en sprint de seguridad)

## 3) Lockout
- 4 intentos fallidos en ventana (definir ventana = 15 min)
- bloquea login por 15 min
- auditar lock/unlock

## 4) 2FA TOTP (ADMIN/SUPERADMIN)
- Enrolar:
  - generar secreto
  - mostrar QR otpauth://
  - validar primer código
- Reset:
  - requiere permiso admin/superadmin
  - reason required
  - auditar before/after (tenía 2FA, se reseteó)

## 5) Roles vs permisos (acción)
- Roles: SUPERADMIN, ADMIN, RECEPCION, VETERINARIO
- Permisos: códigos estables (ver `docs/10-permisos.md`)
- Evaluación:
  - backend: anotaciones/guards por permiso + branch-scoping
  - frontend: ocultar/deshabilitar acciones sin permiso

## 6) Acciones sensibles (reason required)
Mínimo:
- anular factura
- cambiar precio
- anular/borrar atención (preferir “anular”)
- editar historia clínica cerrada
- ajustes inventario manuales
- override solape de cita
- cambios configuración sensible (IVA default, feature flags)
Regla:
- reason obligatorio (string 10–500 chars)
- auditar before/after cuando aplique

## 7) Auditoría
- Debe registrar:
  - actor (userId)
  - rol
  - branchId (si aplica)
  - acción (código estable)
  - entidad afectada (tipo + id)
  - timestamp
  - reason (si aplica)
  - before/after (JSON) en sensibles
- Retención demo: 90 días (purga planificada)

## 8) Rate limit / defensa básica
- v1 local: enfoque en lockout por intentos fallidos.
- Rate limit per endpoint: TBD (si se implementa → RFC/ADR + sprint de hardening).

## 9) CORS
- Local: permitir `http://localhost:<frontPort>` hacia backend.
- Online/stage: restringir por dominio.

<!-- EOF -->
