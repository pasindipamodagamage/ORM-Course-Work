
---

# 📚 ORM Course Work - Student Management System

Welcome to the **ORM Course Work** project! This system is designed to efficiently manage student and course data using Java, Hibernate, and JavaFX, featuring a layered architecture and secure authentication.

---

## 🛠 Features

- **Student Management**: Add, edit, delete, and view student information.
- **Course Management**: Create and manage course records.
- **Secure Login**: Implements BCrypt for password hashing.
- **Layered Architecture**: Maintains clean and modular code structure.
- **Database Integration**: Uses Hibernate for ORM with HQL for queries.

---

## 🚀 Getting Started

### Prerequisites

- **Java JDK** (version 11 or higher)
- **MySQL**: A running MySQL instance
- **Maven**: For dependency management

### Setup Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/pasindipamodagamage/ORM-Course-Work.git
   cd ORM-Course-Work
   ```

2. Configure the database:
   - Create a MySQL database named `student_management`.
   - Update `hibernate.properties` in the `src/main/resources` directory with your MySQL credentials.

3. Run the project:
   ```bash
   mvn clean install
   mvn exec:java
   ```

---

## 📂 Folder Structure

- **`src/main/java`**: Contains all Java source files.
- **`src/main/resources`**: Configuration files, including Hibernate settings.
- **`pom.xml`**: Maven configuration for dependencies.

---

## ❤️ Contributors

- [Pasindi Pamoda](https://github.com/pasindipamodagamage)

---
