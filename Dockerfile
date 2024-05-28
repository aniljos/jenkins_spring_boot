# Use a base image with Java installed
#FROM openjdk:21-jre-slim
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/app-services-1.0.0.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Environment variables for database connection
ENV POSTGRES_HOST=db
ENV POSTGRES_PORT=5432
ENV POSTGRES_DB=training
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=sa

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
