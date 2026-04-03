# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app
ENV MAVEN_ARGS="-B -ntp"
COPY pom.xml .
RUN mvn $MAVEN_ARGS dependency:go-offline
COPY src ./src
RUN mvn $MAVEN_ARGS clean package

# Stage 2: Run
FROM eclipse-temurin:21-jre
WORKDIR /app

RUN groupadd -r appgroup && useradd -r -g appgroup appuser

# Pre-create BOTH images and datasets directories
RUN mkdir -p /var/lib/pdl/images /var/lib/pdl/datasets \
  && chown -R appuser:appgroup /var/lib/pdl

USER appuser

COPY --from=builder --chown=appuser:appgroup /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]