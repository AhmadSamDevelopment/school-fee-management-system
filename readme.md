Sure, here's the updated README with the Swagger UI reference in place.

# School Fee Management System

## Technologies Used

* Java, Spring Boot
* Spring Data JPA (for database communication)
* Project Lombok (for generating getters/setters)
* H2 Database (in-memory)
* Unit tests, Mockito (for automated testing)

## Architecture

This project implements a microservices architecture with three main services:

* **Student Service:** Manages student details, including grades.
* **Catalog Service:** Stores and provides information about the fee catalog.
* **Fee Collection Service:** Orchestrates the fee collection process, interacting with the Student Service and Catalog Service.

## Entity Relationship Diagrams (ERDs)

### Catalog Service

![Catalog Service ERD](https://i.ibb.co/qYRvd1G/catalog-service.png)

### Fee Collection Service

![Fee Collection Service ERD](https://i.ibb.co/fntWG52/fee-collection-service.png)

### Student Service

![Student Service ERD](https://i.ibb.co/yBkM8gk/student-service.png)

## API Documentation

Detailed API documentation is available through Swagger UI. After running the project, you can access the Swagger UI for each service at the following links:

* **Student Service:** http://localhost:8081/swagger-ui/index.html
* **Catalog Service:** http://localhost:8082/swagger-ui/index.html
* **Fee Collection Service:** http://localhost:8083/swagger-ui/index.html

## How to Run the Project

### 1. Running with Maven

**Prerequisites:**

* Java 17+
* Maven

**Steps:**

1. Navigate to each service's directory (e.g., `cd student-service`).
2. Build the project: `mvn clean package`
3. Run the service: `mvn spring-boot:run`
4. Repeat for the other two services (`catalog-service` and `fee-collection-service`).

### 2. Running with Docker Compose

**Prerequisites:**

* Docker
* Docker Compose

**Steps:**

1. Navigate to the project's root directory (where the `docker-compose.yml` file is located).
2. Build and start the services: `docker-compose up --build`
3. Access the services via the configured ports (check `docker-compose.yml`).
4. Stop the services: `docker-compose down` 
