# Project Name

Calculate checkout price  

## Table of Contents

- [Introduction](#introduction)
- [Getting Started](#getting-started)
    - [How to Run](#How-to-Run)
- [About The Service](#about-the-api)
  - [Swagger](#swagger)
  - [Database](#database)
  - [Price Calculation Logic](#checkout-price-calculation-logic)
- [Improvements ](#improvements)
## Introduction

The Project is an e-commerce API that performs a  checkout action. The single endpoint should take a list of watches and return the total cost.


## Getting Started


### How to Run
This application is packaged as a war which has Tomcat embedded. No Tomcat  installation is necessary. 

Clone this repository
```
git clone https://github.com/kemalacar/handelsbanken-case.git
```

Make sure you are using JDK 17 
You can build the project and run the tests by running 

Build the project
```bash
./mvnw clean package
```

Once successfully built, you can run the service by one of these two methods:


```bash
        java -jar  target/interview-handelsbanken-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run 
```

## About the API

There are 3 layer of project. Controller, Service, Repository. Controller is the first layer which take the request.
Logic is coded in the service layer. 

There area 2 services. 

ProductService is responsible for return products. 

CheckoutService is responsible for the calculate checkout price. I created a map which keep usage count of input array. Key is id, Value is count.
For example my input is like  : {"001","002","001"}  map will be like ["001":2 , "002":1 ] Then get products from ProductService by map-keys.

### Checkout price calculation logic
  
```java
 if (productEntity.hasDiscount()) {
    DiscountEntity discount = productEntity.getDiscount();
    long division = requestedQuantity / discount.getQuantity();
    long reminder = requestedQuantity % discount.getQuantity();
    
    BigDecimal discountedPrice = discount.getTotalPrice().multiply(BigDecimal.valueOf(division));
    BigDecimal reminderPrice = productEntity.getUnitPrice().multiply(BigDecimal.valueOf(reminder));

    return discountedPrice.add(reminderPrice);

    } else {
        return productEntity.getUnitPrice().multiply(BigDecimal.valueOf(requestedQuantity));
    }
        

```


### Swagger

Swagger is integrated. Api methods can be seen in that url:  
```
http://localhost:8080/swagger-ui/index.html
```

### Database
H2 Database is used. Endpoint of db : 
```
http://localhost:8080/h2/login.jsp
Username : sa
Password : <no password>
JDBC url : jdbc:h2:mem:test-db
```

#### Checkout Method 
This method takes a list of products id  and return the total cost. 

```
http://localhost:8080/checkout
```
## 

## Improvements
- Database should be changed to another RDBMS (PostgreSQL, Oracle, Mysql etc.)
- Caching System can be added for products. Spring cache or another on memory cache.(Redis,Hazelcast etc.)
- Any authentication layer can be integrated (Spring Security, Keycloak etc.)
- Cookie based login can be integrated.
- Any database  change management solution can be integrated for dml,ddl and dcl scripts ( Liquibase etc.) 
- Project can be dockerized for easy deployment and docker compose file can be added if another app exist.  
- Automation Testing can be improved with using 3rd tool (Selenium, Postman etc.) 
- Unit tests can be implemented with different values and cover edge cases



