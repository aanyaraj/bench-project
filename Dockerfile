FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/BenchProject-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
