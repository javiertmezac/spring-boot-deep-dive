# Branch 15 — Build Your Own Starter ⭐

**Day 3 · Auto-configuration · Starters · Create your own starter** *(the highlight)*

> This is the moment Spring Boot stops being magic. Every `spring-boot-starter-*`
> you've added works exactly like the one you build here.

## What is a starter?
Two things: (1) a curated set of dependencies, and (2) **auto-configuration** —
classes that conditionally register beans so the feature "just works" when the
jar is on the classpath.

## What's here
A second, standalone Maven project: **`taskflow-spring-boot-starter/`**

```
taskflow-spring-boot-starter/
├── pom.xml                              (library jar; depends on spring-boot-autoconfigure)
└── src/main/
    ├── java/.../starter/
    │   ├── TaskflowGreetingProperties   @ConfigurationProperties("taskflow.greeter")
    │   ├── TaskflowGreetingService      the bean the starter contributes
    │   └── TaskflowGreetingAutoConfiguration  @AutoConfiguration + @ConditionalOn*
    └── resources/META-INF/spring/
        └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
```

The `.imports` file is the registry: it lists the auto-configuration class so
Boot discovers and applies it. **No `@ComponentScan` reaches the starter** — this
file is how the bean gets in.

The app (`taskflow-api`) just adds the dependency and **injects
`TaskflowGreetingService`** — which it never declared.

## Build & run (order matters)
```bash
# 1) install the starter into your local Maven repo
cd taskflow-spring-boot-starter
mvn -q clean install
cd ..

# 2) run the app — it auto-configures the greeter
mvn spring-boot:run
```
Startup prints:
```
>> Hello, Spring Boot Bootcamp! (this bean came from taskflow-spring-boot-starter)
```

## The conditions (the real lesson)
- `@AutoConfiguration` — marks a config applied during Boot's auto-config phase.
- `@ConditionalOnProperty(taskflow.greeter.enabled, matchIfMissing=true)` — only
  active unless the app opts out. Set `taskflow.greeter.enabled=false` → the
  bean vanishes and injection fails (proves the condition works).
- `@ConditionalOnMissingBean` — the starter backs off if the app defines its own
  `TaskflowGreetingService`. This is why starters are overridable.

## 🏋️ Exercise
1. Set `taskflow.greeter.enabled=false` → app fails to start (no bean). Read why.
2. Set `taskflow.greeter.name=...` → greeting changes with no code edit.
3. Declare your own `@Bean TaskflowGreetingService` in the app → `@ConditionalOnMissingBean`
   makes the starter back off; your bean wins.

## 🔵 Stretch (senior)
Open `/actuator/conditions` and find `TaskflowGreetingAutoConfiguration` in the
report. You're now reading the same machinery for `DataSourceAutoConfiguration`,
`SecurityAutoConfiguration`, etc.

## Next
`16-custom-health-indicator` — another conditional bean, this time for Actuator.
