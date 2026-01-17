# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Create the directory your app expects for images
RUN mkdir -p /app/src/main/resources/images

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
