# Spring Boot Deep Dive — TaskFlow Bootcamp

A progressive, branch-by-branch Spring Boot project built for a 3-day deep-dive
bootcamp (entry / mid / senior). Each branch is one teaching checkpoint. Diff
any two branches to see exactly what a concept added:

```bash
git switch 05-rest-api
git diff 04-properties-and-profiles..05-rest-api
```

## Branches

| Day | Branch | Concept |
|-----|--------|---------|
| 1 | `00-project-setup` | Spring Boot, starters, embedded container |
| 1 | `01-java-without-spring` | The pain: tight coupling, manual `new` |
| 1 | `02-first-spring-context` | IoC, `ApplicationContext`, beans |
| 1 | `03-dependency-injection` | Interfaces, `@Primary`, `@Qualifier` |
| 1 | `04-command-line-runner` | Bean lifecycle / startup |
| 1 | `05-properties-and-profiles` | `@ConfigurationProperties`, `@Profile` |
| 2 | `06-rest-api` | Spring MVC, `DispatcherServlet`, JSON |
| 2 | `07-request-response-dtos` | API contracts, DTOs |
| 2 | `08-validation` | Bean Validation, `@Valid` |
| 2 | `09-jpa-h2` | Spring Data JPA, repositories |
| 2 | `10-service-layer` | Layered architecture |
| 2 | `11-pagination-search` | `Page<T>`, derived queries |
| 2 | `12-global-exception-handler` | `@ControllerAdvice` |
| 2 | `13-security-basic-auth` | `SecurityFilterChain`, auth/authz |
| 2 | `14-internals-and-actuator` | `BeanPostProcessor`, lifecycle, actuator |
| 3 | `15-custom-starter` | **Auto-configuration & your own starter** |
| 3 | `16-custom-health-indicator` | Conditional beans, production readiness |
| 3 | `17-spring-ai` | `ChatClient`, AI integration |
| 3 | `18-final-version` | Everything assembled |

## Requirements

- JDK 21+ (built/tested on Corretto 25)
- Maven 3.9+

## Run

```bash
mvn spring-boot:run
```
