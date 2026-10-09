package com.rn.hms;
/*
 * Class: Doctor
 * Concept: Single Inheritance (Person -> Doctor)
 * 
 * Extends Person to inherit common attributes while adding doctor-specific
 * fields (specialization, consultationFee) and behaviors.
 */
public class Doctor extends Person {
    
    // Doctor-specific attributes
    protected String specialization;
    protected double consultationFee;

    // Constructor using super() to invoke Person constructor
    public Doctor(int personId, String name, int age, double salary, 
                  String specialization, double consultationFee) 
            throws InvalidPersonException, InvalidSalaryException {
        
        // Pass common fields to parent Person class
        super(personId, name, age, salary);

        // Validation: Specialization cannot be blank
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new InvalidPersonException("Specialization cannot be empty.");
        }
        // Validation: Consultation fee cannot be negative
        if (consultationFee < 0) {
            throw new InvalidPersonException("Consultation fee cannot be negative.");
        }

        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }

    // Specialized method unique to Doctor
    public void diagnosePatient() {
        System.out.println(name + " is diagnosing a patient.");
    }

    // Method Overriding: Replaces Person's general duties with doctor-specific duties
    @Override
    public void performDuties() {
        System.out.println(name + " is examining and treating patients.");
    }

    // Getters
    public String getSpecialization() { return specialization; }
    public double getConsultationFee() { return consultationFee; }
}