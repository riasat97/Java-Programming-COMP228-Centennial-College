
# Hospital Management System

Java application demonstrating inheritance models, interfaces, method overriding, polymorphism, and custom exception handling[cite: 10].

---

## Features & OOP Concepts

* **Single Inheritance:** `Doctor extends Person`[cite: 10]
* **Hierarchical Inheritance:** `Doctor` and `Nurse` both extend `Person`[cite: 10]
* **Multilevel Inheritance:** `Surgeon extends Doctor extends Person`[cite: 10]
* **Multiple Inheritance via Interfaces:** `EmergencyDoctor extends Doctor implements MedicalProfessional, Billable`[cite: 10]
* **Method Overriding:** Subclasses override `performDuties()` with role-specific logic[cite: 10]
* **Polymorphism:** Demonstrated using sequential `Person` reference assignments and array traversal (`hospitalStaff`)[cite: 10, 12, 34]
* **Exception Handling:** Custom checked exceptions (`InvalidPersonException`, `InvalidSalaryException`) and `InputMismatchException` handled in a `try-catch-finally` loop[cite: 11, 12, 15]

---

## Class Structure

| File | Type | Description |
| :--- | :--- | :--- |
| `Person.java` | Class | Parent class (`personId`, `name`, `age`, `salary`)[cite: 10, 11] |
| `Doctor.java` | Class | Adds `specialization`, `consultationFee`, `diagnosePatient()`[cite: 10] |
| `Nurse.java` | Class | Adds `department`, `shift`, `assistPatient()`[cite: 10] |
| `Surgeon.java` | Class | Adds `surgeryType`, `operatingRoom`, `performSurgery()`[cite: 10] |
| `EmergencyDoctor.java` | Class | Adds `emergencyUnit`, `handleEmergency()`, interface methods[cite: 10] |
| `MedicalProfessional.java` | Interface | `void prescribeMedication()`[cite: 10] |
| `Billable.java` | Interface | `void generateBill()`[cite: 10] |
| `InvalidPersonException.java` | Exception | Validates ID, blank strings, age (18–120),
