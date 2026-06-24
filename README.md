# Branch 13 — Security (Basic Auth)

**Day 2 · Spring Security**

## Goal
Lock the API down: nobody reads tasks without logging in, and only an admin can
create them.

## What changed vs 12
- Added `spring-boot-starter-security`. (Just adding it secures everything with
  a generated password — show that first, then replace it.)
- `config/SecurityConfig` defines a `SecurityFilterChain`:
  - `/h2-console/**` → open
  - `POST /tasks/**` → role `ADMIN`
  - everything else → authenticated
  - HTTP Basic auth
- Two in-memory users: `user/password` (USER) and `admin/admin` (ADMIN).

## Run & test
```bash
mvn spring-boot:run

# no credentials -> 401
curl -i http://localhost:8080/tasks

# user can read -> 200
curl -u user:password http://localhost:8080/tasks

# user CANNOT create -> 403
curl -i -u user:password -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" -d '{"title":"Nope"}'

# admin can create -> 200
curl -u admin:admin -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" -d '{"title":"Admin task"}'
```

## Concepts
- **Filter chain** — security is servlet filters running before the controller.
- **Authentication** (who are you? — Basic creds → `UserDetailsService`) vs
  **Authorization** (what may you do? — role rules).
- **`PasswordEncoder`** — never store plaintext; BCrypt here.

## ⚠️ Teaching notes
- CSRF is disabled only because this is a stateless API demo. Leave it ON for
  session/browser apps.
- In-memory users are for demos; real apps back `UserDetailsService` with a DB
  or an OIDC provider (e.g. Okta, as in the LIFT platform).

## 🔵 Stretch (senior)
Print the filter chain (`logging.level.org.springframework.security=DEBUG`) and
count how many filters a request passes through. Discuss where you'd plug a JWT
or OAuth2 resource-server filter.

## Next
`14-internals-and-actuator` — open the hood: beans, lifecycle, conditions.
