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

Subsequently, you may log into the database as a user, admin, employee, or customer using the credentials provided in the .env file located in the project directory.

## Troubleshooting and Contact Information
For further assistance or to report issues, please reach out via email at elinefwd@gmail.com.

## License
All rights reserved, 2024.

