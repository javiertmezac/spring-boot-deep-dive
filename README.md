# Branch 07 — Request/Response DTOs

**Day 2 · Spring MVC — API contracts**

## Goal
Give the API an explicit, stable contract. Stop accepting loose maps and stop
leaking the internal domain object.

## What changed vs 06
- New `dto` package with two records:
  - `CreateTaskRequest(title)` — the input contract.
  - `TaskResponse(id, title, completed)` — the output contract, built via
    `TaskResponse.from(Task)`.
- `TaskController` now consumes `CreateTaskRequest` and returns `TaskResponse`.
  The domain `Task` no longer appears in any method signature.

## Run
```bash
mvn spring-boot:run
curl -X POST http://localhost:8080/tasks -H "Content-Type: application/json" -d '{"title":"With a real DTO"}'
```
Behaviorally identical to branch 06 — the win is structural.

## Concepts
- **API contract** — clients couple to the DTO, not your internals.
- **Separation of concerns** — domain/persistence shape ≠ wire shape. You can
  add a DB column or rename a field without breaking clients.
- **Records** as DTOs — immutable, minimal boilerplate, great Jackson support.

## 🔵 Stretch (senior)
Add a field to `Task` (e.g. `createdBy`) and confirm the API response is
unchanged because `TaskResponse` didn't expose it. That decoupling is the point.

## Next
`08-validation` — reject bad requests before they reach the service.
