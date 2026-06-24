# Branch 04 — CommandLineRunner & Startup Lifecycle

**Day 1 · Bean lifecycle & initialization**

## Goal
Understand *when* things happen at startup, and the standard hook for running
code once the application is ready.

## What changed vs 03
- New `Task` domain object (in-memory for now).
- `TaskService` holds an in-memory list and exposes `createTask` / `findAll`,
  plus a `@PostConstruct init()` to show the init hook.
- `CommandLineRunner` seeds three tasks at startup and prints them.

## Run
```bash
mvn spring-boot:run
```
Watch the **order** of the output:
```
>> TaskService bean initialized (@PostConstruct)   <- during context startup
...Spring "Started TaskflowApplication" log line...
>> CommandLineRunner: seeding tasks                <- after context is ready
>> Current tasks:
   Task{id=1, ...}
```

## Concepts
- **Bean initialization** — `@PostConstruct` runs after injection, before the
  app is "ready".
- **`CommandLineRunner` / `ApplicationRunner`** — run *after* the context is
  fully started; ideal for seeding/bootstrapping. Order multiple runners with
  `@Order`.
- **Startup lifecycle** — construct beans → inject → `@PostConstruct` →
  context refreshed → runners.

## 🔵 Stretch (senior)
Add a second `CommandLineRunner` and use `@Order(1)` / `@Order(2)` to control
which runs first. Then add `implements ApplicationListener<ApplicationReadyEvent>`
to a bean and see where *that* fires relative to the runners.

## Next
`05-properties-and-profiles` — externalize configuration and switch behavior per
environment. (Last Day 1 topic.)
