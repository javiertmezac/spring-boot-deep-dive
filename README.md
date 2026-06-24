# Branch 11 — Pagination & Search

**Day 2 · Spring Data — query derivation & pagination**

## Goal
Don't return unbounded lists. Page results and add search — using a method name
instead of SQL.

## What changed vs 10
- `TaskRepository.findByTitleContainingIgnoreCase(String, Pageable)` — a
  **derived query**: Spring Data writes the SQL from the method name.
- `TaskService.search(q, pageable)` returns `Page<Task>`.
- `GET /tasks` now takes `?page=&size=&sort=` (resolved into a `Pageable`
  automatically) plus an optional `?q=` filter, and returns a page.
- Startup seeds 5 tasks for a visible demo.

## Run & test
```bash
mvn spring-boot:run

# first page of 2
curl "http://localhost:8080/tasks?page=0&size=2"
# sorted by title desc
curl "http://localhost:8080/tasks?sort=title,desc"
# search
curl "http://localhost:8080/tasks?q=write"
```
The response includes `content`, `totalElements`, `totalPages`, `number`, `size`.

## Concepts
- **Query derivation** — `findBy<Field><Op>` parsed into a query; no `@Query`.
- **`Pageable` / `Page<T>`** — limit/offset + total count + sort, resolved from
  request params by Spring Data web support.
- Where derivation stops and `@Query` / Specifications begin.

## 🔵 Stretch (senior)
Add `findByCompleted(boolean, Pageable)` and an endpoint filter. Then enable
`logging.level.org.hibernate.SQL=DEBUG` and compare the generated SQL for a
derived query vs a hand-written `@Query`.

## Next
`12-global-exception-handler` — turn that ugly 500 into a clean 404.
