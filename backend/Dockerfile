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

RUN apt-get update && apt-get install -y gosu && rm -rf /var/lib/apt/lists/*

# Create a non-root user without forcing UID 1000 (avoids Ubuntu OS collisions)
RUN groupadd -r appgroup && useradd -r -g appgroup appuser

# Pre-create BOTH images and datasets directories
RUN mkdir -p /var/lib/pdl/datasets /var/lib/pdl/models \
  && chown -R appuser:appgroup /var/lib/pdl

COPY --from=builder /app/target/*.jar app.jar
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/entrypoint.sh"]
CMD ["java", "-jar", "app.jar"]
