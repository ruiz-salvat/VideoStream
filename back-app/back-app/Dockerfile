# DEVELOPMENT

FROM ubuntu:22.04

RUN apt update &&\
    apt install maven -y

WORKDIR /back-app

EXPOSE 8080

CMD mvn spring-boot:run


# TODO: use maven image
#FROM maven:3.8.2-jdk-8
#RUN mvn clean install