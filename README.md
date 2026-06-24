# Branch 12 — Global Exception Handler

**Day 2 · Spring MVC — consistent error responses**

## Goal
Replace ad-hoc 500s with one consistent, well-shaped error response for the
whole API.

## What changed vs 11
- New `exception` package: `TaskNotFoundException` (domain meaning, not HTTP).
- `GlobalExceptionHandler` (`@RestControllerAdvice`) maps:
  - `TaskNotFoundException` → **404** with an `ApiError` body.
  - `MethodArgumentNotValidException` → **400** with per-field messages.
- New `ApiError` record = the single error contract.
- `TaskService.getById` now throws `TaskNotFoundException`.

## Run & test
```bash
mvn spring-boot:run

# 404 with a clean body (was an ugly 500 in branch 10/11)
curl -i http://localhost:8080/tasks/999

# 400 with field details
curl -i -X POST http://localhost:8080/tasks -H "Content-Type: application/json" -d '{"title":"ab"}'
```
404 body:
```json
{"status":404,"error":"Not Found","message":"Task not found: 999","details":[]}
```

## Concepts
- **`@ControllerAdvice` / `@RestControllerAdvice`** — centralized, cross-cutting
  exception handling.
- **`@ExceptionHandler`** — exception type → HTTP response mapping.
- Keep exceptions **domain-meaningful**; let the advice translate to HTTP.

## 🔵 Stretch (senior)
Compare this to extending `ResponseEntityExceptionHandler`, and to Spring 6's
`ProblemDetail` (RFC 7807). Discuss when a global handler beats per-controller handling.

## Next
`13-security-basic-auth` — lock the API down.
