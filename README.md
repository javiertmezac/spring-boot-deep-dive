# Branch 18 — TaskFlow (Final Version)

**The complete bootcamp project.** Everything from branches 00–17 assembled: a
secured, persistent, paginated REST API with global error handling, actuator
internals, a custom Spring Boot starter, and an AI endpoint.

---

## Architecture

```
HTTP ─▶ SecurityFilterChain ─▶ DispatcherServlet ─▶ TaskController
                                                          │
                            ┌─────────────────────────────┼───────────────┐
                            ▼                              ▼               ▼
                       TaskService                  TaskAiService    (DTOs in/out)
                     (@Transactional)               (ChatClient)
                            │                              │
                            ▼                              ▼
                      TaskRepository                Anthropic ChatModel
                       (Spring Data)                (auto-configured by
                            │                        spring-ai starter)
                            ▼
                       H2 (in-memory)

Cross-cutting:
  GlobalExceptionHandler (@RestControllerAdvice)   → consistent ApiError
  TaskflowHealthIndicator                          → /actuator/health
  LoggingBeanPostProcessor / StartupReporter       → internals demo
  taskflow-spring-boot-starter (separate project)  → auto-configured greeter
```

## Source layout
```
taskflow-api/src/main/java/com/itj/bootcamp/taskflow/
├── TaskflowApplication.java        @SpringBootApplication, CommandLineRunner
├── TaskController.java             REST endpoints
├── TaskService.java                business logic + transactions
├── TaskRepository.java             Spring Data JPA
├── Task.java                       JPA entity
├── TaskflowProperties.java         @ConfigurationProperties
├── NotificationService.java        interface (+ Email/Sms @Profile impls)
├── dto/        CreateTaskRequest, TaskResponse, ApiError
├── config/     SecurityConfig
├── exception/  TaskNotFoundException, GlobalExceptionHandler
├── health/     TaskflowHealthIndicator
├── ai/         TaskAiService
└── internals/  LoggingBeanPostProcessor, StartupReporter

taskflow-spring-boot-starter/        ← your own starter (separate jar)
└── ...starter/  TaskflowGreetingProperties, *Service, *AutoConfiguration
                 + META-INF/spring/...AutoConfiguration.imports
```

## Endpoints
| Method | Path | Auth | Notes |
|--------|------|------|-------|
| GET | `/tasks?page=&size=&sort=&q=` | authenticated | paged + search |
| GET | `/tasks/{id}` | authenticated | 404 if missing |
| POST | `/tasks` | **ADMIN** | validated body |
| POST | `/tasks/{id}/complete` | **ADMIN** | |
| POST | `/tasks/prioritize` | **ADMIN** | LLM (needs API key) |
| GET | `/actuator/{health,beans,conditions,mappings,info}` | open | |
| GET | `/h2-console` | open | |

Users: `user/password` (USER), `admin/admin` (ADMIN).

## Build & run
```bash
# 1) install the custom starter (once)
cd taskflow-spring-boot-starter && mvn -q clean install && cd ..

# 2) (optional) AI key for /tasks/prioritize
#    PowerShell:  $env:ANTHROPIC_API_KEY="sk-ant-..."
export ANTHROPIC_API_KEY=sk-ant-...

# 3) run the app
mvn spring-boot:run

# smoke test
curl -u user:password "http://localhost:8080/tasks?size=2"
curl -u admin:admin -X POST http://localhost:8080/tasks -H "Content-Type: application/json" -d '{"title":"Ship it"}'
```

---

## Concept → branch map (the 3-day journey)

**Day 1 — Core container**
| Branch | Concept |
|--------|---------|
| 00 | Spring Boot, starters, embedded container |
| 01 | the pain: tight coupling, manual `new` |
| 02 | IoC, ApplicationContext, constructor injection |
| 03 | DI: interface, `@Primary`/`@Qualifier` |
| 04 | bean lifecycle, `@PostConstruct`, `CommandLineRunner` |
| 05 | `@ConfigurationProperties`, `@Profile` |

**Day 2 — Web, data, security, internals**
| Branch | Concept |
|--------|---------|
| 06 | Spring MVC, DispatcherServlet, JSON |
| 07 | DTOs / API contracts |
| 08 | Bean Validation |
| 09 | Spring Data JPA + H2 |
| 10 | service layer + `@Transactional` |
| 11 | pagination + derived queries |
| 12 | `@RestControllerAdvice` error handling |
| 13 | Spring Security (filter chain, auth/authz) |
| 14 | internals: `BeanPostProcessor`, lifecycle, actuator |

**Day 3 — Auto-config, starters, AI**
| Branch | Concept |
|--------|---------|
| 15 | ⭐ build your own starter + auto-configuration |
| 16 | custom `HealthIndicator` (conditional bean) |
| 17 | Spring AI — a real starter in action |
| 18 | the whole picture |

## The one idea to remember
Branches 15–17 are the same idea three times: **a jar on the classpath
contributes beans through conditional auto-configuration.** Once you've built one
(15), you can read every `spring-boot-starter-*` and `spring-ai-starter-*` the
same way. That's what Spring Boot is doing behind the scenes.

---

> Built on Spring Boot 4.1.0 / Java 21.
