# Garage

## Introduction
The Garage Web API is designed to provide users with a solid backend for registration of customers,
cars, parts and handlings to do as well as uploading documents that go with it for different users.
This manual will guide you through setting up and running the API successfully.

## System Requirements
You can use Intellij IDEA working with Maven and Springboot and then you will need to install the dependencies
which are listed in the pom file. Also you need to have an external backend like Postgress and an admin tool 
like PG Admin will come in handy too.

## Installation Instructions
First you can clone the repository to your IDEA.
Then you install the dependencies. (usually your IDEA will ask you to do this after you cloned it.)
then you can just run the project using the green play button in your IDEA.

Database Initialization
The application will automatically create the PostgreSQL database schema and populate it with predefined data upon 
startup.
Ensure that your application.properties contains the correct PostgreSQL database connection settings. Update the 
following properties to match your environment:

spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_database_username
spring.datasource.password=your_database_password

Next you may want to log in to the database as a user/ admin/ employee or customer.
You can do that by using the name and password sent in the .env file in the project file.

## Troubleshooting
Common issues and solutions can be found by asking AI.

## Contact Information
Details for getting additional help or reporting issues: email me on elinefwd@gmail.com

## License
All rights reserved 2024.

