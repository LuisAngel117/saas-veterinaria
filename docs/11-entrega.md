# Entrega (checklist vendible)

## 1) Checklist demo local (2–3 min)
- [ ] Login como RECEPCION (demo)
- [ ] Selector sucursal
- [ ] Crear cita (sala+vet, 30m, sin solape)
- [ ] Check-in
- [ ] Login como VETERINARIO
- [ ] Iniciar atención (desde cita)
- [ ] Completar SOAP mínimo
- [ ] Agregar servicio (con BOM) → consumo inventario
- [ ] Cerrar atención
- [ ] Login como RECEPCION/ADMIN
- [ ] Generar factura desde atención
- [ ] Aplicar descuento (opcional)
- [ ] Registrar pago parcial/mixto
- [ ] Ver reporte “ventas por período” + export

## 2) Checklist seguridad
- [ ] Lockout funciona (4 intentos → 15 min)
- [ ] Refresh rotación funciona
- [ ] 2FA para ADMIN/SUPERADMIN funciona
- [ ] Scoping: endpoint branch-scoped exige X-Branch-Id y valida contra JWT
- [ ] Acciones sensibles exigen reason y quedan auditadas

## 3) Checklist RC (local)
- [ ] `scripts/verify/verify-docs-eof.ps1` OK
- [ ] Backend: `./mvnw test` OK
- [ ] Backend: `./mvnw spring-boot:run` OK
- [ ] Frontend: `npm run build` OK
- [ ] Frontend: `npm run dev` OK
- [ ] Smoke scripts core OK

## 4) Qué NO incluye (v1)
- [ ] E-factura SRI real (solo placeholder)
- [ ] Recordatorios externos reales (solo cola “pendiente de enviar”)
- [ ] Multi-tenant real
- [ ] Auto-reserva por clientes
- [ ] Inventario por lotes/caducidad
- [ ] Horarios por sucursal

## 5) Alineación con DoD (anti “piezas sueltas”)
- [ ] Cada sprint entregó incremento integrado
- [ ] RTM cubre BRD-REQ P0 con evidencia
- [ ] LOG/STATUS completos y consistentes

<!-- EOF -->
