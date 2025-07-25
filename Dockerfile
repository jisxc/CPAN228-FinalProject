# Use Eclipse Temurin Java 21 on Ubuntu Jammy
FROM eclipse-temurin:21-jdk-jammy

# Build‐time argument pointing at your packaged JAR
ARG JAR_FILE=target/Assignment1-0.0.1-SNAPSHOT.jar

# Copy the JAR into the image
COPY ${JAR_FILE} /app.jar

# Run the JAR when the container starts
ENTRYPOINT ["java", "-jar", "/app.jar"]