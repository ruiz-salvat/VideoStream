## Build image
```
sudo docker build --tag videostream-image .
```

## Run container
```
sudo docker run --publish 8080:8080 --name videostream-container videostream-image
```

## Remove image
```
sudo docker rmi videostream-image
```

## Remove container
```
sudo docker rm videostream-container
```

## 
```

```