# Branch 17 — Spring AI

**Day 3 · Integrating AI** *(the payoff: a real starter doing what you just built)*

## Goal
Add an LLM-powered endpoint — and recognize that the Spring AI starter is the
**exact same machinery** you built in branch 15, just bigger.

## What changed vs 16
- Imported the **Spring AI BOM** (1.1.1) and added
  `spring-ai-starter-model-anthropic`.
- `ai/TaskAiService` injects an auto-configured `ChatClient.Builder`, builds a
  `ChatClient`, and prompts the model to prioritize the current tasks.
- `POST /tasks/prioritize` returns the suggestion.
- `application.properties` reads the API key from `ANTHROPIC_API_KEY`.

## "It's just a starter"
Open `/actuator/conditions` and find `AnthropicChatAutoConfiguration` (and
friends). It uses `@ConditionalOnClass`, `@ConditionalOnProperty`,
`@ConditionalOnMissingBean`, `@EnableConfigurationProperties` — **the same
annotations as your `TaskflowGreetingAutoConfiguration`.** That's the whole
lesson of Day 3 landing.

## Run & test
```bash
# 1) provide a key (instructor)
export ANTHROPIC_API_KEY=sk-ant-...      # PowerShell: $env:ANTHROPIC_API_KEY="sk-ant-..."

# 2) run
mvn spring-boot:run

# 3) call it (POST under /tasks requires the admin role from branch 13)
curl -u admin:admin -X POST http://localhost:8080/tasks/prioritize
```
The app **starts without a key** (placeholder default); only the call needs one.

## Concepts
- **LLM abstraction** — `ChatClient` is portable across providers (swap the
  starter for OpenAI/Ollama/etc., keep the code).
- **Prompt engineering** — system + user messages shape the output.
- **AI integration as configuration** — adding intelligence was one dependency
  + one bean, because auto-configuration did the wiring.

## 🔵 Stretch (senior)
- Return structured output: `.call().entity(PriorityPlan.class)` to map the
  response into a record.
- Swap to `spring-ai-starter-model-openai` and confirm `TaskAiService` is
  unchanged — only the starter + properties differ.

## Next
`18-final-version` — the whole picture, with a guided tour.
