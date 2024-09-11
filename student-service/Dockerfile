# Use an official Maven image with JDK 11 as the base image
FROM maven:3.8.3 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and the source code into the container
COPY pom.xml .
COPY src ./src

# Package the Maven project (skip tests if needed)
RUN mvn clean package

# Use an official OpenJDK 11 image as the runtime base image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file from the build stage
COPY --from=build /app/target/*.jar ./app.jar

# Expose the port the application will run on
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]
