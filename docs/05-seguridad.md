# Seguridad

## Auth flows
- Login con usuario/password.
- Access token JWT (duracion objetivo: 1h).
- Refresh token (duracion objetivo: 7d) con rotacion.
- Logout invalida refresh activo.

## Password policy
- Minimo 10 caracteres.
- Debe incluir mayuscula, minuscula, numero y simbolo.
- No se permite reutilizacion inmediata (detalle de historial: pendiente de implementacion).

## 2FA TOTP
- Requerido para `ADMIN` y `SUPERADMIN`.
- Basado en RFC 6238.
- Debe existir flujo de alta, verificacion y recuperacion controlada.

## Roles vs permisos
- Roles canonicos: `SUPERADMIN`, `ADMIN`, `RECEPCION`, `VETERINARIO`.
- La autorizacion opera por permisos (no por nombre de pantalla).
- UI debe reflejar permisos (ocultar/deshabilitar con explicacion).

## Acciones sensibles (reason required)
Incluye como minimo:
- Anulacion/cambio de factura.
- Cambio de precio.
- Reapertura de HC cerrada.
- Override de bloqueo por inventario.
- Override de no-solape en agenda.

## Auditoria
- Obligatoria para auth, usuarios/roles, agenda, clientes/mascotas, HC, facturacion, inventario y configuracion sensible.
- Debe registrar actor, timestamp, entidad, accion, reason y before/after cuando aplique.
- Retencion demo: 90 dias.

## Rate limit / lockout
- Lockout: 4 intentos fallidos -> 15 minutos.
- Rate limit detallado por endpoint: pendiente de parametrizacion en implementacion.

## CORS
- Permitir origenes locales de frontend durante operacion local.
- Denegar origenes no listados en configuracion.

<!-- EOF -->
