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
