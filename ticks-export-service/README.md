# Ticks Export Service

## Reference Documentation

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method
in the `com.solactive.ticksconsumerservice.TicksExportServiceApplication` class from your IDE.

Alternatively you can use
the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html)
like so:

```shell
mvn spring-boot:run
```

## Access application

Application be accessed using url http://localhost:8081/

## API Documentation

Documentation available on http://localhost:8081/api-docs.html

### Available APIs

The REST APIs to the tick consumer service are described below.

### Export all ticks for a given RIC if close values is available

`GET /export/{ric}`
curl --location --request GET 'http://localhost:8081/export/{RIC}' \--header 'Content-Type: application/json'
\--header 'Authorization: Bearer {token}'