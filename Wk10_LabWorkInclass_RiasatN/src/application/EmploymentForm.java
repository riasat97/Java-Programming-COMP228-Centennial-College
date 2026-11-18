package application;

import java.time.LocalDate;

/**
 * - COMP228 - Week 10 In-Class Lab
 * - Student Name: Riasat Raihan Noor
 * - Purpose: Model class representing an Employment Application form submission.
 *   Replaces User.java from the class template.
 */
public class EmploymentForm {

    private int id;
    private String fullName;
    private String currentAddress;
    private String contactNumber;
    private String emailAddress;
    private String highestEducation;
    private String gender;
    private LocalDate availableDate;
    private String desiredPosition;
    private double desiredSalary;
    private boolean legallyAuthorized;
    private boolean relativesInCompany;
    private String relativesInCompanyExplain;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getHighestEducation() {
        return highestEducation;
    }

    public void setHighestEducation(String highestEducation) {
        this.highestEducation = highestEducation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }

    public String getDesiredPosition() {
        return desiredPosition;
    }

    public void setDesiredPosition(String desiredPosition) {
        this.desiredPosition = desiredPosition;
    }

    public double getDesiredSalary() {
        return desiredSalary;
    }

    public void setDesiredSalary(double desiredSalary) {
        this.desiredSalary = desiredSalary;
    }

    public boolean isLegallyAuthorized() {
        return legallyAuthorized;
    }

    public void setLegallyAuthorized(boolean legallyAuthorized) {
        this.legallyAuthorized = legallyAuthorized;
    }

    public boolean isRelativesInCompany() {
        return relativesInCompany;
    }

    public void setRelativesInCompany(boolean relativesInCompany) {
        this.relativesInCompany = relativesInCompany;
    }

    public String getRelativesInCompanyExplain() {
        return relativesInCompanyExplain;
    }

    public void setRelativesInCompanyExplain(String relativesInCompanyExplain) {
        this.relativesInCompanyExplain = relativesInCompanyExplain;
    }
}