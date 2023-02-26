FROM ubuntu:22.04

RUN apt update &&\
    apt install maven -y
    #apt install mysql-server -y

#RUN systemctl start mysql.services

#RUN mysql &&\
#    ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'pass_00' &&\
#    CREATE USER 'springuser'@'host' IDENTIFIED WITH authentication_plugin BY 'pass_00';

WORKDIR /app

COPY . .

CMD mvn spring-boot:run

EXPOSE 8080