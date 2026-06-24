# Branch 16 — Custom Health Indicator

**Day 3 · Production readiness · auto-configuration (consumer side)**

## Goal
Contribute application-specific health to Actuator — and see that "implement an
interface, register a bean" is the same pattern your starter relied on.

## What changed vs 15
- `health/TaskflowHealthIndicator implements HealthIndicator`, registered as a
  `@Component`. It reports `UP` with the task count (or `DOWN` if the store fails).

## Run & test
```bash
mvn spring-boot:run
curl http://localhost:8080/actuator/health
```
```json
{
  "status": "UP",
  "components": {
    "db": { "status": "UP" },
    "diskSpace": { "status": "UP" },
    "ping": { "status": "UP" },
    "taskflow": {
      "status": "UP",
      "details": { "store": "H2 (in-memory)", "taskCount": 5 }
    }
  }
}
```

## Concepts
- **`HealthIndicator`** — your check is aggregated into the overall health.
- **Auto-detection** — Actuator's `HealthContributorAutoConfiguration` finds
  every `HealthIndicator` bean. No registration file needed because the beans
  are in the app's own component scan (contrast with the starter in branch 15,
  which needed the `.imports` file precisely because it's *outside* the scan).
- **Production readiness** — health drives k8s liveness/readiness probes and LBs.

## ⚠️ Spring Boot 4 note
The health API moved: `HealthIndicator` / `Health` are now in
`org.springframework.boot.health.contributor` (the new `spring-boot-health`
module), not the old `org.springframework.boot.actuate.health`. If you copy a
Boot 3 example, fix the import.

## 🔵 Stretch (senior)
Stop the data layer (or throw in `health()`) and watch the overall status flip to
`DOWN` and the HTTP status become `503`. Then group it under a readiness probe
with `management.endpoint.health.group.readiness.include=taskflow,db`.

## Next
`17-spring-ai` — the payoff: a real starter (Spring AI) doing what you just built.
