# Branch 08 — Validation

**Day 2 · Spring MVC — request validation**

## Goal
Reject malformed requests at the edge, before any business logic runs.

## What changed vs 07
- Added `spring-boot-starter-validation` (Hibernate Validator).
- `CreateTaskRequest.title` now carries `@NotBlank` + `@Size(min=3, max=120)`.
- `TaskController.create` marks the body `@Valid`.

## Run & test
```bash
mvn spring-boot:run

# valid -> 200
curl -X POST http://localhost:8080/tasks -H "Content-Type: application/json" -d '{"title":"Valid task"}'

# invalid (blank) -> 400 Bad Request
curl -i -X POST http://localhost:8080/tasks -H "Content-Type: application/json" -d '{"title":""}'

# invalid (too short) -> 400
curl -i -X POST http://localhost:8080/tasks -H "Content-Type: application/json" -d '{"title":"ab"}'
```

## Concepts
- **Bean Validation (Jakarta)** — declarative constraints on the DTO.
- **`@Valid`** — tells Spring MVC to run those constraints; a violation throws
  `MethodArgumentNotValidException` → automatic **400**.
- Validation belongs on the **contract**, not buried in the service.

## ⚠️ Note
The default 400 body is generic. Branch 12 (`@ControllerAdvice`) makes error
responses clean and consistent.

## 🔵 Stretch (senior)
Add `@Future` to a new `dueDate` field, or write a custom constraint annotation.
Discuss where validation should NOT live (don't re-validate the same rule in 3 layers).

## Next
`09-jpa-h2` — start of the Spring Data block: real persistence.
