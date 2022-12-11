# VideoStream

## To Do List

* Server setup

-- Milestone --

* Fix issue: need spring refresh to find video after upload

* Create and throw exceptions for User and Role

* Add and implement slug field to video model

* WebSecurityConfig is deprecated -> update

* Split login and registration controllers

* Create public and private components folder

* Javascript constants file

* Unit Tests

* OAuth

* Finish upload backend

* Finish upload frontend

* Droplet monitoring

* Docker

* Category system

* Mobile view

* Custom error page with handling

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

Build executable jar
```
mvn clean compile assembly:single
```

Run jar
```
java -jar target/VideoStream-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Tutorials

SQL relationships:
<https://medium.com/@emekadc/how-to-implement-one-to-one-one-to-many-and-many-to-many-relationships-when-designing-a-database-9da2de684710>

Spring Security Mysql:
<https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d>


## Deployment steps

1. Create droplet

2. Generate SSH key

3. Copy SSH key to github account to grant access to github from the droplet

4. Clone repository

5. Install maven

```
sudo apt update
sudo apt install maven
```

6. Create swap file to enable mysql to work
(1Gb)

```
 free -m
 swapon -s
 swapon -show
 dd if=/dev/zero of=/swapfile bs=1024 count=1048576
 chmod 600 /swapfile
 mkswap /swapfile
 swapon /swapfile
 
 nano /etc/fstab
 # Add this line
 /swapfile swap swap defaults 0 0
 
 # Verify with:
 free -m
```

7. Install MySQL

```
sudo apt update
sudo apt install mysql-server
sudo systemctl start mysql.service
```

8. Create mysql springuser credentials

9. Run migrations, Compile & Run



