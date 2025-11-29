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

## 2. System Requirements| Requirement       | Description                                              | Versie(s) & Downloadlink(s)                                                                            |
| Requirement       | Beschrijving | Versie(s) & Downloadlink(s) |
|-------------------|----------------|----------------------------|
| IntelliJ IDEA     | IDE voor ontwikkeling (meestal inclusief JDK) | Versie: 2023.2+ [Download](https://www.jetbrains.com/idea/download/) |
| Maven             | Dependency management & build tool | Versie: 3.8.6+ [Download](https://maven.apache.org/download.cgi) |
| Spring Boot       | Framework voor het bouwen van applicaties | Versie: 3.1.2 [Documentatie & download](https://spring.io/projects/spring-boot) |
| PostgreSQL        | External database voor je app | Versie: 15+ [Download](https://www.postgresql.org/download/) |
| PGAdmin           | GUI voor databasebeheer | Versie: 6.15+ [Download](https://www.pgadmin.org/download/) |
| Postman           | Tool voor HTTP verzoeken testen | Versie: 10.15+ [Download](https://www.postman.com/downloads/) |


## 3. Installation Instructions

### 1. Clone the Repository
Begin by cloning the repository into your IntelliJ IDEA workspace.

### 2. Install Dependencies
After cloning, IntelliJ IDEA should prompt you to install the required dependencies. Follow the prompts to ensure all dependencies are properly installed.

### 3.  Database Initialization Steps

### Step 1: Connect Your Database
- Use the **Database Tool Window** in IntelliJ to connect your PostgreSQL database.

### Step 2: Update Application Properties
- Update the `application.properties` file:

- spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
- spring.datasource.username=your_database_username
- spring.datasource.password=your_database_password

### (optional): Use PG Admin for maintaining your database.
Instead of using the terminal you can also use PG admin for a clear view and easy 
maintenance of the database.

### 4. Run the Project
Execute the application by clicking the green play button in IntelliJ IDEA.


### 5. Test Users
| Username     | Password     | Role     |
|--------------|--------------|----------|
| eline        | wachtwoord1  | ADMIN    |
| john_doe     | wachtwoord2  | EMPLOYEE |
| jane_doe     | wachtwoord3  | CUSTOMER |



### 5. Testing Endpoints and JWT tokens

Testing Endpoints is possible by importing the .json file into Postman. After every successfully user login you will receive
a JWT token in the response. This one you can copy and use as authentication, 
Bearer token and send it with all the requests that need authentication. (Which is all of them except for the user login) 
You need to generate your own secure key of 64 characters and fill it in the JwtUtill class in the security file, and you need 
to keep that one private.

| Endpoint                                   | Methode   | Beschrijving                                                 | Voorbeeld URL                                              |
|--------------------------------------------|-----------|--------------------------------------------------------------|------------------------------------------------------------|
| `/auth/login`                            | POST      | Inloggen (voor token verkrijgen)                              | `/auth/login` (met username en password in body)          |
| `/customers`                             | POST      | Nieuwe klant toevoegen (met DTO)                               | `/customers` (met CustomerDto in body)                     |
| `/customers/{id}`                        | GET       | Klant op ID opvragen                                          | `/customers/1`                                             |
| `/customers/{id}`                        | PUT       | Bestaande klant bijwerken (met DTO)                            | `/customers/1` (met CustomerDto in body)                    |
| `/customers/{id}`                        | DELETE    | Klant verwijderen                                             | `/customers/1`                                             |
| `/vehicles`                              | POST      | Nieuw voertuig toevoegen (met DTO)                              | `/vehicles` (met VehicleDto in body)                         |
| `/vehicles/{id}`                         | GET       | Voertuig op ID opvragen                                       | `/vehicles/1`                                              |
| `/vehicles/{id}`                         | PUT       | Bestaand voertuig aanpassen (met DTO)                           | `/vehicles/1` (met VehicleDto in body)                         |
| `/vehicles/{id}`                         | DELETE    | Voertuig verwijderen                                           | `/vehicles/1`                                              |
| `/upload`                                | POST      | Bestand uploaden gekoppeld aan klant (met DTO en file)       | `/upload` (multipart/form-data met file en customerId)   |
| `/upload/documents`                      | GET       | Alle documenten ophalen                                       | `/upload/documents`                                       |
| `/upload/documents/{id}`                 | GET       | Document op ID ophalen                                          | `/upload/documents/5`                                       |
| `/upload/documents/{id}`                 | DELETE    | Document verwijderen                                            | `/upload/documents/5`                                       |
| `/inspections`                           | GET       | Alle inspecties ophalen                                        | `/inspections`                                            |
| `/inspections/{id}`                      | GET       | Inspectie op ID ophalen                                         | `/inspections/123`                                          |
| `/inspections`                           | POST      | Nieuwe inspectie plaatsen (met DTO)                             | `/inspections` (met InspectionDto in body)                 |
| `/inspections/{id}`                      | PUT       | Bestaande inspectie bijwerken (met DTO)                         | `/inspections/123` (met InspectionDto in body)             |
| `/inspections/{id}`                      | DELETE    | Inspectie verwijderen                                           | `/inspections/123`                                          |
| `/stock`                                | POST      | Nieuwe stock item toevoegen (met DTO)                            | `/stock` (met StockDto in body)                              |
| `/stock/{id}`                           | GET       | Stock item op ID ophalen                                        | `/stock/1`                                                  |
| `/stock/{id}`                           | PUT       | Stock item bijwerken (met DTO)                                    | `/stock/1` (met StockDto in body)                            |
| `/stock/{id}`                           | DELETE    | Stock item verwijderen                                           | `/stock/1`                                                  |



### 6.  Troubleshooting and Contact Information
If you have doubts about functionalities, consider asking an AI help tool for assistance. Should there be any questions
or concerns, feel free to reach out to me at **elinefwd@gmail.com**.