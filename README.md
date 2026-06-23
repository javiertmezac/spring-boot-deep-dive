# Branch 00 — Project Setup

**Day 1 · Spring Framework & Spring Boot**

## Goal
See how a Spring Boot project is created and what the absolute minimum looks like.

- `pom.xml` — inherits `spring-boot-starter-parent` (4.1.0), one dependency: `spring-boot-starter`.
- `TaskflowApplication.java` — a single class with `@SpringBootApplication` + `main`.
- `application.properties` — just an app name.

## Run
```bash
mvn spring-boot:run
```
The app starts the Spring `ApplicationContext`, prints the banner, and exits
(there's no web server yet — that arrives in branch 06).

## Talking points
- **Starter dependencies** — `spring-boot-starter` pulls in core + logging + a
  curated, version-aligned dependency set. You never pick versions yourself; the
  parent BOM does.
- **Embedded container** — coming once we add `-web`; Boot favors *embedded*
  servers over deploying a WAR to an external one.
- **`@SpringBootApplication`** — a meta-annotation. We decode it at the end of
  Day 1: `@Configuration` + `@EnableAutoConfiguration` + `@ComponentScan`.
- **`SpringApplication.run(...)`** — bootstraps the container.

## 🔵 Stretch (senior)
Run `mvn dependency:tree` and find how many JARs one `spring-boot-starter` line
brought in. Ask: who chose those versions?

## Next
`01-java-without-spring` — we build the same idea *without* Spring to feel the pain.
