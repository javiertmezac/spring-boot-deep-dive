# Branch 02 — First Spring Context

**Day 1 · IoC, ApplicationContext, Beans**

## Goal
Hand object creation and wiring to the Spring container. Compare this diff to
branch 01 — the business logic barely changed; *who builds the objects* did.

## What changed vs 01
- `EmailNotificationService` → `@Component` (a managed bean).
- `TaskService` → `@Service`, dependency now arrives via the **constructor**
  instead of `new`.
- `TaskflowApplication` → `@SpringBootApplication` returns, and we run work
  through a `@Bean CommandLineRunner`. No manual wiring anywhere.

## Run
```bash
mvn spring-boot:run
```
You'll see the task output **and** a line like `Beans managed by the context: N`.

## Concepts
- **IoC (Inversion of Control)** — you no longer create dependencies; the
  container does, and gives them to you.
- **ApplicationContext** — the container. `run()` returns it. It holds every bean.
- **Bean** — an object the container manages (created via `@Component` scanning
  or a `@Bean` method).
- **Constructor injection** — the preferred way to receive dependencies
  (immutable, testable, no hidden `@Autowired` field magic).

## 🔵 Stretch (senior)
`@Component` vs `@Service` vs `@Repository` — functionally all beans. Why do the
specialized stereotypes exist? (Hint: intent + `@Repository`'s exception
translation, which we'll meet on Day 2.)

## Next
`03-dependency-injection` — introduce an interface with two implementations and
let Spring choose between them.
