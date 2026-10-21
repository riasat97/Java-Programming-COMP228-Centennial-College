package com.va.week7.midterm;

// 4. LIBRARIAN CLASS (Hybrid Inheritance: Extends LibraryStaff and implements two interfaces)
public class Librarian extends LibraryStaff implements Reportable, Reservable {

    private final int dailyIssueLimit;
    private final int booksProcessed;

    public Librarian(String personId, String name, String phoneNumber,
                     String staffId, String department, double monthlySalary,
                     int dailyIssueLimit, int booksProcessed) {
        super(personId, name, phoneNumber, staffId, department, monthlySalary);

        if (dailyIssueLimit <= 0) {
            throw new IllegalArgumentException("Daily issue limit must be positive.");
        }
        if (booksProcessed < 0) {
            throw new IllegalArgumentException("Books processed cannot be negative.");
        }

        this.dailyIssueLimit = dailyIssueLimit;
        this.booksProcessed = booksProcessed;
    }

    // Compensation = monthly salary + $2.00 per book processed
    @Override
    public double calculateMonthlyCompensation() {
        return monthlySalary + (booksProcessed * 2.00);
    }

    @Override
    public String getRoleDescription() {
        return "Librarian";
    }

    @Override
    public void displayStaffDetails() {
        super.displayStaffDetails();
        System.out.println("Daily issue limit: " + dailyIssueLimit);
        System.out.println("Books processed: " + booksProcessed);
    }

    // Method Overloading 1: default rate of $1.00 per overdue day
    public double calculateLateFee(int daysOverdue) {
        return calculateLateFee(daysOverdue, 1.00);
    }

    // Method Overloading 2: custom daily rate
    public double calculateLateFee(int daysOverdue, double dailyRate) {
        if (daysOverdue < 0) {
            throw new IllegalArgumentException("Overdue days cannot be negative.");
        }
        if (dailyRate <= 0) {
            throw new IllegalArgumentException("Daily rate must be positive.");
        }
        return daysOverdue * dailyRate;
    }

    // Reportable interface implementation
    @Override
    public void generateReport() {
        System.out.println("=== Librarian Activity Report ===");
        System.out.println("Librarian: " + getName());
        System.out.println("Books processed: " + booksProcessed);
        System.out.println("Daily issue limit: " + dailyIssueLimit);
    }

    // Reservable interface implementation
    @Override
    public void reserveBook(String bookId) {
        if (bookId == null || bookId.isBlank()) {
            System.out.println("Reservation failed: book ID cannot be blank.");
            return;
        }
        System.out.println("Reservation request recorded for " + bookId + " by " + getName() + ".");
    }
}
