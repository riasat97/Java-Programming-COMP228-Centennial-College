package com.rn.hms;
/*
 * Class: Surgeon
 * Concept: Multilevel Inheritance (Person -> Doctor -> Surgeon)
 * 
 * Demonstrates multilevel inheritance by extending Doctor, inheriting
 * attributes and methods from both Person and Doctor while adding surgical fields.
 */
public class Surgeon extends Doctor {
    
    // Surgeon-specific attributes
    protected String surgeryType;
    protected String operatingRoom;

    // Constructor chaining using super() up through Doctor to Person
    public Surgeon(int personId, String name, int age, double salary, 
                   String specialization, double consultationFee, 
                   String surgeryType, String operatingRoom) 
            throws InvalidPersonException, InvalidSalaryException {
        
        // Pass fields to parent Doctor class constructor
        super(personId, name, age, salary, specialization, consultationFee);

        this.surgeryType = surgeryType;
        this.operatingRoom = operatingRoom;
    }

    // Specialized method unique to Surgeon
    public void performSurgery() {
        System.out.println(name + " is performing " + surgeryType + " in " + operatingRoom + ".");
    }

    // Method Overriding: Replaces Doctor's performDuties with surgical duties
    @Override
    public void performDuties() {
        System.out.println(name + " is performing surgical duties.");
    }

    // Getters
    public String getSurgeryType() { return surgeryType; }
    public String getOperatingRoom() { return operatingRoom; }
}