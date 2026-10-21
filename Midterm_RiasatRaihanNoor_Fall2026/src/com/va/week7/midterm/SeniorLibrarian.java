package com.va.week7.midterm;

// 5. SENIOR LIBRARIAN CLASS (Multilevel Inheritance: Person -> LibraryStaff -> Librarian -> SeniorLibrarian)
public class SeniorLibrarian extends Librarian {

    private final String specialization;
    private final double seniorityAllowance;

    public SeniorLibrarian(String personId, String name, String phoneNumber,
                           String staffId, String department, double monthlySalary,
                           int dailyIssueLimit, int booksProcessed,
                           String specialization, double seniorityAllowance) {
        super(personId, name, phoneNumber, staffId, department, monthlySalary,
                dailyIssueLimit, booksProcessed);

        if (specialization == null || specialization.isBlank()) {
            throw new IllegalArgumentException("Specialization cannot be blank.");
        }
        if (seniorityAllowance < 0) {
            throw new IllegalArgumentException("Seniority allowance cannot be negative.");
        }

        this.specialization = specialization;
        this.seniorityAllowance = seniorityAllowance;
    }

    // Includes librarian compensation + seniority allowance
    @Override
    public double calculateMonthlyCompensation() {
        return super.calculateMonthlyCompensation() + seniorityAllowance;
    }

    @Override
    public String getRoleDescription() {
        return "Senior Librarian (" + specialization + ")";
    }

    @Override
    public void displayStaffDetails() {
        super.displayStaffDetails();
        System.out.println("Specialization: " + specialization);
        System.out.printf("Seniority allowance: $%.2f%n", seniorityAllowance);
        System.out.printf("Total compensation: $%.2f%n", calculateMonthlyCompensation());
    }
}
