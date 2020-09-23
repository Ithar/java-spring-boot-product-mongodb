# java-spring-mongodb
#### 2020-09-22 

This repository contains a Spring Boot example project for MongoDB held on docker.


### Docker 
```
docker volume create mongo-db-trader
docker run -p 27017:27017 -v mongo-db-trader:/data/db -d mongo
```

* Run Application
* http://localhost:8080/trade

