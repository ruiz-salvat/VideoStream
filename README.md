# VideoStream

## To Do List

* Create and throw exceptions for User and Role

* Add and implement slug field to video model

* WebSecurityConfig is deprecated -> update

* Error page

* Create public and private components folder

* Javascript constants file

* Unit Tests

* OAuth

* Upload backend

* Upload frontend

-- Milestone --

* Server setup

* Docker

* Category system

* Mobile view

...



## Database setup

```
mysql> create database db_example; -- Creates the new database
mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
mysql> grant all on db_example.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database
```

## Commands

Apply migration

```
mvn flyway:migrate
```

## Tutorials

SQL relationships:
<https://medium.com/@emekadc/how-to-implement-one-to-one-one-to-many-and-many-to-many-relationships-when-designing-a-database-9da2de684710>

Spring Security Mysql:
<https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d>

