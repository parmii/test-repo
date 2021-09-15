# Documentation for Ticks Consumer Service and Ticks Export Service
Ticks Consumer Service is used to add the new ticks and tick lookup and Ticks Export Service is used to export the tick values to CSV

## Reference Documentation

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3](https://maven.apache.org)

## Approach
- Created two microservices, tick-consumer-service and tick-export-service
- Since we are keeping the data in memory, both the APIs should be up for proper function
- Full API documentation for tick-consumer-service is available on http://localhost:8080/api-docs.html
- Full API documentation for tick-export-service is available on http://localhost:8081/api-docs.html
- tick-consumer-service is used to add new ticks and for tick lookups
- tick-export-service is used to export csv file based on ric if closed price is available
- CSV files are generated at runtime
- After saving the data successfully tick-consumer-service pass the data to tick-export-service
- I am not calling the existing tick lookup API to avoid load on tick-consumer-service while doing the export
- All API are un-protected except Tick Lookup API (`GET /ticks/{ric}`) which is protected by role `ADMIN`
- JWT token is required to call Tick Lookup API (`GET /ticks/{ric}`)
- Generate the token using API `POST /generatetoken`, details given below.
- One static user is created for authentication and authorization with username `admin` and password `admin123`

## Assumptions
- Only Tick Lookup API (`GET /ticks/{ric}`) needs to be protected and rest of the APIs should be open(no authentication or authorization)
- There could be multiple close prices
- Close price is optional
- Since we have only two services, hence there is no need to use Service Discovery
- It is ok to keep the data in memory within both the services
- It is ok to generate the CSV file at runtime when requested
- No authentication or authorization is required for CSV export
- We should not use third party library like Apache POI for csv generation
- We do not need to focus on concurrency

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. 
One way is to execute the `main` method 

For tick-consumer-service execute the `main` method of `com.solactive.ticksconsumerservice.TicksConsumerServiceApplication` class from your IDE.

For tick-export-service execute the `main` method of `com.solactive.ticksconsumerservice.TicksExportServiceApplication` class from your IDE.

Alternatively you can use
the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html)
like so:

```shell
mvn spring-boot:run
```

## Access application

Application tick-consumer-service can be accessed using url http://localhost:8080/

Application tick-export-service can be accessed using url http://localhost:8081/

## API Documentation

Detailed API Documentation of tick-consumer-service available on http://localhost:8080/api-docs.html

Detailed API Documentation of tick-export-service available on http://localhost:8081/api-docs.html

### Available APIs for Tick Consumer Service

The REST APIs to the tick consumer service are described below.

Note: API `GET /ticks/{ric}` is protected, please generate the JWT Token using API `GET /generatetoken`

#### Add new ticks

`POST /ticks`
curl --location --request POST 'http://localhost:8080/ticks' \ --header 'Content-Type: application/json' \ --data-raw '{"TIMESTAMP":"{TIMESTAMP}","PRICE":"{PRICE}","CURRENCY":"{CURRENCY}","RIC" : "{RIC}"}'

#### Generate JWT Token
`POST /generatetoken`
curl --location --request POST 'http://localhost:8080/generateToken' \--header 'Content-Type: application/json' \--data-raw '{"username":"admin","password":"admin123"}'`

#### Fetch all ticks for a given RIC if user has ADMIN role

`GET /ticks/{ric}`
curl --location --request GET 'http://localhost:8080/ticks/ABC' \--header 'Content-Type: application/json' \--header 'Authorization: Bearer {token}'


### Available APIs for Tick export service

The REST APIs to the tick export service are described below.

### Export all ticks for a given RIC if close values is available

`GET /export/{ric}`
curl --location --request GET 'http://localhost:8081/export/{RIC}' \--header 'Content-Type: application/json'
\--header 'Authorization: Bearer {token}'