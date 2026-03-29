# Stage 1: Build with Dependency Caching (Upgraded to Java 25)
FROM maven:3.9-eclipse-temurin-25-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Optimized & Secure Runtime (Upgraded to Java 25)
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Fix: Create /var/lib/pdl/images and assign ownership to appuser
RUN mkdir -p /app/src/main/resources/images /var/lib/pdl/images \
  && chown -R appuser:appgroup /app /var/lib/pdl/images

USER appuser
COPY --from=build --chown=appuser:appgroup /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
