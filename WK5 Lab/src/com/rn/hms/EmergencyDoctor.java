package com.rn.hms;
/*
 * Class: EmergencyDoctor
 * Concept: Multiple Inheritance via Interfaces
 *          (extends Doctor, implements MedicalProfessional, Billable)
 * 
 * Inherits Doctor class implementation and realizes contracts from
 * both MedicalProfessional and Billable interfaces.
 */
public class EmergencyDoctor extends Doctor implements MedicalProfessional, Billable {
    
    // Emergency-specific attribute
    protected String emergencyUnit;

    // Constructor chaining via super() to Doctor and Person
    public EmergencyDoctor(int personId, String name, int age, double salary, 
                           String specialization, double consultationFee, String emergencyUnit) 
            throws InvalidPersonException, InvalidSalaryException {
        
        // Pass fields to parent Doctor class constructor
        super(personId, name, age, salary, specialization, consultationFee);
        this.emergencyUnit = emergencyUnit;
    }

    // Specialized method unique to EmergencyDoctor
    public void handleEmergency() {
        System.out.println(name + " is handling a medical emergency.");
    }

    // Interface Method Implementation: MedicalProfessional
    @Override
    public void prescribeMedication() {
        System.out.println(name + " is prescribing medication.");
    }

    // Interface Method Implementation: Billable
    @Override
    public void generateBill() {
        System.out.println(name + " generated an emergency treatment bill.");
    }

    // Method Overriding: Overrides Doctor's performDuties with ER duties
    @Override
    public void performDuties() {
        System.out.println(name + " is providing emergency medical care.");
    }

    // Getter
    public String getEmergencyUnit() { return emergencyUnit; }
}