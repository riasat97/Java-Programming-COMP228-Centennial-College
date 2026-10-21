package com.va.week7.midterm;

// 7. MAIN APPLICATION CLASS
public class Main {

    public static void main(String[] args) {

        System.out.println("MAPLE LEAF PUBLIC LIBRARY - DEMO Work for Students to See!!");

     // 1. Register two members using overloaded registerMember() methods
        System.out.println("\n--- Members ---");
        Member member1 = Member.registerMember(
                "P101", "Alice", "416-555-1101", "M2001", 25);
        Member member2 = Member.registerMember(
                "P102", "David", "416-555-1102", "M2002", 34, "Premium", 18.00);

        member1.displayMemberDetails();
        System.out.println();
        member2.displayMemberDetails();

        // 2. Create Staff Objects (Librarian, SeniorLibrarian, LibraryAssistant)
        Librarian librarian = new Librarian(
                "P103", "Sarah", "416-555-1103",
                "S3001", "Lending Services", 4000.00, 35, 110);

        SeniorLibrarian senior = new SeniorLibrarian(
                "P104", "Michael", "416-555-1104",
                "S3002", "Research & Collections", 5000.00, 45, 140,
                "Rare Books", 400.00);

        LibraryAssistant assistant = new LibraryAssistant(
                "P105", "Emma", "416-555-1105",
                "S3003", "Front Desk", 2900.00,
                "Morning", 150.00);

        // 3. Demonstrate Method Overloading: Late Fees
        System.out.println("\n--- Method overloading: late fees ---");
        System.out.printf("7 days at default rate: $%.2f%n",
                librarian.calculateLateFee(7));
        System.out.printf("7 days at $1.50/day: $%.2f%n",
                librarian.calculateLateFee(7, 1.50));

        // 4. Runtime Polymorphism and Staff Details using LibraryStaff references
        System.out.println("\n--- Runtime polymorphism / staff details ---");
        LibraryStaff[] staff = {librarian, senior, assistant};
        for (LibraryStaff employee : staff) {
            System.out.println("\n" + employee.getRoleDescription());
            employee.displayStaffDetails();
            System.out.printf("Calculated compensation: $%.2f%n",
                    employee.calculateMonthlyCompensation());
        }

        // 5. Demonstrate Interfaces (Reportable and Reservable)
        System.out.println("\n--- Interfaces ---");
        Reportable reportable = librarian;
        Reservable reservable = librarian;

        reportable.generateReport();
        reservable.reserveBook("BK-1042");
        reservable.reserveBook("");

        // 6. Display Static Counters
        System.out.println("\n--- Static counters ---");
        System.out.println("Person-derived objects: " + Person.getPersonCount());
        System.out.println("Staff objects: " + LibraryStaff.getStaffCount());

        // 7. Demonstrate toString()
        System.out.println("\n--- toString() ---");
        System.out.println(member1);

        // 8. Validation Demonstrations
        System.out.println("\n--- Validation demonstrations ---");
        try {
            librarian.calculateLateFee(-2);
        } catch (IllegalArgumentException ex) {
            System.out.println("Expected error: " + ex.getMessage());
            System.out.println(ex.getStackTrace());
        }

        try {
            Member.registerMember("P006", "", "416-555-0106", "M1003", 20);
        } catch (IllegalArgumentException ex) {
            System.out.println("Expected error: " + ex.getMessage());
            System.out.println(ex.getStackTrace());
        }

        System.out.println("\nDemonstration complete.");
    }
}
