# ADR-0002 — Arquitectura (monolito modular)

## Contexto
Se requiere velocidad de entrega, coherencia y trazabilidad, evitando microservicios y capas desconectadas.

## Decisión
Usar monolito modular:
- módulos claros (auth, branch, agenda, crm, clinic, catalog, inventory, billing, reports, audit)
- reglas de negocio en dominio/aplicación
- persistencia JPA por módulo
- API REST `/api/v1` con Problem Details

## Consecuencias
- Integración más simple y demo más rápida.
- Necesita gobernanza fuerte para no degenerar en “big ball of mud”.

## Alternativas descartadas
- Microservicios (sobre-complejidad para v1 demo).
- Backend “solo CRUD” sin invariantes (riesgo de bugs).

## Fecha
2026-02-11

<!-- EOF -->
