# ==========================================
# Stage 1: Build the Application (And Run Tests)
# ==========================================
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Set Maven arguments to keep logs clean across all commands
ENV MAVEN_ARGS="-B -ntp"

# Copy POM and download dependencies first to utilize Docker caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Package the application and run all tests.
# If ImageLifecycleEndpointLayerTests fails, the Docker build stops here.
RUN mvn clean package

# ==========================================
# Stage 2: Run the Application
# ==========================================
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Create a non-root user for security best practices
RUN addgroup --system spring && adduser --system --ingroup spring springuser
USER springuser

# Copy the compiled JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Execute the application
ENTRYPOINT ["java", "-jar", "app.jar"]