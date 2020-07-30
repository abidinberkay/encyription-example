FROM maven:3.6.3-jdk-8-slim AS build
COPY src /usr/app/src
COPY pom.xml /usr/app
RUN mvn -f /usr/app/pom.xml clean package

FROM openjdk:8
EXPOSE 8080
COPY --from=build /usr/app/target/*.jar icefire-assignment.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=DOCKER", "/icefire-assignment.jar"]