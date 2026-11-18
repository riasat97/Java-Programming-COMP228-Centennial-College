# COMP228 - Week 10 In-Class Lab: Employment Application (JavaFX • JDBC)

**Author:** Riasat Raihan Noor  
**Course:** COMP228 - Java Programming (Centennial College)  
**Project Name:** `Wk10_Lab_RiasatRaihanNoor`  
**Technology:** Java 25, JavaFX 25, JDBC (`PreparedStatement`), MySQL

## Overview
A desktop **Employment Application** form built with JavaFX and connected to a MySQL database (`javafxsample`) using JDBC. The application replicates the standard two-section employment form (**Personal Information** and **Employment Eligibility**), validates user input, and inserts completed applications into MySQL using parameterized `PreparedStatement` queries with transaction management.

<img width="1534" height="862" alt="Screenshot 2026-11-18 134105" src="https://github.com/user-attachments/assets/34328c61-5bdf-4a45-ad18-33308d9f46d9" />
<img width="1534" height="862" alt="Screenshot 2026-11-18 134145" src="https://github.com/user-attachments/assets/e592b636-d278-4043-b139-6dc8fde27248" />
## Form Sections & UI Controls

### 1. Header Banner
* Custom company logo (`✶ Riasat Company`) paired with a dark `"Employment Application"` header bar.

### 2. Personal Information (`GridPane`)
* **Full Name** (`TextField` — required)
* **Current Address** (`TextField` — required)
* **Contact Number** (`TextField` — required)
* **Email Address** (`TextField` — required)
* **Highest Educational Attainment** (`TextField` — required)
* **Gender** (`RadioButton` group with `ToggleGroup`: `Male`, `Female`, `Other`)

### 3. Employment Eligibility (`GridPane` & `VBox`)
* **Date Available** (`DatePicker` calendar control — required)
* **Desired Position** (`TextField` — required)
* **Desired Salary** (`TextField` — required, validated as a numeric `double`)
* **Are you legally authorized to work in the country?** (`Yes` / `No` `RadioButton` group mapped to `boolean`)
* **Do you have relatives working for our company?** (`Yes` / `No` `RadioButton` group mapped to `boolean`)
* **If yes, please explain further** (`TextField` — required when `"Yes"` is selected for relatives in the company)

### 4. Policy Agreement & Submission Footer (`HBox`)
* Displays the applicant policy agreement statement alongside the **Submit Application** button (placed in the signature region) and triggers a JavaFX `Alert` confirmation dialog (`"Successful!"`) upon database insertion.

## Code Architecture
The project follows the modular 5-class DAO design pattern:
* **`Database.java`:** Manages the MySQL JDBC driver (`com.mysql.cj.jdbc.Driver`) and connection to `jdbc:mysql://localhost:3306/javafxsample`.
* **`StringPool.java`:** Provides shared string constants (`StringPool.BLANK`) for input validation.
* **`EmploymentForm.java`:** Model (POJO) class encapsulating all 12 application attributes (`fullName`, `currentAddress`, `contactNumber`, `emailAddress`, `highestEducation`, `gender`, `availableDate`, `desiredPosition`, `desiredSalary`, `legallyAuthorized`, `relativesInCompany`, `relativesInCompanyExplain`).
* **`EmploymentFormDao.java`:** Data Access Object that executes parameterized `INSERT` statements using `PreparedStatement`, `Statement.RETURN_GENERATED_KEYS`, and explicit transaction handling (`setAutoCommit(false)`, `commit()`, `rollback()`).
* **`Main.java`:** Constructs the JavaFX scene graph, handles form validation on submission, invokes `EmploymentFormDao.saveApplication()`, and displays status alerts.

## Project Structure
    Wk10_Lab_RiasatRaihanNoor/
    ├── src/
    │   ├── application/
    │   │   ├── Database.java
    │   │   ├── EmploymentForm.java
    │   │   ├── EmploymentFormDao.java
    │   │   ├── Main.java
    │   │   ├── StringPool.java
    │   │   └── application.css
    │   └── module-info.java
    ├── database.sql
    └── README.md

## How to Run

### Step 1: Set Up the MySQL Database
Open **MySQL Workbench** and run the following script to create the `javafxsample` schema and `employment_form` table:

```sql
CREATE DATABASE IF NOT EXISTS javafxsample;
USE javafxsample;

CREATE TABLE IF NOT EXISTS employment_form (
    id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    current_address VARCHAR(200) NOT NULL,
    contact_number VARCHAR(30) NOT NULL,
    email_address VARCHAR(100) NOT NULL,
    highest_education VARCHAR(100) NOT NULL,
    gender VARCHAR(20) NOT NULL,
    available_date DATE NOT NULL,
    desired_position VARCHAR(100) NOT NULL,
    desired_salary DECIMAL(10, 2) NOT NULL,
    legally_authorized BOOLEAN NOT NULL,
    relatives_in_company BOOLEAN NOT NULL,
    relatives_in_company_explain VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Step 2: Configure & Launch in Eclipse IDE
1. Import `Wk10_Lab_RiasatRaihanNoor` into Eclipse via **File -> Import -> General -> Existing Projects into Workspace**.
2. Verify that **JavaFX SDK 25** and **MySQL Connector/J (`mysql-connector-j.jar`)** are added to the project's **Build Path**.
3. Confirm `Database.java` matches your local MySQL credentials (`DB_USER = "root"`, `DB_PASSWORD = "root"`).
4. Verify `src/module-info.java`:
   ```java
   module Wk10_Lab_RiasatRaihanNoor {
       requires javafx.controls;
       requires javafx.graphics;
       requires java.sql;

       opens application to javafx.graphics, javafx.fxml;
   }
   ```
5. Right-click `src/application/Main.java` and select **Run As -> Java Application**.

### Step 3: Verify Database Insertion
After filling out the form and clicking **Submit Application**, verify the inserted record in **MySQL Workbench**:
```sql
SELECT * FROM javafxsample.employment_form;
```
