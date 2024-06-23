FROM openjdk:17-oracle
EXPOSE 8080
ADD target/MVC2-0.0.1-SNAPSHOT.jar spring-boot-app.jar
ENTRYPOINT ["java", "-jar", "spring-boot-app.jar"]
