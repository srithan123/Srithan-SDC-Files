Program 5: Java Standalone Application with MySQL (CRUD Operations)

Objective

This project is a Java Standalone Application that connects to a MySQL database and performs CRUD (Create, Read, Update, Delete) operations on a product table. It's designed to demonstrate Java-Database connectivity using JDBC.

Prerequisites 

Before running this application, make sure you have the following installed:

1.	MySQL Server– Ensure MySQL is installed and running.
2.	MySQL Workbench – Optional, for GUI-based database management.
3.	Java Development Kit (JDK) – Java 8 or later.
4.	VS Code / IntelliJ / Eclipse – Any IDE that supports Java.
5.	MySQL Connector/J (JDBC Driver) – Required for Java-MySQL connectivity.
  

Installation & Setup
Step 1: Install Required Software
•	Download and install MySQL Server: [https://dev.mysql.com/downloads/mysql/](https://dev.mysql.com/downloads/mysql/)
•	Install MySQL Workbench (Optional): [https://dev.mysql.com/downloads/workbench/](https://dev.mysql.com/downloads/workbench/)
•	Install JDK: [https://www.oracle.com/java/technologies/javase-downloads.html](https://www.oracle.com/java/technologies/javase-downloads.html)
•	Install VS Code: [https://code.visualstudio.com/](https://code.visualstudio.com/)
•	Download MySQL Connector/J: [https://dev.mysql.com/downloads/connector/j/](https://dev.mysql.com/downloads/connector/j/)

Step 2: Configure MySQL Database

Use MySQL Workbench or terminal to set up the database.
CREATE DATABASE shoppingdb;

USE shoppingdb;

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    quantity INT NOT NULL
    );

Step 3: Set Up Java Project
1.	Create a folder structure:
java-crud-app/
├── src/
│   └── Main.java
├── lib/
│   └── mysql-connector-j-<version>.jar
└── bin/

2.	Load JDBC Driver in Java:
Class.forName("com.mysql.cj.jdbc.Driver");

3.	Include MySQL Connector/J in the classpath when compiling and running:
javac -cp ".;lib/mysql-connector-j-9.3.0.jar" -d bin src/Main.java
java -cp ".;bin;lib/mysql-connector-j-9.3.0.jar" Main

Step 4: Implement CRUD Operations
•	Create – Insert new product
•	Read – View all products
•	Update – Modify product details
•	Delete – Remove product

Sample Java Code – Database Connection
import java.sql.*;

public class DatabaseConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/crud_db";
        String user = "root";
        String password = "your_password";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL successfully!");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

Step 5: Running the Application
1.	Compile:
javac -cp ".;lib/mysql-connector-j-9.3.0.jar" -d bin src/Main.java

2.	Run:
java -cp ".;bin;lib/mysql-connector-j-9.3.0.jar" Main

3.	If successful, you'll see:
--- Product Management ---
1. Add Product
2. View All Products
3. Update Product
4. Delete Product
5. Exit


## Objective
Build a Java CLI app that performs CRUD operations on a database.

## Technologies Used
- Java
- JDBC
- MySQL/Oracle

## Features
- Add, View, Update, Delete student data
- Menu-driven CLI interface

## Steps to Execute
1. Install Java and JDBC connector.
2. Set up a MySQL/Oracle DB.
3. Compile using: `javac StudentCRUD.java`
4. Run: `java StudentCRUD`

## Folder Contents
- `StudentCRUD.java`: Main CRUD logic


# Experiment 5: Java Standalone CRUD Application (JDBC with Oracle/MySQL)

This project develops a standalone Java application that demonstrates fundamental **CRUD (Create, Read, Update, Delete) operations** on a database table. The application connects to either an **Oracle or MySQL database** using **JDBC (Java Database Connectivity)**, illustrating the core principles of database interaction from a Java console application.

## Features

* **Database Connectivity**: Establishes a connection to a relational database (configured for either Oracle or MySQL).
* **CRUD Operations**:
    * **Create**: Inserts new records into a `STUDENTS` table.
    * **Read**: Retrieves and displays all records from the `STUDENTS` table, or specific records based on criteria.
    * **Update**: Modifies existing records in the `STUDENTS` table.
    * **Delete**: Removes records from the `STUDENTS` table.
* **Prepared Statements**: Utilizes `PreparedStatement` to prevent SQL injection vulnerabilities and improve performance for repetitive queries.
* **Resource Management**: Ensures proper closing of JDBC resources (`Connection`, `Statement`, `PreparedStatement`, `ResultSet`) using `try-with-resources` (or `finally` blocks for older Java versions) to prevent resource leaks.
* **Error Handling**: Includes robust error handling for `SQLException` and other potential issues during database operations.
* **Configurable Database**: Easy to switch between Oracle and MySQL by modifying connection details.

## Technologies Used

* Java SE
* JDBC API
* Oracle Database OR MySQL Database
* Oracle JDBC Driver (`ojdbcX.jar`) OR MySQL Connector/J (`mysql-connector-java-X.X.X.jar`)

## Prerequisites and Database Setup

Before running this project, ensure you have the following:

1.  **Java Development Kit (JDK)**: Installed on your system.
2.  **Database Instance**: A running Oracle Database instance OR MySQL Database instance.
3.  **JDBC Driver**:
    * For **Oracle**: Download `ojdbcX.jar` (e.g., `ojdbc11.jar`) from Oracle's website.
    * For **MySQL**: Download `mysql-connector-java-X.X.X.jar` from MySQL's website.
    * Place the chosen driver JAR file in a `lib/` folder within your project or add it directly to your project's build path.
4.  **Database User and Table**:
    * You need a database user with appropriate privileges (`SELECT`, `INSERT`, `UPDATE`, `DELETE`, `CREATE TABLE`).
    * Create the `STUDENTS` table in your chosen database using the following SQL DDL:

    ```sql
    -- For Oracle
    CREATE TABLE STUDENTS (
        ID NUMBER(10) PRIMARY KEY,
        NAME VARCHAR2(100) NOT NULL,
        AGE NUMBER(3),
        MAJOR VARCHAR2(100)
    );

    -- For MySQL
    CREATE TABLE STUDENTS (
        ID INT PRIMARY KEY,
        NAME VARCHAR(100) NOT NULL,
        AGE INT,
        MAJOR VARCHAR(100)
    );
    ```

    * **Important**: Update the `DB_URL`, `DB_USER`, `DB_PASSWORD`, and `DB_DRIVER_CLASS` constants in `JdbcCrudDemo.java` to match your specific database type and connection details.

## Setup and Running

1.  **Clone the Repository (or create manually):**
    ```bash
    git clone [https://github.com/your-username/Experiment-05_Java-standalone_CRUD_App.git](https://github.com/your-username/Experiment-05_Java-standalone_CRUD_App.git)
    cd Experiment-05_Java-standalone_CRUD_App
    ```

2.  **Add JDBC Driver to Project:**
    * Create a `lib/` directory in your project root if it doesn't exist.
    * Place the chosen `ojdbcX.jar` (for Oracle) or `mysql-connector-java-X.X.X.jar` (for MySQL) into the `lib/` folder.
    * **In your IDE (IntelliJ IDEA/Eclipse):** Add this JAR file to your project's build path (or classpath).
        * **IntelliJ IDEA:** Right-click on `ojdbcX.jar` / `mysql-connector-java-X.X.X.jar` -> `Add as Library...`.
        * **Eclipse:** Right-click on Project -> `Properties` -> `Java Build Path` -> `Libraries` tab -> `Add JARs...` (or `Add External JARs...`).

3.  **Update Database Configuration:**
    * Open `src/main/java/com/example/jdbc/JdbcCrudDemo.java`.
    * **Uncomment and update** the appropriate `DB_URL`, `DB_USER`, `DB_PASSWORD`, and `DB_DRIVER_CLASS` variables for your chosen database (Oracle or MySQL). Make sure the other database's configuration is commented out.

4.  **Run the Application:**
    * Open your IDE.
    * Navigate to `src/main/java/com/example/jdbc/JdbcCrudDemo.java`.
    * Right-click on `JdbcCrudDemo.java` and select `Run 'JdbcCrudDemo.main()'`.

## Expected Output

The program will print messages to the console indicating:

* JDBC driver registration.
* Connection success or failure.
* Results of each INSERT, SELECT, UPDATE, and DELETE operation (e.g., "X rows inserted/updated/deleted", "Students Table: ...").
* Any SQL errors encountered.

## Verification

After running the Java program, you can connect to your database using a client tool (e.g., SQL Developer for Oracle, MySQL Workbench/CLI for MySQL) and run:

```sql
SELECT * FROM STUDENTS;