# PRODUCTION

# FROM eclipse-temurin:11-jdk
FROM maven:3.8.2-jdk-8

WORKDIR /back-app

COPY back-app/ .

RUN mvn clean compile assembly:single

EXPOSE 8080

CMD java -jar target/VideoStream-1.0-SNAPSHOT-jar-with-dependencies.jar