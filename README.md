# Branch 06 — REST API

**Day 2 · Spring MVC**

## Goal
Expose tasks over HTTP. Meet the `DispatcherServlet`, request mapping, and
automatic JSON serialization.

## What changed vs 05
- `spring-boot-starter` → **`spring-boot-starter-web`** (embedded Tomcat + Spring
  MVC + Jackson). The app is now a web server and stays running.
- New `TaskController` (`@RestController`) with `GET /tasks` and `POST /tasks`.
- Tasks are still stored in memory by `TaskService` (seeded at startup).

## Run
```bash
mvn spring-boot:run
```

```bash
# list
curl http://localhost:8080/tasks

# create
curl -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Demo REST"}'
```

## Concepts
- **Embedded container** — no WAR, no external Tomcat; the server is *in* the app.
- **`DispatcherServlet`** — the front controller routing every request to a handler.
- **`@RestController` / `@RequestMapping` / `@GetMapping` / `@PostMapping`**.
- **JSON serialization** — Jackson turns `Task` ↔ JSON automatically.

## ⚠️ Smell to call out
`POST` takes a `Map<String, Object>` — no type, no contract, no validation. We're
also returning the **domain object** straight to clients. Both are fixed next.

## 🔵 Stretch (senior)
Hit a bad URL and inspect the default error JSON. Where did `/error` come from?
(Foreshadows auto-configuration: `BasicErrorController`.)

## Next
`07-request-response-dtos` — replace the loose Map and stop leaking entities.
