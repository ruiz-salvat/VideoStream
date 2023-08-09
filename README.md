# VideoStream


## To Do List

* SSL
  * https://www.learnbestcoding.com/post/17/ssl-https-with-nginx
  * https://www.digitalocean.com/community/tutorials/how-to-secure-nginx-with-let-s-encrypt-on-ubuntu-20-04
  * https://www.digitalocean.com/community/tutorials/how-to-secure-a-containerized-node-js-application-with-nginx-let-s-encrypt-and-docker-compose
* finish video reproducer styles

* Frontend

  * index page
    * read cookie and display user page
  * video page
* Ads

-- Milestone --

* Externalize file storage
* Analytics Bugs
* Server/security setup
  * Firewall
    * make ports unavailable
  * NGINX
    * load balancer
    * finish proxies
    * handle errors and index
* Docker
  * name containers?
  * docker test
  * docker desktop?
* Unit Tests
* Integration Tests
* Docs

-- Milestone --

* Registration form validation at template
* serialize name plan
* Logout
* Captcha
* OAuth
* Dashboard
* Plan name
* New DB model
* CI/CD
* GTA4 to login and registration
* Document how video permissions work
* Style 404
* Style 502

-- Improvement --

* Scale admin
* Set requirement upload image min width 300px;
* Droplet monitoring
* Mobile view
* Custom error page with handling?
* Docker Migration
* DTO (two-way)
* Handle just jpg and throw exceptions for other extensions
* Create and throw exceptions for User and Role
* Not null and db constraints

-- Issues --

* warn-once.js:11  Do not add <script> tags using next/head (see <script> tag with src="https://www.googletagmanager.com/gtag/js?id=G-8F2W27LGV5"). Use next/script instead. 
* WebSocket connection to 'ws://127.0.0.1:8082/_next/webpack-hmr' failed: 
  init @ websocket.js:60

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

or

mvn clean package
```

Run jar

```
java -jar target/VideoStream-1.0-SNAPSHOT-jar-with-dependencies.jar

or

java -jar target/VideoStream-1.0-SNAPSHOT.jar
```

Systemctl service
```
systemctl status video_stream
systemctl start video_stream
systemctl stop video_stream
```

## Tutorials

Digital Ocean Server Setup:
<https://www.digitalocean.com/community/tutorials/initial-server-setup-with-ubuntu-22-04>

Run spring boot as service:
<https://www.springcloud.io/post/2022-02/running-as-system-service/#gsc.tab=0>

Ubuntu firewall:
<https://ubuntu.com/server/docs/security-firewall>

SQL relationships:
<https://medium.com/@emekadc/how-to-implement-one-to-one-one-to-many-and-many-to-many-relationships-when-designing-a-database-9da2de684710>

Spring Security Mysql:
<https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d>

Access control by user roles:
<https://www.baeldung.com/spring-security-method-security>

Next js pre-rendering

<https://nextjs.org/learn/basics/data-fetching/pre-rendering>

Install docker compose ubuntu

<https://docs.docker.com/engine/install/ubuntu/>

<https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-compose-on-ubuntu-22-04>

## Deployment steps

1. Create droplet

2. Generate SSH key

3. Create ubuntu user
	```
	adduser videostream
	usermod -aG sudo videostream
	```

4. Set up firewall
	```
	ufw allow OpenSSH
	ufw enable
	```
	Verify:
	```
	ufw status
	```

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

9. Copy flyway.conf and application.properties (ignored files)

10. Run migrations
	1. Might need to populate hibernate_sequence
	```
	INSERT INTO hibernate_sequence VALUES (99);
	```

11. Create VideoStreamData folder at the same level of the repository

12. Build executable jar

13. Might need to set a high number in the hibernate sequence table to avoid having problems with ids

14. Run as service

Create files:

mkdir shell-scripts
cd shell-scripts

start.sh
```
#!/bin/bash
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/bin/java
export PATH=$JAVA_HOME/bin:$PATH
APP_NAME=video_stream
nohup java -jar /home/videostream/VideoStream/target/VideoStream-1.0-SNAPSHOT-jar-with-dependencies.jar > video_stream.log 2>&1 &
echo "$APP_NAME is running"
```

stop.sh
```
#!/bin/bash
APP_NAME=video_stream
pid=`ps -ef | grep $APP_NAME | grep -v grep | awk '{print $2}'`
  
if [ -z "${pid}" ]; then
   echo "$APP_NAME is not running"
else
    echo "kill thread...$pid"
    kill -9 $pid
fi
```

restart.sh
```
#!/bin/bash
APP_NAME=video_stream
pid=`ps -ef | grep $APP_NAME | grep -v grep | awk '{print $2}'`
  
if [ -z "${pid}" ]; then
   echo "$APP_NAME is not running"
else
    echo "kill thread...$pid"
    kill -9 $pid
fi

nohup java -jar /home/videostream/VideoStream/target/VideoStream-1.0-SNAPSHOT-jar-with-dependencies.jar > video_stream.log 2>&1 &
echo "$APP_NAME is running"
```

Grant access to the shell scripts:

```
chmod 777 start.sh
chmod 777 stop.sh
chmod 777 restart.sh
```

at /usr/lib/systemd/system/video_stream.service:

[Unit]
Description=video_stream
After=syslog.target network.target remote-fs.target nss-lookup.target

[Service]
Type=forking
ExecStart=/home/videostream/shell-scripts/start.sh
ExecReload=/home/videostream/shell-scripts/restart.sh
ExecStop=/home/videostream/shell-scripts/stop.sh
PrivateTmp=true

[Install]
WantedBy=multi-user.target

Reload systemctl:
```
sudo systemctl daemon-reload
```
### Docker compose deployment

1. Install docker compose

2. Run:

   ```
   sudo docker compose -f production.yml up -d
   ```

   

# Free domain movies sites

<http://www.publicdomaintorrents.info/index.html>

<https://archive.org/details/feature_films>
