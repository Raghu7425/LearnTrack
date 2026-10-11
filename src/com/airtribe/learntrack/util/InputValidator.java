package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

/**
 * Static helpers that reject bad input with a clear message.
 */
public class InputValidator {

    // private constructor: nobody needs to create an object of this class
    private InputValidator() {
    }

    public static String requireNonEmpty(String value, String fieldName) throws InvalidInputException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }

    public static String validateEmail(String email) throws InvalidInputException {
        String trimmed = requireNonEmpty(email, "Email");
        if (!trimmed.contains("@") || !trimmed.contains(".")) {
            throw new InvalidInputException("Invalid email: " + trimmed);
        }
        return trimmed;
    }

    public static int requirePositive(int value, String fieldName) throws InvalidInputException {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be greater than 0.");
        }
        return value;
    }
}
