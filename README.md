# Branch 01 — Java Without Spring

**Day 1 · IoC & DI (the problem)**

## Goal
Feel the pain Spring exists to solve. There is **no Spring here** — `main()`
constructs everything by hand.

## What's here
- `EmailNotificationService` — plain class.
- `TaskService` — creates its own `EmailNotificationService` with `new`.
- `TaskflowApplication` — `main()` does `new TaskService()` and calls it. No
  `@SpringBootApplication`, no container.

## Run
```bash
mvn compile exec:java -Dexec.mainClass=com.itj.bootcamp.taskflow.TaskflowApplication
```
Expected:
```
Creating task: Write bootcamp materials
[EMAIL] to=user@taskflow.dev : Task created: Write bootcamp materials
...
```

## 🏋️ Exercise — switch Email to SMS
Add an `SmsNotificationService` and make `TaskService` use it instead.

Notice what you had to do: **edit `TaskService`'s source code.** `TaskService`
is *tightly coupled* to a concrete class and is responsible for *creating* its
own dependency. Every wiring decision lives inside the class that should only
care about business logic.

## Concepts
- Tight coupling
- Manual object creation / wiring
- No inversion: the class controls its own dependencies

## Next
`02-first-spring-context` — hand object creation over to the Spring container.
