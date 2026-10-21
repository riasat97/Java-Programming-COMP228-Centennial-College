package com.va.week7.midterm;

// 6. LIBRARY ASSISTANT CLASS (Hierarchical Inheritance with Librarian extending LibraryStaff)
public class LibraryAssistant extends LibraryStaff {

    private final String shiftType;
    private final double shiftAllowance;

    public LibraryAssistant(String personId, String name, String phoneNumber,
                            String staffId, String department, double monthlySalary,
                            String shiftType, double shiftAllowance) {
        super(personId, name, phoneNumber, staffId, department, monthlySalary);

        if (shiftType == null || shiftType.isBlank()) {
            throw new IllegalArgumentException("Shift type cannot be blank.");
        }
        if (shiftAllowance < 0) {
            throw new IllegalArgumentException("Shift allowance cannot be negative.");
        }

        this.shiftType = shiftType;
        this.shiftAllowance = shiftAllowance;
    }

    // Compensation = monthly salary + shift allowance
    @Override
    public double calculateMonthlyCompensation() {
        return monthlySalary + shiftAllowance;
    }

    @Override
    public String getRoleDescription() {
        return "Library Assistant (" + shiftType + " shift)";
    }

    @Override
    public void displayStaffDetails() {
        super.displayStaffDetails();
        System.out.println("Shift type: " + shiftType);
        System.out.printf("Shift allowance: $%.2f%n", shiftAllowance);
    }
}
