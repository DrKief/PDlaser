# Stage 1: Build remains the same (Alpine is fine for building)
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app
ENV MAVEN_ARGS="-B -ntp"
COPY pom.xml .
RUN mvn $MAVEN_ARGS dependency:go-offline
COPY src ./src
RUN mvn $MAVEN_ARGS clean package

# Stage 2: Run the Application
# CHANGED: Switched from alpine to the standard Ubuntu-based image for native AI support
FROM eclipse-temurin:21-jre
WORKDIR /app

# Create security user and group (Ubuntu syntax is slightly different)
RUN groupadd -r appgroup && useradd -r -g appgroup appuser

# Pre-create image directory with correct ownership
RUN mkdir -p /var/lib/pdl/images \
  && chown -R appuser:appgroup /var/lib/pdl

# Switch to non-privileged user for security
USER appuser

# Copy the JAR from the builder stage
COPY --from=builder --chown=appuser:appgroup /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
