# Branch 10 — Service Layer

**Day 2 · Spring Data — layered architecture & transactions**

## Goal
Make the three layers explicit and give the service real responsibilities:
business rules and transaction boundaries.

```
Controller (HTTP)  ->  Service (rules + @Transactional)  ->  Repository (data)
```

## What changed vs 09
- `TaskService` is `@Transactional(readOnly = true)` by default; writes
  (`createTask`, `completeTask`) override with a read/write transaction.
- New business operations: `getById(id)` and `completeTask(id)`.
- New endpoints: `GET /tasks/{id}` and `POST /tasks/{id}/complete`.

## Run & test
```bash
mvn spring-boot:run

curl -X POST http://localhost:8080/tasks -H "Content-Type: application/json" -d '{"title":"Finish slides"}'
curl -X POST http://localhost:8080/tasks/1/complete
curl http://localhost:8080/tasks/1
# missing id -> HTTP 500 for now (fixed in branch 12)
curl -i http://localhost:8080/tasks/999
```

## Concepts
- **Layered architecture** — each layer has one job; dependencies point inward.
- **`@Transactional`** — the service method is the transaction boundary; commit
  on success, rollback on a runtime exception.
- **Dirty checking** — inside a transaction, modifying a managed entity persists
  on commit without an explicit save.

## ⚠️ Note
`GET /tasks/999` returns an ugly 500 with a stack-traced error. That's the hook
for branch 12.

## 🔵 Stretch (senior)
Throw a `RuntimeException` mid-transaction after a `save` and show the rollback.
Then switch it to a checked exception and observe that rollback does NOT happen
by default — discuss `@Transactional(rollbackFor = ...)`.

## Next
`11-pagination-search` — finish the Spring Data block.
