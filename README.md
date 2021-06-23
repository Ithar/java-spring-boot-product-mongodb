# java-spring-boot-hibernate-mysql

Project contains all the possible hibernate JPA entities relationships backed by MySQL database.

#### Features
- MySQL schema loader
- @OneToOne
- @OneToMany
- @ManyToOne
- @ManyToMany  

## Installation
> docker run -p3306:3306 --name hibernate-mysql -v /Users/ithar.malik/dev/personal/java/java-spring-boot-hibernate-mysql/volume:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:8.0.25

> docker exec -it hibernate-mysql bash -l

> mysql -uroot -pmy-secret-pw  
   
## Application Stack

Stack  | version |
--- | --- |  
*Java* | 1.8
*SpringBoot* |  2.1.0.RELEASE
*Frontend* | n/a
*DB* | my-sql:8.0.25 (docker)
*Server* | n/a
*Build Tool* | Maven
*CI* | n/a
*Code Coverage* | n/a
*Build env* | docker run 

   
## Application Run (MySQL DB) 
> docker start hibernate-mysql

## Application Run 

> Run Application src/main/java/com/ithar/malik/hibernate/main/XToY


## Application GIT branches
- master

## Application profile

## Further enhancements 