# Garage API

**By Eline van der Windt for NOVI Hogeschool**

## Introduction
The Garage Web API is developed to offer a robust backend solution for the registration of customers, vehicles, parts, and associated handling operations, including document uploads for various user roles. This manual serves as a comprehensive guide for the successful setup and operation of the API.

## System Requirements
- **IntelliJ IDEA** (usually comes with JDK; if not, please add it)
- **Maven**
- **Spring Boot**
- **PostgreSQL** for the external database
- **PGAdmin** for effective database management
- **Postman** to check the requests

## Installation Instructions

### 1. Clone the Repository
Begin by cloning the repository into your IntelliJ IDEA workspace.

### 2. Install Dependencies
After cloning, IntelliJ IDEA should prompt you to install the required dependencies. Follow the prompts to ensure all dependencies are properly installed.

### 3. Run the Project
Execute the application by clicking the green play button in IntelliJ IDEA.

### Database Initialization
The application is configured to automatically create the PostgreSQL database schema and populate it with predefined data upon startup. This includes data from the `data.sql` file. All other data should be added manually using Postman or PGAdmin.

**Ensure you connect your database to your IntelliJ project by using the database tool window on the right. Add your PostgreSQL database.**

Update the following properties in the `application.properties` file as required for your environment:
properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_database_username
spring.datasource.password=your_database_password



Test users
These test users are also in the Data.sql file so they are inserrted automatically after you run the project connected to your database.
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


List of tried and tested requests.
With these request you can manually insert your own data.

POST  http://localhost:8080/auth/login

Admin
{
"username": "eline",  // or any other user you created
"password": "wachtwoord1"  // correct password for the chosen user
}

Employee
{
"username": "john_doe",  // or any other user you created
"password": "wachtwoord2"  // correct password for the chosen user
}

Customer
{
"username": "jane_doe",  // or any other user you created
"password": "wachtwoord3"  // correct password for the chosen user
}

{
"username": "joe",  // or any other user you created
"password": "wachtwoord5"  // correct password for the chosen user
}

{
"username": "jack",  // or any other user you created
"password": "wachtwoord6"  // correct password for the chosen user
}

This is what the response should look like:

response 200
{
"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYWNrIiwiaWF0IjoxNzM3NDU2MTc0LCJleHAiOjE3MzgzMjAxNzR9.7SGBZFlVMf7uoLG5BwCicQVk7lFtI9ysylFYjkSuByc"
}

Now the actual token you recieve from postman is the one you use for authenticated requests as an admin, employee or customer. You choose authorisation bearer token and there you fill in the token. Now with every request you make you will be recognised by the system to gain acces.

Add customers;
POST   http://localhost:8080/customers

(Only accessible with a token you put in as written above)

For a new customer you put these details in the body like this:
{
"name": "Johnny Doe",
"address": "123 Main St",
"phoneNumber": "555-1234",
"email": "johnny.doe@example.com"
}
Or this one, for example
{
"name": "Mary Cat Jane",
"address": "Samuel Road 5",
"phoneNumber": "555-4444",
"email": "marycatjane@mail.com"
}
You should get a 200 and confirmation in the output.

See customer by ID
GET http://localhost:8080/customers/1 (id 1 for example)
In the response you should get a 200 and the right info.

Update information customer
PUT http://localhost:8080/customers/1 (update customer with id1)
And put the right updated info in the body like this:

{
"customerId": 1,
"name": "John Doe",
"address": "123 Main St",
"phoneNumber": "555-6789",
"email": "john.doe@example.com"
}

Delete customers by id
DELETE http://localhost:8080/customers/3
You should see a 204 and no content.


(: This is what you put in pg admin to see all SELECT * FROM customer; (or stock etc.) :)

Add stock;
POST   http://localhost:8080/stock

For adding stock you put the details in the body like this:

{
"partName": "WheelX",
"quantity": 100,
"price": 99.99
}

You should get a 201 and confirmation in the output.

See all stock;
GET http://localhost:8080/stock

You should a 200 and all the stock in the output.

See stock by id;
GET http://localhost:8080/stock/3 (id number 3)

You should get a 200 and id number 3 in the output.

Update stock
PUT http://localhost:8080/stock/2 (id number 2 for example)

And you put the updated information in the body like this for example:
{
"partName": "FrameB",
"quantity": 80,
"price": 99.99
}

You should get a 200 and the updated info in the body.

Delete stock
DELETE http://localhost:8080/stock/1 (id number 1 for example)

You should get a 204 and no content in the response.


Add vehicles;
POST http://localhost:8080/vehicles

Put the info in the body like this
{
"customer": {
"customerId": 1  // Link to the existing customer
},
"licensePlate": "XYZ-1234",
"model": "Toyota Camry",
"year": 2020,
"uploadedDocuments": "documents/path/to/document.pdf"  // Optional field
}


See all vehicles;
GET http://localhost:8080/vehicles

You should get a 200 and all vehicles in the output.

Update vehicle
PUT http://localhost:8080/vehicles/{id}

Delete vehicle
DELETE http://localhost:8080/vehicles/{id}


Add inspection;
POST  http://localhost:8080/inspections

Body json format:
{
"vehicle": {
"vehicleID":6
},
"action": "Full Inspection",
"date": "2025-02-05"
}

You should get a 200 and the info in the body like this:
{
"inspectionId": 4,
"vehicle": {
"licensePlate": "XYZ-1234",
"model": "Toyota Camry",
"year": 2020,
"uploadedDocuments": "documents/path/to/document.pdf",
"vehicleID": 6
},
"action": "Full Inspection",
"cost": 150.0,
"date": "2025-02-05"
}


See inspections;
GET http://localhost:8080/inspections

You should get a 200 and all inspections.

See inspections by id;
GET http://localhost:8080/inspections/1

Update inspections
PUT GET http://localhost:8080/inspections/1

For example
{
"vehicle": {
"vehicleID": 5
},
"action": "Full Inspection, Replacement Wheel, Replacement Wipers",
"date": "2025-02-05"
}

You should see a 200 and the confirmation in the output like this;

{
"inspectionId": 1,
"vehicle": {
"licensePlate": "XYZ-1234",
"model": "Toyota Camry",
"year": 2020,
"uploadedDocuments": "documents/path/to/document.pdf",
"vehicleID": 5,
"customer": {
"customerId": 1,
"name": "Jane Doe",
"address": "123 Main St",
"phoneNumber": "555-6789",
"email": "jane.doe@example.com"
}
},
"action": "Full Inspection, Replacement Wheel, Replacement Wipers",
"cost": 300.0,
"date": "2025-02-05"
}

Note the costs total gets calculated in the inspection controller so if you want to add certain operations you can add them in there.


Upload car papers;

POST http://localhost:8080/upload

Then you put body on form-data
Key = file Value = Select file
Key = customerID Value = 1 (for example)

The response should be File uploaded successfully: Screenshot 2025-02-07 at 09.58.14.png


See user by id
GET http://localhost:8080/users/1

Response should be 200 and the info in the output
{
"userId": 1,
"username": "eline",
"password": "$2a$10$uBeRC5SWvpG0EdKrEgOleuifrICHp/aNcG1vElPDd921G329t0YWG",
"role": "ADMIN"
}

See user by username
GET http://localhost:8080/users/username/eline

Response should be 200 and the info in the output
{
"userId": 1,
"username": "eline",
"password": "$2a$10$uBeRC5SWvpG0EdKrEgOleuifrICHp/aNcG1vElPDd921G329t0YWG",
"role": "ADMIN"
}

Update user info
PUT http://localhost:8080/users/1

Delete user
DELETE http://localhost:8080/users/1


### 3. Troubleshooting and contact info
When you are having doubt about functionalities the easiest is to ask Chat GPT or another AI help tool, they usually come with a lot of different options to help you out. Is there still doubt remained or any other questions, don’t hesitate to contact me on elinefwd@gmail.com.



