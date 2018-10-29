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

## Explanation of Project
This project was initialized using [start.spring.io](https://start.spring.io/).  It uses Gradle, Spring Boot, an H2 in-memory SQL database (so data does not persist between runs), Hibernate for the JPA ORM, and Lombok for annotation pre-procession and code generation.

## Feature and Bug List
- BE & FE tests (unit, integrated, etc.)
- Enter key should save editing objects
- Only edit one object at a time
- Delete objects
- Substantial duplicate code. DRY
- Normalize data - associative array for pets & vets?  Single, cohesive datastruct?
- Table cell widths shift dynamically.  Give them a fixed size.  to recreate: Change all pets in appt list to Fido and see how the width reduces dynamically
- Reactive layout
- sort by name
- CORS annotations are repeated and wide-open.  Reevaluate security
- Better branding and icons
- Timezone hardcoded to PST ("America/Los_Angeles")
- MaterialUI warning: '
index.js:1452 Warning: Material-UI: you are using the deprecated typography variants that will be removed in the next major release.'
- Change error responses to enumerators and format error message in UI
- Validate that appointment time is sufficient - e.g. >=30 minutes
- Validate appointment times in FE w/ better visual feedback
