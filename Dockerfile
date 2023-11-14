# Stage 1: Build the application using Maven
FROM maven:3.6.3-jdk-8 AS build
WORKDIR /usr/src/app
COPY . .
RUN mvn clean package

# Stage 2: Use a smaller base image and copy the JAR file
FROM openjdk:8-jre-slim
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target .

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "target/soap_service-1.0.jar"]