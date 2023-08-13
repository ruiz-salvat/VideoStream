## Build image
```
sudo docker build --tag <image_name> .
```

## Run container
```
sudo docker run --publish 8080:8080 --name <container_name> <image_name>
```

## Remove image
```
sudo docker rmi <image_name>
```

## Remove container
```
sudo docker rm <container_name>
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

## Restart service
```
sudo docker compose restart
sudo docker compose restart <service_name>
```

## Rebuild service 
```
sudo docker compose build <service_name>

sudo docker compose stop <service_name>
sudo docker compose rm <service_name>
sudo docker compose build <service_name>
sudo docker compose create <service_name>
sudo docker compose start <service_name>
```

## Recreate
```
sudo docker compose -f production.yml up --force-recreate
```