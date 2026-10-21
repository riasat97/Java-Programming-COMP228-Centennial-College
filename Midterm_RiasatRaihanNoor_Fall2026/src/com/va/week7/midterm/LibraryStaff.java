package com.va.week7.midterm;

// 3. ABSTRACT LIBRARY STAFF CLASS (Extends Person)
public abstract class LibraryStaff extends Person {

    private final String staffId;
    private final String department;
    protected final double monthlySalary;
    private static int staffCount = 0;

    public LibraryStaff(String personId, String name, String phoneNumber,
                        String staffId, String department, double monthlySalary) {
        super(personId, name, phoneNumber);

        if (staffId == null || staffId.isBlank()) {
            throw new IllegalArgumentException("Staff ID cannot be blank.");
        }
        if (department == null || department.isBlank()) {
            throw new IllegalArgumentException("Department cannot be blank.");
        }
        if (monthlySalary < 0) {
            throw new IllegalArgumentException("Monthly salary cannot be negative.");
        }

        this.staffId = staffId;
        this.department = department;
        this.monthlySalary = monthlySalary;
        staffCount++;
    }

    public static int getStaffCount() {
        return staffCount;
    }

    // Abstract method for monthly compensation
    public abstract double calculateMonthlyCompensation();

    // Display common staff details
    public void displayStaffDetails() {
        displayBasicDetails();
        System.out.println("Staff ID: " + staffId);
        System.out.println("Department: " + department);
        System.out.printf("Base salary: $%.2f%n", monthlySalary);
        System.out.printf("Monthly compensation: $%.2f%n", calculateMonthlyCompensation());
        System.out.println("Role: " + getRoleDescription());
    }
}
