# COMP228 - Hospital Management System (JavaFX • JDBC • MySQL CRUD)

**Author:** Riasat Raihan Noor  
**Course:** COMP228 - Java Programming (Centennial College)  
**Technology:** Java 25, JavaFX 25, JDBC, MySQL

## Overview
A desktop **Hospital Management System** module designed to maintain patient registration and clinical records. Built with JavaFX for the user interface and JDBC (`PreparedStatement` and `ResultSet` with `try-with-resources`) to perform full **Create, Read, Update, Delete (CRUD)**, search, and clinical workflow operations against a MySQL database (`hospital_db`).


## Architecture & Optional Extensions Implemented
The project follows a clean separation of concerns across three classes and implements all optional extensions:
* **`Main.java` (UI & Event Controller):**
  * **`BorderPane` Root Container:** Top `VBox` header, Center split `HBox` (Left Form + Right Table), and Bottom status bar.
  * **`GridPane` Form:** Input fields for `Patient ID` (read-only), `Full Name`, `Age` (`0–125`), `Gender` (`ComboBox`), `Phone`, `Diagnosis`, `Department` (`ComboBox`), `Patient Status` (`ComboBox`), and `Admission Date` (`DatePicker`, non-future).
  * **`TableView` & Search/Filter Bar:** Real-time viewing of patient records with keyword search (`name` or `phone`) and filtering by `Admission Date`.
  * **Clinical Actions:** Includes a **View Details** modal summary popup (`Alert`) and a one-click **Discharge Patient** action.
* **`PatientDAO.java` (Data Access Object):** Isolates all JDBC database connectivity, parameterized SQL queries, and `ResultSet` mapping.
* **`Patient.java` (Model Class):** Encapsulates patient attributes bound to the JavaFX `TableView` columns.

## Project Structure
    Wk8_Lab_RiasatN/
    ├── src/
    │   ├── application/
    │   │   ├── Main.java
    │   │   ├── Patient.java
    │   │   ├── PatientDAO.java
    │   │   └── application.css
    │   └── module-info.java
    ├── database.sql
    └── README.md

## How to Run

### 1. Database Setup (MySQL)
Open MySQL Workbench and execute `database.sql` to create the `hospital_db` schema, the `patients` table, and initial fictional records:
```sql
SOURCE database.sql;
```
*(Ensure your MySQL credentials in `PatientDAO.java` match your local server: `DB_USER = "root"`, `DB_PASSWORD = "root"`).*

### 2. Running in Eclipse IDE
1. Import the project via **File -> Import -> General -> Existing Projects into Workspace**.
2. Ensure **JavaFX SDK 25** and **MySQL Connector/J (`mysql-connector-j.jar`)** are configured on the project's Build Path (**Libraries -> Modulepath / Classpath**).
3. Verify `src/module-info.java` contains:
   ```java
   module Wk8_BorderPane {
       requires javafx.controls;
       requires javafx.graphics;
       requires javafx.base;
       requires java.sql;

       opens application to javafx.graphics, javafx.fxml, javafx.base;
   }
   ```
4. Right-click `src/application/Main.java` and select **Run As -> Java Application**.