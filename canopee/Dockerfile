# Step 1: Build the application
# Use Maven to build the Spring Boot app
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the project files to the container
COPY pom.xml .
COPY src ./src

# Package the application into a JAR file
RUN mvn clean package -DskipTests

# Step 2: Run the application
# Use the OpenJDK 17 JRE image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/canopee-0.0.1-SNAPSHOT.jar .

# Expose the port the app runs on
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app/canopee-0.0.1-SNAPSHOT.jar"]
