# Garage API

**By Eline van der Windt for NOVI Hogeschool**

## Introduction
The Garage Web API is developed to offer a robust backend solution for the registration of customers, vehicles, parts, and associated handling operations, including document uploads for various user roles. This manual serves as a comprehensive guide for the successful setup and operation of the API.

## System Requirements
| Requirement      | Description                                    |
|------------------|------------------------------------------------|
| IntelliJ IDEA    | IDE for development (usually comes with JDK) |
| Maven            | Dependency manager                             |
| Spring Boot      | Framework for building applications            |
| PostgreSQL       | External database                              |
| PGAdmin          | Database management tool                       |
| Postman          | Tool for checking HTTP requests                |

## Installation Instructions

### 1. Clone the Repository
Begin by cloning the repository into your IntelliJ IDEA workspace.

### 2. Install Dependencies
After cloning, IntelliJ IDEA should prompt you to install the required dependencies. Follow the prompts to ensure all dependencies are properly installed.

### 3. Run the Project
Execute the application by clicking the green play button in IntelliJ IDEA.

### 4.  Database Initialization Steps
### Step 1: Automatic Database Creation
- The application automatically creates the PostgreSQL database schema and populates it with predefined data upon startup.

### Step 2: Connect Your Database
- Use the **Database Tool Window** in IntelliJ to connect your PostgreSQL database.

### Step 3: Update Application Properties
- Update the `application.properties` file:

- spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
- spring.datasource.username=your_database_username
- spring.datasource.password=your_database_password



## Test Users
| Username     | Password     | Role     |
|--------------|--------------|----------|
| eline        | wachtwoord1  | ADMIN    |
| john_doe     | wachtwoord2  | EMPLOYEE |
| jane_doe     | wachtwoord3  | CUSTOMER |




List of tried and tested requests.
With these request you can manually insert your own data.

## API Endpoints
| HTTP Method | Endpoint                               | Description                                |
|-------------|---------------------------------------|--------------------------------------------|
| POST        | /auth/login                           | Login as a user                           |
| POST        | /customers                             | Add a new customer (Auth required)       |
| GET         | /customers/{id}                       | See customer by ID                        |
| PUT         | /customers/{id}                       | Update customer information               |
| DELETE      | /customers/{id}                       | Delete customer by ID                     |
| POST        | /stock                                 | Add new stock (Auth required)            |
| GET         | /stock                                 | See all stock                            |
| GET         | /stock/{id}                           | See stock by ID                          |
| PUT         | /stock/{id}                           | Update stock information                  |
| DELETE      | /stock/{id}                           | Delete stock by ID                       |
| POST        | /vehicles                              | Add a new vehicle (Auth required)        |
| GET         | /vehicles                              | See all vehicles                         |
| PUT         | /vehicles/{id}                        | Update vehicle information                |
| DELETE      | /vehicles/{id}                        | Delete vehicle by ID                     |
| POST        | /inspections                           | Add new inspection (Auth required)       |
| GET         | /inspections                           | See all inspections                      |
| GET         | /inspections/{id}                     | See inspection by ID                     |
| PUT         | /inspections/{id}                     | Update inspection                         |
| POST        | /upload                                | Upload car papers (form-data required)   |
| GET         | /users/{id}                           | See user by ID                           |
| GET         | /users/username/{username}           | See user by username                     |
| PUT         | /users/{id}                           | Update user information                   |
| DELETE      | /users/{id}                           | Delete user                               |

## Response Formats
# API Responses

## Successful Login Responses
| Response Code | Description        | Example Response                                   |
|---------------|--------------------|----------------------------------------------------|
| 200           | Successful Login    |                                                    |
|               |                    | ```json                                           |
|               |                    | {                                                  |
|               |                    |   "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYWNrIiwiaWF0IjoxNzM3NDU2MTc0LCJleHAiOjE3MzgzMjAxNzR9.7SGBZFlVMf7uoLG5BwCicQVk7lFtI9ysylFYjkSuByc" |
|               |                    | }                                                  |
|               |                    | ```                                              |

## Error Responses
| Response Code | Description                           | Example Response                             |
|---------------|---------------------------------------|----------------------------------------------|
| 401           | Unauthorized - Invalid credentials    |                                              |
|               |                                       | ```json                                    |
|               |                                       | {                                            |
|               |                                       |   "error": "Invalid username or password."  |
|               |                                       | }                                            |
| 404           | Not Found - User does not exist       |                                              |
|               |                                       | ```json                                    |
|               |                                       | {                                            |
|               |                                       |   "error": "User not found."                |
|               |                                       | }                                            |
| 400           | Bad Request – Missing information      |                                              |
|               |                                       | ```json                                    |
|               |                                       | {                                            |
|               |                                       |   "error": "Username and password required." |
|               |                                       | }                                            |

## Troubleshooting and Contact Information
If you have doubts about functionalities, consider asking an AI help tool for assistance. Should there be any questions or concerns, feel free to reach out to me at **elinefwd@gmail.com**.