# === Stage 1: Build and test ===
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Set working directory
WORKDIR /build

# Copy dependencies and build files
COPY pom.xml .
COPY src ./src

# Build the app
RUN mvn clean package -DskipTests

# === Stage 2: Final image ===
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy jar from builder stage
COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
