# syntax=docker/dockerfile:1
FROM openjdk:17-alpine3.14 as base
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=localdocker" ]