package com.rn.hms;
/*
 * Class: Person
 * Concept: Base Class (Encapsulation and Inheritance Root)
 * 
 * Serves as the top-level parent class for all hospital personnel.
 * Contains common attributes (personId, name, age, salary) and default behaviors.
 */
public class Person {
	// Protected attributes to allow direct access by subclasses
    protected int personId;
    protected String name;
    protected int age;
    protected double salary;
    // Base constructor with input validation
    public Person(int personId, String name, int age, double salary) 
            throws InvalidPersonException, InvalidSalaryException {
    	// Validation: ID must be positive
        if (personId <= 0) {
            throw new InvalidPersonException("Person ID must be greater than zero.");
        }
        // Validation: Name must not be blank
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidPersonException("Name cannot be empty.");
        }
        // Validation: Age must fall within working boundaries
        if (age < 18 || age > 120) {
            throw new InvalidPersonException("Age must be between 18 and 120.");
        }
        // Validation: Salary cannot be negative
        if (salary < 0) {
            throw new InvalidSalaryException("Salary cannot be negative.");
        }

        this.personId = personId;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    // Displays basic person demographic information
    public void displayDetails() {
        System.out.println("Person ID: " + personId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
    // General hospital duty method to be overridden by child classes
    public void performDuties() {
        System.out.println(name + " is performing general hospital duties.");
    }
    // Getters
    public int getPersonId() { return personId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }
}
