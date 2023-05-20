# PRODUCTION

# FROM maven:3.8.4-jdk-8
FROM maven:3.9.2

WORKDIR /back-app

COPY back-app/ .

# RUN mvn clean compile assembly:single
RUN mvn clean package

EXPOSE 8080

# CMD java -jar target/VideoStream-1.0-SNAPSHOT-jar-with-dependencies.jar
CMD java -jar target/VideoStream-1.0-SNAPSHOT.jar