package com.va.week7.midterm;

// 1. ABSTRACT PERSON CLASS
public abstract class Person {

    private final String personId;
    private final String name;
    private final String phoneNumber;
    private static int personCount = 0;

    public Person(String personId, String name, String phoneNumber) {
        if (personId == null || personId.isBlank()) {
            throw new IllegalArgumentException("Person ID cannot be blank.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be blank.");
        }

        this.personId = personId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        personCount++;
    }

    public String getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static int getPersonCount() {
        return personCount;
    }

    // Display common person details
    public void displayBasicDetails() {
        System.out.println("Person ID: " + personId);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phoneNumber);
    }

    // Abstract method to be implemented by subclasses
    public abstract String getRoleDescription();

    @Override
    public String toString() {
        return "Person{personId='" + personId + "', name='" + name + "', phoneNumber='" + phoneNumber + "'}";
    }
}
