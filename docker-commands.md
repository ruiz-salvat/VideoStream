## Build image
```
sudo docker build --tag videostream-image .
sudo docker build --tag front-app-image .
```

## Run container
```
sudo docker run --publish 8080:8080 --name videostream-container videostream-image
sudo docker run --publish 3000:3000 --name front-app-container front-app-image
```

## Remove image
```
sudo docker rmi videostream-image
sudo docker rmi front-app-image
```

## Remove container
```
sudo docker rm videostream-container
sudo docker rm front-app-container
```

## Docker compose dev
```
sudo docker compose up
sudo docker compose down
```

## Docker compose prod
```
sudo docker compose -f production.yml up -d
sudo docker compose -f docker-compose.yml -f production.yml up -d
```