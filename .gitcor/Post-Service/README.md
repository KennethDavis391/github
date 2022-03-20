# Post Service

## Overview

Post Service is responsible for handling posts and related content, including images. It handles the things that a user *does*, including the CRUD of posts, but also the modification of post meta-data such as likes. Requests made to the Post Service are authenticated by a call to User Service validation.

## Technologies

- Java
- Spring Boot
- Spring Web
- Spring WebFlux
- Spring Data JPA
- Log4J
- Netflix Eureka
- JUnit4
- Mockito
- Lombok
- Hibernate

# Getting Started with Post-Service

1. Install and run Discovery (unless you want to temporarily disable self-registration by modifying the YAML config and removing the Eureka dependency in the POM)

2. Install and run Gateway (technically optional, but recommended to avoid manually setting location of services)

3. Install and run User-Service (unless you want to temporarily remove JWT validation as that requires the user-service be up)

4. Clone repo from https://github.com/Revature-Reverse/Post-Service.git

5. Open in IDE of your choice


6. Set environment variables:

DB_USERNAME is the username for your user on your database

DB_URL is the address of your database with the appropriate prefix (jdbc:postgresql://reverse.xxxxxxx.us-east-1.rds.amazonaws.com/postgres, for example)

DB_PASSWORD is the password for your user on your database

EUREKA_URI is the IP:port address of your Discovery service, this should be set in production but shouldn't need to be set for local testing

VALIDATION is the full address for accessing your USER-SERVICE validation endpoint (http://localhost:8084/users/validate if you are running vanilla Gateway/Discovery/User-Service/Post-Service locally)

AWS_ACCESS_KEY and AWS_SECRET_KEY are necessary to set up programmatic access to the S3 bucket for storing images.

7. Create run configuration with PostServiceApplication.java as the main class

8. Run
