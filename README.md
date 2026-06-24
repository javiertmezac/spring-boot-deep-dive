# Branch 03 — Dependency Injection

**Day 1 · Polymorphism, bean resolution, constructor injection**

## Goal
Remember the branch 01 exercise where switching Email→SMS meant editing
`TaskService`? Now it's a wiring decision. `TaskService` depends only on the
`NotificationService` **interface**.

## What changed vs 02
- New `NotificationService` interface.
- `EmailNotificationService` and `SmsNotificationService` both implement it.
- `EmailNotificationService` is `@Primary`.
- `TaskService` now injects the interface — and never names a concrete class.

## The resolution problem
Two beans satisfy `NotificationService`. How does the container pick?
- **`@Primary`** — the default winner when the type is ambiguous → Email runs.
- **`@Qualifier("smsNotificationService")`** — explicitly demand a specific bean.

Without `@Primary` or `@Qualifier`, startup **fails** with
`NoUniqueBeanDefinitionException` — a great thing to show on purpose.

## Run
```bash
mvn spring-boot:run
```
Output shows `[EMAIL]` (the primary).

## 🏋️ Exercise
1. Add `@Qualifier("smsNotificationService")` to the `TaskService` constructor
   parameter → output flips to `[SMS]`, with **zero** changes to business logic.
2. Remove `@Primary` and *don't* qualify → watch the context fail to start.
   Read the exception message.

## Concepts
- Program to an interface (polymorphism)
- Bean resolution: by type, then `@Primary` / `@Qualifier`
- Why constructor injection makes the dependency explicit and testable

## Next
`04-command-line-runner` — a closer look at startup lifecycle and seeding data.
