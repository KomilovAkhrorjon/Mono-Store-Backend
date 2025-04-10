# Mono Store Backend

Mono Store Backend is a Java Spring Boot application designed to manage the backend operations of the Mono Store. It provides RESTful APIs for handling products, orders, and user management.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)

## Features

- Product management: Create, read, update, and delete products.
- Order processing: Handle customer orders and track their statuses.
- User authentication and authorization.

## Prerequisites

Ensure you have the following installed:

- [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Gradle](https://gradle.org/install/)
- [PostgreSQL](https://www.postgresql.org/download/) or your preferred relational database

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/KomilovAkhrorjon/Mono-Store-Backend.git

2. **Navigate to the project directory:**

    ```bash
    cd Mono-Store-Backend
    
3. **Configure the Database:**

   Update the application.properties file located in src/main/resources with your database credentials:

    ```bash
    spring.datasource.url=jdbc:postgresql://localhost:5432/your-database-name
    spring.datasource.username=your-username
    spring.datasource.password=your-password

4. **Build the Project:**
   
    ```bash
   ./gradlew build

5. **Run the Application:**

   ```bash
   ./gradlew bootRun
