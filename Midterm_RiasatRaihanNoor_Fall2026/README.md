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
| **1** | Member Registration (Overloaded) | `P101, Alice, M2001, 25` & `P102, David, M2002, 34, Premium, $18.00` | Creates `Standard` ($0.00 fee) and `Premium` ($18.00 fee) members. |
| **2** | Late Fee Calculation (Overloaded) | `calculateLateFee(7)` and `calculateLateFee(7, 1.50)` | Outputs `$7.00` (default $1/day) and `$10.50` ($1.50/day). |
| **3** | Staff Compensation (Polymorphism) | Librarian (110 books), Senior (140 books + $400), Assistant ($150 shift) | Calculates `$4220.00`, `$5680.00`, and `$3050.00` respectively. |
| **4** | Validation & Exception Handling | `calculateLateFee(-2)` and blank name `""` in `registerMember()` | Throws `IllegalArgumentException` with error messages and does not increment counters. |
| **5** | Static Counters & Reservations | 2 Members + 3 Staff created; `reserveBook("BK-1042")` & `reserveBook("")` | `Person-derived objects: 5`, `Staff objects: 3`; valid book reserved by Sarah, blank ID rejected. |
