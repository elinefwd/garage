# Garage

## Introduction
The Garage Web API is developed to offer a robust backend solution for the registration of customers, vehicles, parts, and associated handling operations, including document uploads for various user roles. This manual serves as a comprehensive guide for the successful setup and operation of the API.

## System Requirements
The development environment for this application utilizes IntelliJ IDEA, along with Maven and Spring Boot. It is essential to install the necessary dependencies as specified in the POM file. Additionally, an external database system, specifically PostgreSQL, is required, along with an administrative tool such as PGAdmin for effective database management.

## Installation Instructions
Clone the Repository:

Begin by cloning the repository into your IntelliJ IDEA workspace.
Install Dependencies:

After cloning, IntelliJ IDEA should prompt you to install the required dependencies. Follow the prompts to ensure all dependencies are properly installed.
Run the Project:

Execute the application by clicking the green play button in IntelliJ IDEA.
Database Initialization
The application is configured to automatically create the PostgreSQL database schema and populate it with predefined data upon startup.

It is crucial to verify that your application.properties file contains the correct connection settings for the PostgreSQL database. Update the following properties as required for your environment:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_database_username
spring.datasource.password=your_database_password
```

## Test users
```
[
{
"username": "eline",
"password": "wachtwoord1",
"role": "ADMIN"
},
{
"username": "john_doe",
"password": "wachtwoord2",
"role": "EMPLOYEE"
},
{
"username": "jane_doe",
"password": "wachtwoord3",
"role": "CUSTOMER"
}
]
```
## Postman Collection
```json
{
  "info": {
    "name": "User Index Management",
    "_postman_id": "32145-67890-fghijk",
    "description": "Collection of requests for user management, including retrieving the list of test users.",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Get User List",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/api/users",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "users"]
        }
      },
      "response": []
    },
    {
      "name": "Create Admin User",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json",
            "description": ""
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"username\": \"eline\", \"password\": \"wachtwoord1\", \"role\": \"ADMIN\"}"
        },
        "url": {
          "raw": "http://localhost:8080/api/users",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "users"]
        }
      },
      "response": []
    },
    {
      "name": "Create Employee User",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json",
            "description": ""
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"username\": \"john_doe\", \"password\": \"wachtwoord2\", \"role\": \"EMPLOYEE\"}"
        },
        "url": {
          "raw": "http://localhost:8080/api/users",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "users"]
        }
      },
      "response": []
    },
    {
      "name": "Create Customer User",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json",
            "description": ""
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"username\": \"jane_doe\", \"password\": \"wachtwoord3\", \"role\": \"CUSTOMER\"}"
        },
        "url": {
          "raw": "http://localhost:8080/api/users",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "users"]
        }
      },
      "response": []
    },
    {
      "name": "Insert Part A",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json",
            "description": ""
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"part_name\": \"Part A\", \"quantity\": 100}"
        },
        "url": {
          "raw": "http://localhost:8080/api/parts",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "parts"]
        }
      },
      "response": []
    },
    {
      "name": "Insert Part B",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json",
            "description": ""
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"part_name\": \"Part B\", \"quantity\": 50}"
        },
        "url": {
          "raw": "http://localhost:8080/api/parts",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "parts"]
        }
      },
      "response": []
    }]}
``` 


## list of REST endpoints
```
GET /api/users

Description: Retrieves the list of users.
POST /api/users

Description: Creates a new admin user.
Request Body Example:
json

{
"username": "eline",
"password": "wachtwoord1",
"role": "ADMIN"
}
POST /api/users

Description: Creates a new employee user.
Request Body Example:
json

{
"username": "john_doe",
"password": "wachtwoord2",
"role": "EMPLOYEE"
}
POST /api/users

Description: Creates a new customer user.
Request Body Example:
json

{
"username": "jane_doe",
"password": "wachtwoord3",
"role": "CUSTOMER"
}
Summary of Endpoints
GET: /api/users
POST: /api/users (for creating an admin, employee, and customer user)

POST /api/parts

Description: Inserts a new part into the inventory.
Request Body Example:
json

{
"part_name": "Part A",
"quantity": 100
}
POST /api/parts

Description: Inserts another part into the inventory.
Request Body Example:
json

{
"part_name": "Part B",
"quantity": 50
}
Summary of New Endpoints
POST: /api/parts (for adding new parts to inventory)
```

## Troubleshooting and Contact Information
For further assistance or to report issues, please reach out via email at elinefwd@gmail.com.

## License
All rights reserved, 2024.

