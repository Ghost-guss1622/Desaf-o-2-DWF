This is a RESTful API built with Spring Boot, Hibernate, and Spring Data JPA for managing users and subscriptions. It includes CRUD operations, data validation with DTOs, centralized error handling, and automatic documentation with Swagger.

## Features
- CRUD for Users and Subscriptions  
- DTOs with validation annotations (@NotBlank, @NotNull, @Email, @Size)  
- Global error handling with @ControllerAdvice  
- Swagger UI for API documentation  
- Layered architecture (entities, repositories, controllers)  

## Installation
Requirements: Java 17+, Maven, MySQL  

Steps:  
1. Clone the repository.  
2. Configure src/main/resources/application.properties:  
   spring.datasource.url=jdbc:mysql://localhost:3306/gestor_suscripciones
   spring.datasource.username=root
   spring.datasource.password=
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

3. Run the project:  
   mvn spring-boot:run

4. Access the API at http://localhost:8080 and Swagger UI at http://localhost:8080/swagger-ui.html

## Endpoints
Users  
- GET /api/users  
- GET /api/users/{id}  
- POST /api/users  
- PUT /api/users/{id}  
- DELETE /api/users/{id}  

Subscriptions  
- GET /api/subscriptions  
- GET /api/subscriptions/{id}  
- POST /api/subscriptions  
- PUT /api/subscriptions/{id}  
- DELETE /api/subscriptions/{id}  

## Example Request
POST /api/users
Content-Type: application/json

{
  "nombre": "Rene",
  "apellido": "Aparicio",
  "email": "reneaparicioruiz@example.com"
}

## Example Error Response
{
  "status": 400,
  "error": "Bad Request",
  "message": "Field 'email' must be a valid email address",
  "timestamp": "2025-09-22T10:30:00"
}

## Documentation
Full API documentation available in Swagger UI at http://localhost:8080/swagger-ui.html


