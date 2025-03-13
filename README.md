# UniClub Project

## Overview
UniClub is a Spring Boot-based project that provides authentication and authorization functionalities. It uses JWT for secure token-based authentication and custom authentication providers to handle user login and role-based access control.

## Technologies Used
- Java
- Spring Boot
- Spring Security
- Maven
- JWT (JSON Web Token)

## Project Structure
- `src/main/java/com/dream/uniclub/security`: Contains security configurations and custom authentication providers.
- `src/main/java/com/dream/uniclub/controller`: Contains REST controllers for handling HTTP requests.
- `src/main/java/com/dream/uniclub/request`: Contains request DTOs.
- `src/main/java/com/dream/uniclub/response`: Contains response DTOs.
- `src/main/java/com/dream/uniclub/service`: Contains service classes for business logic.
- `src/main/java/com/dream/uniclub/utils`: Contains utility classes.

## Key Components
### SecurityConfig.java
Configures Spring Security, including password encoding, authentication manager, and security filter chain.

### AuthController.java
Handles authentication requests and generates JWT tokens upon successful login.

### CustomAuthenticationProvider.java
Custom authentication provider that validates user credentials and assigns roles.

### AuthRequest.java
DTO for authentication requests, including validation annotations.

## Running the Project
1. Clone the repository.
2. Navigate to the project directory.
3. Run `mvn clean install` to build the project.
4. Run the application using your IDE or `mvn spring-boot:run`.

## Endpoints
- `POST /authen`: Authenticates the user and returns a JWT token.
- `GET /product`: Accessible only by users with the `USER` role.

## Example Request
### Authentication Request
```json
POST /authen
{
  "email": "user@example.com",
  "password": "password123"
}