FROM maven:3.8.5-openjdk-17-slim AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

FROM openjdk:17-oracle
EXPOSE 8080
ADD target/MVC2-0.0.1-SNAPSHOT.jar spring-boot-app.jar
ENTRYPOINT ["java", "-jar", "spring-boot-app.jar"]
