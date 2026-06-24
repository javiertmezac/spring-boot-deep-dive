# Branch 09 — JPA + H2

**Day 2 · Spring Data (persistence)**

## Goal
Replace the in-memory list with a real database and the repository pattern —
without writing any SQL or any DAO implementation.

## What changed vs 08
- Added `spring-boot-starter-data-jpa` + `h2` (in-memory DB).
- `Task` is now a JPA `@Entity` (`@Id @GeneratedValue`); the **DB** assigns ids.
- New `TaskRepository extends JpaRepository<Task, Long>` — no implementation.
- `TaskService` saves/loads through the repository; the `ArrayList`/`AtomicLong`
  are gone.
- `application.properties` configures H2, SQL logging, and the H2 console.

## Run
```bash
mvn spring-boot:run
```
- Watch the console: Hibernate logs the `insert`/`select` SQL it generates.
- Open `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:mem:taskflow`,
  user `sa`) and run `SELECT * FROM TASKS;`.

```bash
curl http://localhost:8080/tasks
curl -X POST http://localhost:8080/tasks -H "Content-Type: application/json" -d '{"title":"Persisted task"}'
```

## Concepts
- **ORM** — `Task` ↔ `TASKS` table mapping via annotations.
- **Repository pattern** — `JpaRepository` gives CRUD + paging for free; the bean
  is a runtime-generated proxy.
- **Auto-configuration teaser** — adding the JPA starter auto-configured a
  `DataSource`, `EntityManagerFactory`, and `TransactionManager`. We dig into
  *how* on Day 3.

## 🔵 Stretch (senior)
Set `spring.jpa.hibernate.ddl-auto=create-drop` vs `update` and discuss why you'd
never let Hibernate manage schema in production (→ Flyway/Liquibase / Ratchet).

## Next
`10-service-layer` — formalize the layering and add real business logic + transactions.
