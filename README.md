# java-spring-boot-hibernate-mysql

Project contains all the possible hibernate JPA entities relationships backed by MySQL database.

#### Features
- MySQL schema 
- @OneToOne
- @OneToMany
- @ManyToOne
- @ManyToMany  
- Lombok

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


## Good reads
- https://thorben-janssen.com/jpa-generate-primary-keys/
- https://ankitkamboj18.medium.com/a-guide-to-jpa-with-hibernate-relationship-mappings-onetoone-onetomany-manytoone-310ce31df3f6

## Notes

A relationship may be optional or mandatory. Considering the `@OneToMany` side — 
it is always optional, and we can’t do anything about it. 
The `@ManyToOne` side, on the other hand, offers us the option of making it mandatory.

The annotation `@JoinColumn` indicates that this entity is the owner of the relationship 
(that is: the corresponding table has a column with a foreign key to the referenced table), 
whereas the attribute `mappedBy` indicates that the entity in this side is the inverse of the relationship, 
and the owner resides in the "other" entity.