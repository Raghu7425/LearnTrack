package com.airtribe.learntrack.exception;

/**
 * Thrown when user input or a requested operation breaks a business rule.
 */
public class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super(message);
    }
}
