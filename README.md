# Garage API

Index

1. Introduction.

2. System requirements.

3. Installation instrusctions.

4. Test users.

5. Testing endpoints.

6. Troubleshooting and contactinfo.



## 1. Introduction
The Garage Web API is developed to offer a robust backend solution for the registration of customers, vehicles, parts, 
and associated handling operations, including document uploads for various user roles. This manual serves as a comprehensive
guide for the successful setup and operation of the API.

## 2. System Requirements
| Requirement      | Description                                    |
|------------------|------------------------------------------------|
| IntelliJ IDEA    | IDE for development (usually comes with JDK) |
| Maven            | Dependency manager                             |
| Spring Boot      | Framework for building applications            |
| PostgreSQL       | External database                              |
| PGAdmin          | Database management tool                       |
| Postman          | Tool for checking HTTP requests                |

## 3. Installation Instructions

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



## 4. Test Users
| Username     | Password     | Role     |
|--------------|--------------|----------|
| eline        | wachtwoord1  | ADMIN    |
| john_doe     | wachtwoord2  | EMPLOYEE |
| jane_doe     | wachtwoord3  | CUSTOMER |



## 5. Testing Endpoints

Testing Endpoints is possible by importing the .json file into Postman. After every successfull user login you will recieve
a JWT token in the response. This one you can copy and use as autentification, 
Bearer token and send it with all the requests that need authentifaction. (Wich is all of them except for the user login)


## 6.  Troubleshooting and Contact Information
If you have doubts about functionalities, consider asking an AI help tool for assistance. Should there be any questions
or concerns, feel free to reach out to me at **elinefwd@gmail.com**.