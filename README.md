# Coursow-Web

Personal portfolio website of **Tom-Henry Coursow**, built with Spring Boot and Thymeleaf.

See it live at [coursow.de](https://coursow.de).

## Tech Stack

- **Java 26** with **Spring Boot 4.0**
- **Thymeleaf** server-side templating
- **Maven** build tool
- **PostgreSQL** with **Spring Data JPA / Hibernate**
- **Liquibase** for database schema migrations
- **MapStruct** for entity-to-VO mapping (zero boilerplate)
- Custom CSS (dark theme, responsive grid layout)

## Features

- About Me section with greeting and description
- Projects showcase with technology tags and GitHub links
- Timeline-based Companies / Work Experience section
- Responsive dark-themed design with animated gradients
- Data-driven content via PostgreSQL (seeded from JSON on first run)

## Prerequisites

- **Docker** or Podman (for local database and deployment)
- JDK 26 + Apache Maven (for local development)

Three compose files are provided:

| File                     | Purpose                                                           |
|--------------------------|-------------------------------------------------------------------|
| `docker-compose.db.yaml` | Standalone PostgreSQL Service to use with local Maven development |
| `docker-compose.yaml`    | Full stack (PostgreSQL + Web App)                                 |
| `docker-stack.yml`       | Docker Swarm Stack deployment for production                      |

## Local Development

Build, test, and run directly with Maven. PostgreSQL runs in Docker.

### Build

```bash
mvn clean install
```

### Test

```bash
mvn test
```

Uses H2 in-memory database automatically.
Liquibase is disabled in tests.
Hibernate's `create-drop` manages the test schema.

### Run

Create and Edit `secrets/postgres_password.txt` (gitignored) to change the password.

Make sure that the `POSTGRES_PASSWORD` environment variable is set (as export or in IDE Run Config):

```
export POSTGRES_PASSWORD=$(cat secrets/postgres_password.txt)
```

Then run:

```bash
docker compose -f docker-compose.db.yaml up -d
mvn spring-boot:run
```

On startup, Liquibase applies any pending schema migrations before JPA initializes.
Then the app seeds itself from the bundled legacy JSON files if the database is empty.

> **Note:** An older JSON-based repository layer (`@Profile("json")`) is also available for scenarios without a database by setting the Spring profile to `json`.

### Adding a new schema migration

Create a new file in `src/main/resources/db/changelog/changes/` with a filename
that sorts after the existing ones.
It is picked up automatically via `includeAll`.
Add the corresponding fields to the JPA entities.

## Deployment

### Run locally (full stack)

```bash
docker compose up -d
```

Opens at `http://localhost:8080`.

### Deploy to Swarm

```bash
echo "<mySecret>" | docker secret create postgres_password -
docker stack deploy -c docker-stack.yml coursow
```

The stack includes a **PostgreSQL 17** service (single replica, persistent volume)
and the **Coursow-Web** app (2 replicated instances, rolling updates).

| Service | Replicas | Port |
|---|---|---|
| `postgres` | 1 | internal 5432 |
| `coursow-web` | 2 | published 8080 |

### Build image

```bash
docker build -t tom-coursow/coursow-web:latest .
```

## Project Structure

```
src/
├── main/java/de/coursow/web/
│   ├── CoursowWebApplication.java        # Entry point
│   ├── bootstrap/DataInitializer.java    # Seeds DB from JSON on first run
│   ├── controller/HomeController.java    # MVC controller
│   ├── entity/                           # JPA entities (AboutEntity, CompanyEntity, ...)
│   ├── mapper/                           # MapStruct mapper interfaces
│   ├── model/                            # VOs sent to templates
│   ├── repository/                       # Old JSON-backed repos (fallback)
│   │   └── jpa/                          # Spring Data JPA repositories
│   └── service/                          # Business logic layer
└── main/resources/
    ├── data/                             # about.json, companies.json, projects.json
    ├── db/changelog/                     # Liquibase schema migrations
    │   ├── db.changelog-master.yaml      #   entrypoint (includeAll)
    │   └── changes/                      #   individual migration files
    ├── static/css/styles.css             # Custom styles
    └── templates/                        # Thymeleaf fragments & pages
```

## License

Apache License 2.0
