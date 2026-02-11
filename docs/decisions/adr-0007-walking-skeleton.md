# ADR-0007 — Walking Skeleton temprano

## Contexto
Para evitar construir por capas sin integración, se requiere un flujo mínimo end-to-end temprano.

## Decisión
Implementar un “walking skeleton” temprano en BACK y FRONT:
- BACK: health + auth + scoping + seeds + 1 endpoint branch-scoped real
- FRONT: login + selector branch + home mínima integrada
- Smoke script que pruebe el flujo mínimo

## Consecuencias
- Reduce riesgo de desalineación.
- Exige disciplina: cada sprint debe ser integrado o justificar “foundation”.

## Alternativas descartadas
- Construir dominio completo antes de integrar (alto riesgo).

## Fecha
2026-02-11

<!-- EOF -->
