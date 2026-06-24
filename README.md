# Branch 05 — Properties & Profiles

**Day 1 · Externalized configuration & environment-specific behavior** *(last Day 1 topic)*

## Goal
Stop hard-coding values. Bind configuration type-safely, and change behavior per
environment without recompiling.

## What changed vs 04
- `TaskflowProperties` (`@ConfigurationProperties(prefix = "taskflow")`) binds
  all `taskflow.*` keys; registered via `@EnableConfigurationProperties`.
- `spring-boot-configuration-processor` added → IDE auto-complete for our keys.
- Three property files: `application.properties` (base, activates `dev`),
  `application-dev.properties`, `application-prod.properties`.
- `EmailNotificationService` → `@Profile("dev")`, `SmsNotificationService` →
  `@Profile("prod")`. The active profile now selects the implementation.
- `TaskService` uses `properties.getRecipient()` instead of a literal.

## Run — dev (default)
```bash
mvn spring-boot:run
```
```
>> Active profiles: [dev]
>> Greeting: Hello from DEV (Email notifications)
[EMAIL] to=dev-user@taskflow.dev : Task created: ...
```

## Run — prod
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```
```
>> Active profiles: [prod]
>> Greeting: Hello from PROD (SMS notifications)
[SMS] to=ops@taskflow.dev : Task created: ...
```
Same code, same beans declared — **only the active profile changed**, and both
the notification channel *and* the config values flipped.

## Concepts
- Externalized configuration & property precedence (CLI > profile file > base)
- `@ConfigurationProperties` (type-safe) vs `@Value` (one-off)
- `@Profile` for environment-specific beans
- How this ties back to DI: profiles are just another bean-resolution tool

## 🔵 Stretch (senior)
Override `taskflow.recipient` with an env var (`TASKFLOW_RECIPIENT=...`) or a
JVM `-Dtaskflow.recipient=` and watch it win over the file. Discuss the property
source order Spring Boot uses.

## End of Day 1 — Annotations recap
Decode `@SpringBootApplication` = `@Configuration` + `@EnableAutoConfiguration`
+ `@ComponentScan`. Map every annotation seen so far: `@Component`/`@Service`,
`@Bean`, `@Primary`/`@Qualifier`, `@PostConstruct`, `@ConfigurationProperties`,
`@EnableConfigurationProperties`, `@Profile`. `@EnableAutoConfiguration` is the
thread we pull on Day 3.

## Next
`06-rest-api` — Day 2 begins: expose tasks over HTTP with Spring MVC.
