package com.airtribe.learntrack.exception;

/**
 * Custom checked exception.
 * Thrown when a student, course or enrollment ID does not exist.
 */
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String entityName, int id) {
        super(entityName + " with ID " + id + " not found.");
    }
}
