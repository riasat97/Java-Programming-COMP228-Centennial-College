# Library Management System

## 1. Compilation and Execution Instructions

**In Eclipse:**
1. Import the project `Midterm_RiasatRaihanNoor_Fall2026`.
2. Open `src/com/va/week7/midterm/Main.java`.
3. Right-click and select **Run As -> Java Application**.

**Command Line (Java 21+):**
```bash
javac -d bin src/com/va/week7/midterm/*.java
java -cp bin com.va.week7.midterm.Main
```

---

## 2. Class Hierarchy and OOP Concepts

```text
Person (Abstract)
 ├── Member
 └── LibraryStaff (Abstract)
      ├── Librarian (implements Reportable, Reservable)
      │    └── SeniorLibrarian
      └── LibraryAssistant
```

* **Abstract Classes & Methods:** `Person` (`getRoleDescription()`) and `LibraryStaff` (`calculateMonthlyCompensation()`) define base templates for subclasses.
* **Single Inheritance:** `Member extends Person`.
* **Multilevel Inheritance:** `Person -> LibraryStaff -> Librarian -> SeniorLibrarian`.
* **Hierarchical Inheritance:** `Librarian` and `LibraryAssistant` both extend `LibraryStaff`.
* **Multiple Inheritance & Interfaces:** Java does not allow a class to extend multiple classes to prevent ambiguity (the Diamond Problem) when two parent classes define the same method. Interfaces solve this by allowing a class to implement multiple contracts (`Reportable`, `Reservable`).
* **Hybrid Inheritance:** `Librarian` extends `LibraryStaff` and implements both `Reportable` and `Reservable`.
* **Static & Final Variables:** Core fields are marked `final` so they cannot be modified after creation. Static counters (`personCount`, `staffCount`) track valid objects created.
* **Method Overloading:** Used in `Member.registerMember()` and `Librarian.calculateLateFee()`.
* **Method Overriding & Runtime Polymorphism:** Subclasses override `getRoleDescription()`, `displayStaffDetails()`, and `calculateMonthlyCompensation()`, called dynamically at runtime using a `LibraryStaff[]` array.

---

## 3. Five Test Cases

| Test # | Feature Tested | Input Values | Expected Output |
| :--- | :--- | :--- | :--- |
| **1** | Registration (Overloaded) | `P101, Alice, M2001, 25` & `P102, David, M2002, 34, Premium, $18.00` | Registers `Standard` ($0.00 fee) and `Premium` ($18.00 fee) members. |
| **2** | Late Fees (Overloaded) | `calculateLateFee(7)` and `calculateLateFee(7, 1.50)` | Outputs `$7.00` (default $1/day) and `$10.50` ($1.50/day). |
| **3** | Compensation | Librarian (110 books), Senior (140 books + $400), Assistant ($150 shift) | Outputs `$4220.00`, `$5680.00`, and `$3050.00`. |
| **4** | Validation | `calculateLateFee(-2)` and blank name `""` in `registerMember()` | Throws `IllegalArgumentException` and rejects invalid data. |
| **5** | Static Counters & Reservations | 2 Members + 3 Staff created; `reserveBook("BK-1042")` & `reserveBook("")` | `Person-derived objects: 5`, `Staff objects: 3`; valid book reserved, blank ID rejected. |

---

## 4. Sample Console Output

```text
MAPLE LEAF PUBLIC LIBRARY - DEMO Work for Students to See!!

--- Members ---
Person ID: P101
Name: Alice
Phone: 416-555-1101
Member number: M2001
Age: 25
Membership: Standard
Outstanding fees: $0.00

Person ID: P102
Name: David
Phone: 416-555-1102
Member number: M2002
Age: 34
Membership: Premium
Outstanding fees: $18.00

--- Method overloading: late fees ---
7 days at default rate: $7.00
7 days at $1.50/day: $10.50

--- Runtime polymorphism / staff details ---

Librarian
Person ID: P103
Name: Sarah
Phone: 416-555-1103
Staff ID: S3001
Department: Lending Services
Base salary: $4000.00
Monthly compensation: $4220.00
Role: Librarian
Daily issue limit: 35
Books processed: 110
Calculated compensation: $4220.00

Senior Librarian (Rare Books)
Person ID: P104
Name: Michael
Phone: 416-555-1104
Staff ID: S3002
Department: Research & Collections
Base salary: $5000.00
Monthly compensation: $5680.00
Role: Senior Librarian (Rare Books)
Daily issue limit: 45
Books processed: 140
Specialization: Rare Books
Seniority allowance: $400.00
Total compensation: $5680.00
Calculated compensation: $5680.00

Library Assistant (Morning shift)
Person ID: P105
Name: Emma
Phone: 416-555-1105
Staff ID: S3003
Department: Front Desk
Base salary: $2900.00
Monthly compensation: $3050.00
Role: Library Assistant (Morning shift)
Shift type: Morning
Shift allowance: $150.00
Calculated compensation: $3050.00

--- Interfaces ---
=== Librarian Activity Report ===
Librarian: Sarah
Books processed: 110
Daily issue limit: 35
Reservation request recorded for BK-1042 by Sarah.
Reservation failed: book ID cannot be blank.

--- Static counters ---
Person-derived objects: 5
Staff objects: 3

--- toString() ---
Member{memberNumber='M2001', age=25, membershipType='Standard', outstandingFees=0.00, person=Person{personId='P101', name='Alice', phoneNumber='416-555-1101'}}

--- Validation demonstrations ---
Expected error: Overdue days cannot be negative.
[Ljava.lang.StackTraceElement;@12a3a380
Expected error: Name cannot be blank.
[Ljava.lang.StackTraceElement;@29453f44

Demonstration complete.
```
