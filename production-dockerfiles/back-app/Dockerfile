FROM ubuntu:22.04

RUN apt update &&\
    apt install maven -y

WORKDIR /back-app

COPY ../../back-app/. .

EXPOSE 8080

CMD mvn spring-boot:run