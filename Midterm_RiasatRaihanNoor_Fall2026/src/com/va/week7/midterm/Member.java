package com.va.week7.midterm;

// 2. MEMBER CLASS (Single Inheritance: Member extends Person)
public class Member extends Person {

    private final String memberNumber;
    private final int age;
    private final String membershipType;
    private double outstandingFees;
    private static int memberCount = 0;

    // Basic constructor
    public Member(String personId, String name, String phoneNumber,
                  String memberNumber, int age) {
        this(personId, name, phoneNumber, memberNumber, age, "Standard", 0.0);
    }

    // Full constructor with validation
    public Member(String personId, String name, String phoneNumber,
                  String memberNumber, int age, String membershipType, double outstandingFees) {
        super(personId, name, phoneNumber);

        if (memberNumber == null || memberNumber.isBlank()) {
            throw new IllegalArgumentException("Member number cannot be blank.");
        }
        if (age < 5 || age > 120) {
            throw new IllegalArgumentException("Age must be between 5 and 120.");
        }
        if (membershipType == null || membershipType.isBlank()) {
            throw new IllegalArgumentException("Membership type cannot be blank.");
        }
        if (outstandingFees < 0) {
            throw new IllegalArgumentException("Outstanding fees cannot be negative.");
        }

        this.memberNumber = memberNumber;
        this.age = age;
        this.membershipType = membershipType;
        this.outstandingFees = outstandingFees;
        memberCount++;
    }

    // Overloaded registerMember method 1: basic registration
    public static Member registerMember(String personId, String name, String phoneNumber,
                                        String memberNumber, int age) {
        return new Member(personId, name, phoneNumber, memberNumber, age);
    }

    // Overloaded registerMember method 2: registration with membership type and fees
    public static Member registerMember(String personId, String name, String phoneNumber,
                                        String memberNumber, int age,
                                        String membershipType, double outstandingFees) {
        return new Member(personId, name, phoneNumber, memberNumber, age, membershipType, outstandingFees);
    }

    public static int getMemberCount() {
        return memberCount;
    }

    @Override
    public String getRoleDescription() {
        return "Member (" + membershipType + ")";
    }

    public void displayMemberDetails() {
        displayBasicDetails();
        System.out.println("Member number: " + memberNumber);
        System.out.println("Age: " + age);
        System.out.println("Membership: " + membershipType);
        System.out.printf("Outstanding fees: $%.2f%n", outstandingFees);
    }

    @Override
    public String toString() {
        return String.format(
                "Member{memberNumber='%s', age=%d, membershipType='%s', outstandingFees=%.2f, person=%s}",
                memberNumber, age, membershipType, outstandingFees, super.toString());
    }
}
