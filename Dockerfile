ARG JAVA_VERSION=26

FROM maven:3.9-eclipse-temurin-${JAVA_VERSION} AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:${JAVA_VERSION}-jre
RUN apt-get update -qq && apt-get install -y -qq curl && rm -rf /var/lib/apt/lists/*
RUN groupadd -r coursow && useradd -r -g coursow -d /app coursow
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
COPY entrypoint.sh /app/entrypoint.sh
RUN chown -R coursow:coursow /app
USER coursow
ENV SERVER_PORT=8080
ENV LOGGING_LEVEL=INFO
HEALTHCHECK --interval=30s --timeout=3s --start-period=15s --retries=3 \
  CMD curl -f http://localhost:${SERVER_PORT}/actuator/health || exit 1
ENTRYPOINT ["/app/entrypoint.sh"]
