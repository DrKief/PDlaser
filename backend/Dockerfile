# syntax=docker/dockerfile:1

# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app
ENV MAVEN_ARGS="-B -ntp"
COPY pom.xml .
# Cache the Maven repository so downloads aren't repeated on every build
RUN --mount=type=cache,target=/root/.m2 mvn $MAVEN_ARGS dependency:go-offline

COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn $MAVEN_ARGS clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre
WORKDIR /app

# Create a non-root user without forcing UID 1000 (avoids Ubuntu OS collisions)
RUN groupadd -r appgroup && useradd -r -g appgroup appuser

# Pre-create BOTH images and datasets directories
RUN mkdir -p /var/lib/pdl/images /var/lib/pdl/datasets \
  && chown -R appuser:appgroup /var/lib/pdl

USER appuser

COPY --from=builder --chown=appuser:appgroup /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
