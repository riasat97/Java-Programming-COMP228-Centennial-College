# Library Management System - Practical Midterm

Console-based Java 21 application for Maple Leaf Public Library demonstrating core Object-Oriented Programming (OOP) principles.

## OOP Concepts


* **Single Inheritance:** `Member extends Person`.
* **Multilevel Inheritance:** `Person -> LibraryStaff -> Librarian -> SeniorLibrarian`.
* **Hierarchical Inheritance:** `Librarian` and `LibraryAssistant` both extend `LibraryStaff`.
* **Multiple Inheritance & Interfaces:** Java does not allow a class to extend multiple classes to avoid the Diamond Problem (ambiguity when two parent classes define the same method). Instead, `Librarian` implements multiple interfaces (`Reportable` and `Reservable`).
* **Hybrid Inheritance:** `Librarian` combines class inheritance (`extends LibraryStaff`) with multiple interfaces (`implements Reportable, Reservable`).
* **Method Overloading:** Demonstrated in `Member.registerMember()` and `Librarian.calculateLateFee()`.
* **Method Overriding & Runtime Polymorphism:** Demonstrated using a `LibraryStaff[]` array calling overridden `getRoleDescription()`, `displayStaffDetails()`, and `calculateMonthlyCompensation()`.

---

## Five Test Cases

| Test # | Feature Tested | Input Values | Expected Output |
| :--- | :--- | :--- | :--- |
| **1** | Member Registration (Overloaded) | `P001, Peter, M1001, 22` & `P002, Tom, M1002, 31, Premium, $12.50` | Creates `Standard` ($0.00 fee) and `Premium` ($12.50 fee) members. |
| **2** | Late Fee Calculation (Overloaded) | `calculateLateFee(7)` and `calculateLateFee(7, 1.50)` | Outputs `$7.00` (default $1/day) and `$10.50` ($1.50/day). |
| **3** | Staff Compensation (Polymorphism) | Librarian (125 books), Senior (160 books + $350), Assistant ($175 shift) | Calculates `$4450.00`, `$5870.00`, and `$3275.00` respectively. |
| **4** | Validation & Exception Handling | `calculateLateFee(-2)` and blank name `""` in `registerMember()` | Throws `IllegalArgumentException` with error messages and does not increment counters. |
| **5** | Static Counters & Reservations | 2 Members + 3 Staff created; `reserveBook("BK-1042")` & `reserveBook("")` | `Person-derived objects: 5`, `Staff objects: 3`; valid book reserved, blank ID rejected. |
