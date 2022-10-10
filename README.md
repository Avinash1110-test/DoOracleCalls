# DoOracleCalls
#### In this project we'll learn how to call stored procedures, functions and execute queries from oracle database.

## Technologies:
* Java 11
* Spring Boot 2.7.5-SNAPSHOT
* Maven 3.8.6
* Oracle 21.3.0.0.0
## Tools:
* Intellij
* Git
* SQL Developer
* Postman
## Authentication:
* In this project we used Basic Authentication (username & password).
* Due to lack of source we are taking username and password from property file.
* In live we can validate these credentials from SSM Parameter Store/Pipeline.
#### For building and running the application:
* JDK 11.0.16
* Apache Maven 3.8.6
* Oracle Database
#### Running the application locally:

* Clone the Git repository or download zip file.
* Open command promt from the cloned project directory (if downloaded zip file, then unzip file into a directory).
* Run the below commands
```
mvn clean install
mvn package
```
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.oracle.application.Application class from your IDE.

Alternatively you can use the Spring Boot Maven command in command prompt/terminal like :
```
mvn spring-boot:run
```
This application runs on port 8080.

#### Run application in docker container:

1.Build image
```
docker build [OPTIONS] PATH | URL | -
```

2.Run application in container
```
docker run -d --name oracleapplication -p 8080:8080 [image id]
```
## Swagger Documentation:
http://localhost:8080/swagger-ui/index.html#/
## Postman collection:
https://www.getpostman.com/collections/3be00471a14f18dc604c
