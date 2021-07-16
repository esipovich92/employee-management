
# Employee Management Service

Employee Management Service allows to create/update/view employees

## Dependencies and versions

- Java 11
- Spring Boot 2.3.12
- H2 DB

## DB configuration

- H2 console url: http://localhost:8080/h2-console
- db url: jdbc:h2:mem:employee
- username: admin
- password: changeIt

## Swagger

Swagger is accessible after the app started by the following url:
- http://localhost:8080/management/swagger-ui.html

## Requests examples
Create employee:
```
curl --location --request POST 'localhost:8080/management/employee' \
--header 'Content-Type: application/json' \
--data-raw '{
    "jobTitle": "Software Engineer"
}'
```

Update employee:
```
curl --location --request PUT 'localhost:8080/management/employee/2' \
--header 'Content-Type: application/json' \
--data-raw '{
    "jobTitle": "Updated Job Title",
    "firstName": "Updated First Name",
    "lastName": "Updated Last Name",
    "photoURL": "Updated URL"
}'
```

Get employees:
```
curl --location --request GET 'localhost:8080/management/employee?sort=lastName,desc&page=0&size=1'
```


