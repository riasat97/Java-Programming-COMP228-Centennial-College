package application;

import java.time.LocalDate;

/**
 * - COMP228 - Java Programming
 * - Lab Assignment: Hospital Management System
 * - Student Name: Riasat Raihan Noor
 * - Student ID: 301554207
 * 
 * Purpose of this class:
 * This is a Model (POJO) class that represents a single patient record from the
 * 'patients' table in our MySQL database. JavaFX's TableView uses the getter
 * methods in this class to display each column on the screen.
 */
public class Patient {

    // Fields matching the columns in the 'patients' MySQL table
    private final int patientId;
    private final String name;
    private final int age;
    private final String gender;
    private final String phone;
    private final String diagnosis;
    private final String department;     // Optional extension: Department
    private final String status;         // Optional extension: Patient Status
    private final LocalDate admissionDate;
    private final String createdAt;

    // Constructor 1: Used when reading a full patient row (including ID and timestamp) from MySQL
    public Patient(int patientId, String name, int age, String gender, String phone,
                   String diagnosis, String department, String status,
                   LocalDate admissionDate, String createdAt) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.diagnosis = diagnosis;
        this.department = department;
        this.status = status;
        this.admissionDate = admissionDate;
        this.createdAt = createdAt;
    }

    // Constructor 2: Used when creating or updating a patient from the JavaFX form
    // (where 'createdAt' is handled automatically by the database)
    public Patient(int patientId, String name, int age, String gender, String phone,
                   String diagnosis, String department, String status, LocalDate admissionDate) {
        this(patientId, name, age, gender, phone, diagnosis, department, status, admissionDate, null);
    }

    // Getter methods required by JavaFX TableView to read each field's value
    public int getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getPhone() { return phone; }
    public String getDiagnosis() { return diagnosis; }
    public String getDepartment() { return department; }
    public String getStatus() { return status; }
    public LocalDate getAdmissionDate() { return admissionDate; }
    public String getCreatedAt() { return createdAt; }
}