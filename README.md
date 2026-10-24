# Spring Boot Redis Demo

A hands-on project demonstrating how to integrate **Redis with Spring Boot** to perform CRUD (Create, Read, Update, Delete) operations on user data using RedisTemplate and Redis Hashes.

## Overview

This project implements REST APIs for managing user records stored in Redis. It demonstrates how a Java Spring Boot application communicates with Redis using Spring Data Redis and the Lettuce client.

## Technologies Used

- Java
- Spring Boot
- Spring Data Redis
- Redis
- Lettuce Redis Client
- Maven
- REST API
- JSON

## Features

- **Create User:** Save a new user with a randomly generated UUID.
- **Get User:** Retrieve a user using their user ID.
- **Get All Users:** Retrieve all users stored in Redis.
- **Update User:** Update an existing user's information.
- **Delete User:** Delete a user using their user ID.

## Redis Data Structure

This project uses a Redis Hash to organize user records.

- **Redis key:** `USER`
- **Hash field:** User ID
- **Hash value:** User object serialized as JSON

Conceptual example:

```text
USER
 ├── U101 → User object
 ├── U102 → User object
 └── U103 → User object
```

Each user ID identifies an individual record within the `USER` hash.

## Project Architecture

```text
Client (Postman)
       |
       v
UserController
       |
       v
UserDao
       |
       v
RedisTemplate
       |
       v
Lettuce Client
       |
       v
Redis Server
```

## API Endpoints

Base URL: `http://localhost:8080/users`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/users` | Create a user |
| GET | `/users/{userId}` | Retrieve a user by ID |
| GET | `/users` | Retrieve all users |
| PUT | `/users/{userId}` | Update a user's information |
| DELETE | `/users/{userId}` | Delete a user |

### Example: Create a User

**Request**

`POST /users`

```json
{
  "name": "Rahul",
  "phone": "9876543210",
  "email": "rahul@example.com"
}
```

The application generates the `userId` automatically using UUID.

The exact JSON response depends on your configured serializers and application setup.

## Key Concepts Learned

- Integrating Redis with Spring Boot.
- Configuring `RedisConnectionFactory` and `RedisTemplate`.
- Understanding the role of the Lettuce Redis client.
- Using Redis Hashes to store multiple user records.
- Configuring serializers for Redis keys, values, and hash entries.
- Implementing REST endpoints using Spring MVC annotations.
- Performing CRUD operations using Spring Data Redis.
- Converting Redis hash entries into a list of Java objects.

## Running the Project

1. Install Java and Maven, or use the Maven Wrapper included in the project.
2. Install Redis or run a Redis server using Docker.
3. Configure your Redis connection settings if Redis is not running at the default local address.
4. Start the Spring Boot application.
5. Test the endpoints using Postman or another REST client.

## What I Learned

This project helped me understand how to connect a Spring Boot application to Redis, configure RedisTemplate, work with Redis Hashes, serialize Java objects, and implement REST APIs for CRUD operations.

## Future Improvements

- Add input validation and centralized exception handling.
- Return appropriate HTTP status codes.
- Separate update-only behavior from create behavior.
- Add integration tests for Redis operations.
- Configure and document Redis persistence and production connection settings.

---

**Project Type:** Backend Development / Learning Project

**Focus:** Spring Boot + Redis Integration
