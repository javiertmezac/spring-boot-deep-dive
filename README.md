# Branch 14 — Internals & Actuator

**Day 2 · Internals** *(the senior payoff — and the bridge to Day 3)*

## Goal
Open the hood. Make the container's mechanics *visible*: how beans get created,
when lifecycle events fire, and how to introspect a running app.

## What changed vs 13
- `internals/LoggingBeanPostProcessor` — a `BeanPostProcessor` that logs each of
  our beans as it's initialized. **This is the same hook Spring uses** to build
  `@Autowired`, `@Transactional`/AOP proxies, validation, etc.
- `internals/StartupReporter` — injects the `ApplicationContext`, listens for
  `ApplicationReadyEvent`, prints the bean count.
- Added `spring-boot-starter-actuator`; exposed `health, beans, conditions, env,
  mappings`; security permits `/actuator/**`.

## Run & explore
```bash
mvn spring-boot:run
```
Watch the startup log: `[BeanPostProcessor] before-init: taskService ...` for
each bean, then `Context ready. Total bean definitions: N`.

```bash
curl http://localhost:8080/actuator/health
curl http://localhost:8080/actuator/beans       | jq '.contexts.application.beans | keys'
curl http://localhost:8080/actuator/conditions   # the auto-configuration report
curl http://localhost:8080/actuator/mappings      # every URL -> handler
```

## Concepts — how Spring actually works
- **`BeanFactory` vs `ApplicationContext`** — the factory creates/wires beans;
  the context adds events, i18n, resource loading, and runs `BeanPostProcessor`s.
- **`BeanPostProcessor`** — the extension point behind most Spring "magic".
- **Lifecycle** — definitions registered → instantiate → inject → BPP before →
  `@PostConstruct` → BPP after → ready → `ApplicationReadyEvent`.
- **Actuator** — production observability; `/conditions` previews exactly the
  auto-configuration story we unpack on Day 3.

## 🔵 Stretch (senior)
In `/actuator/conditions`, find a config that was **negative** (skipped) and read
why. That `@ConditionalOn...` decision is precisely what you'll write in your own
starter tomorrow.

## End of Day 2
You've built a secured, persistent, paginated REST API — and seen the machinery
underneath it.

## Next (Day 3)
`15-custom-starter` — the highlight: build your own Spring Boot starter and
auto-configuration.
