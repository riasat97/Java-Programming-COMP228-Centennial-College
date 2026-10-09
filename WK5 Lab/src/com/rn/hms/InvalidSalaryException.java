package com.rn.hms;
/*
 * Exception: InvalidSalaryException
 * Concept: Custom Checked Exception
 * 
 * Thrown whenever a negative or invalid salary value is entered.
 */
public class InvalidSalaryException extends Exception {
	// Constructor passing error message to parent Exception
    public InvalidSalaryException(String message) {
        super(message);
    }
}
