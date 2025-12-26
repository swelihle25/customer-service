# Use Eclipse Temurin JDK 17 on Ubuntu Jammy as base image
FROM eclipse-temurin:17-jdk-jammy

# Install Maven inside the container
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean

# Set working directory
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the project using installed Maven
RUN mvn clean package -DskipTests

# Run the JAR
CMD ["java", "-jar", "target/customer-service-0.0.1-SNAPSHOT.jar"]
