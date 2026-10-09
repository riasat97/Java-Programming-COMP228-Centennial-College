package com.rn.hms;
/*
 * Exception: InvalidPersonException
 * Concept: Custom Checked Exception
 * 
 * Thrown whenever person details (ID, name, age, specialization, 
 * consultation fee, department, or staff type) fail business validation.
 */
public class InvalidPersonException extends Exception {
	// Constructor passing error message to parent Exception
    public InvalidPersonException(String message) {
        super(message);
    }
}
