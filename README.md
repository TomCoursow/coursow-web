# Coursow-Web

Personal portfolio website of **Tom-Henry Coursow**, built with Spring Boot and Thymeleaf.

See it live at [coursow.de](https://coursow.de).

## Tech Stack

- **Java 26** with **Spring Boot 4.0**
- **Thymeleaf** server-side templating
- **Maven** build tool
- Custom CSS (dark theme, responsive grid layout)

## Features

- About Me section with greeting and description
- Projects showcase with technology tags and GitHub links
- Timeline-based Companies / Work Experience section
- Responsive dark-themed design with animated gradients
- Simple Data-driven content via JSON files (no database required)

## Getting Started

### Prerequisites

- JDK 26
- Apache Maven

### Build

```bash
mvn clean install
```

### Run

```bash
mvn spring-boot:run
```

### Test

```bash
mvn test
```

## Project Structure

```
src/
├── main/java/de/coursow/web/
│   ├── CoursowWebApplication.java        # Entry point
│   ├── controller/HomeController.java    # MVC controller
│   ├── model/                            # About, Company, Project, Technology
│   └── repository/                       # JSON-backed data repositories
└── main/resources/
    ├── data/                             # about.json, companies.json, projects.json
    ├── static/css/styles.css             # Custom styles
    └── templates/                        # Thymeleaf fragments & pages
```

## Configuration

All portfolio content lives in `src/main/resources/data/` as JSON files.

## Docker

### Build

```bash
docker build -t tom-coursow/coursow-web:latest .
```

### Run (standalone)

```bash
docker run -d -p 8080:8080 --name coursow-web tom-coursow/coursow-web:latest
```

Opens the app at `http://localhost:8080`.

### Deploy to Swarm

```bash
docker stack deploy -c docker-stack.yml coursow
```

The stack runs 2 replicated instances behind a health check, with rolling updates (start-first) and automatic rollback on failure. The service is exposed on port `8080`. See `docker-stack.yml` for all tuning parameters.

## License

Apache License 2.0
