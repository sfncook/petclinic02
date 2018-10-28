# S.Cook PetClinic Project
Backend REST API for a sample PetClinic project written mostly from scratch with the help of a few initialization utilities

## Requirements
This has been tested with Gradle v4.10.2
## Build
```console
$ ./gradlew build
```

## Run Tests
(I have not written any tests for this project, but if I had this is how you could run them)
```console
$ ./gradlew test
```

## Run Project
(I have not written any tests for this project, but if I had this is how you could run them)
```console
$ ./gradlew bootRun
```
Once service is up and running you can send requests to it like this:
```console
$ curl -X GET http://localhost:8080/appointments -H 'Content-Type: application/json'
```
