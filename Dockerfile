FROM ubuntu:22.04

RUN apt update &&\
    apt install maven -y

WORKDIR /app

COPY . .

CMD mvn spring-boot:run

EXPOSE 8080