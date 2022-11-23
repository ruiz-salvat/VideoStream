# VideoStream

## To Do List

* Setup migration system

* Build video page

* Build main page

* Rename main to index

* Remove H2 (including data folder)

* Unit Tests

* OAuth

* Sytem with users (only admin atm)

* Upload backend

* Upload frontend

-- Milestone --

* Server setup

* Docker

* Category system

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
