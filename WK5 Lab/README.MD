# Hospital Management System

Java application demonstrating inheritance models, interfaces, method overriding, polymorphism, and custom exception handling.

---

## Features & OOP Concepts

* **Single Inheritance:** `Doctor extends Person`
* **Hierarchical Inheritance:** `Doctor` and `Nurse` both extend `Person`
* **Multilevel Inheritance:** `Surgeon extends Doctor extends Person`
* **Multiple Inheritance via Interfaces:** `EmergencyDoctor extends Doctor implements MedicalProfessional, Billable`
* **Method Overriding:** Subclasses override `performDuties()` with role-specific logic
* **Polymorphism:** Demonstrated using sequential `Person` reference assignments and array traversal (`hospitalStaff`)
* **Exception Handling:** Custom checked exceptions (`InvalidPersonException`, `InvalidSalaryException`) and `InputMismatchException` handled in a `try-catch-finally` loop

---

## Class Structure

| File | Type | Description |
| :--- | :--- | :--- |
| `Person.java` | Class | Parent class (`personId`, `name`, `age`, `salary`) |
| `Doctor.java` | Class | Adds `specialization`, `consultationFee`, `diagnosePatient()` |
| `Nurse.java` | Class | Adds `department`, `shift`, `assistPatient()` |
| `Surgeon.java` | Class | Adds `surgeryType`, `operatingRoom`, `performSurgery()` |
| `EmergencyDoctor.java` | Class | Adds `emergencyUnit`, `handleEmergency()`, interface methods |
| `MedicalProfessional.java` | Interface | `void prescribeMedication()` |
| `Billable.java` | Interface | `void generateBill()` |
| `InvalidPersonException.java` | Exception | Validates ID, blank strings, age (18–120), fees |
| `InvalidSalaryException.java` | Exception | Validates non-negative salary |
| `MainDriver.java` | Class | Console menu driver, exception handling, polymorphism demo |

---

## Validation Rules

* `personId > 0`
* `name` not null or blank
* `age` between 18 and 120
* `salary >= 0`
* `specialization` not blank
* `consultationFee >= 0`

---

## Test Inputs

1. **Doctor:** Type `1`, ID `101`, Name `John`, Age `45`, Salary `85000`, Spec `Cardiology`, Fee `150.0`
2. **Nurse:** Type `2`, ID `102`, Name `Sarah`, Age `32`, Salary `62000`, Dept `Pediatrics`, Shift `Night`
3. **Surgeon:** Type `3`, ID `103`, Name `Brown`, Age `50`, Salary `140000`, Spec `Cardiac surgery`, Fee `350.0`, Surgery `Cardiac surgery`, Room `Operating Room 5`
4. **Emergency Doctor:** Type `4`, ID `104`, Name `Wilson`, Age `40`, Salary `110000`, Spec `Trauma`, Fee `200.0`, Unit `ER-RedZone`

---
