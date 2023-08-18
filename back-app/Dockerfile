# PRODUCTION

# FROM maven:3.8.4-jdk-8
FROM maven:3.9.2

WORKDIR /back-app

COPY back-app/ .

EXPOSE 8080

CMD mvn spring-boot:run


# TODO: solve problem with isoparser and reimplement jar package

# # RUN mvn clean compile assembly:single
# RUN mvn clean package -Dmaven.test.skip

# EXPOSE 8080

# # CMD java -jar target/VideoStream-1.0-SNAPSHOT-jar-with-dependencies.jar
# CMD java -Xmx1g -jar target/VideoStream-1.0-SNAPSHOT.jar