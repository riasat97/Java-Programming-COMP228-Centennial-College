package com.rn.hms;
/*
 * Class: Nurse
 * Concept: Hierarchical Inheritance (Person -> Doctor and Person -> Nurse)
 * 
 * Demonstrates hierarchical inheritance by extending the same base class
 * (Person) alongside Doctor, adding department and shift attributes.
 */
public class Nurse extends Person {
    
    // Nurse-specific attributes
    protected String department;
    protected String shift;

    // Constructor using super() to invoke Person constructor
    public Nurse(int personId, String name, int age, double salary, 
                 String department, String shift) 
            throws InvalidPersonException, InvalidSalaryException {
        
        // Pass common fields to parent Person class
        super(personId, name, age, salary);

        // Validation: Department cannot be blank
        if (department == null || department.trim().isEmpty()) {
            throw new InvalidPersonException("Department cannot be empty.");
        }

        this.department = department;
        this.shift = shift;
    }

    // Specialized method unique to Nurse
    public void assistPatient() {
        System.out.println(name + " is assisting a patient.");
    }

    // Method Overriding: Replaces Person's general duties with nurse duties
    @Override
    public void performDuties() {
        System.out.println(name + " is providing nursing care.");
    }

    // Getters
    public String getDepartment() { return department; }
    public String getShift() { return shift; }
}