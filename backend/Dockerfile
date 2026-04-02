# Stage 1: Build the Application and Run Tests
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app

# The -B (Batch mode) and -ntp (No Transfer Progress) flags 
# prevent the massive "Download 1/500 KB" spam in logs.
ENV MAVEN_ARGS="-B -ntp"

# Copy POM and download dependencies to utilize Docker caching
COPY pom.xml .
RUN mvn $MAVEN_ARGS dependency:go-offline

# Copy the source code
COPY src ./src

# Run tests and package. If tests fail, the build stops here.
RUN mvn $MAVEN_ARGS clean package

# Stage 2: Run the Application
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Create security user and group
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Pre-create image directory with correct ownership
RUN mkdir -p /var/lib/pdl/images \
    && chown -R appuser:appgroup /var/lib/pdl

# Switch to non-privileged user for security
USER appuser

# Copy the JAR from the builder stage
COPY --from=builder --chown=appuser:appgroup /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]