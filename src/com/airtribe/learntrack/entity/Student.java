package com.airtribe.learntrack.entity;

/**
 * A learner enrolled in one batch.
 */
public class Student extends Person {

    private String batch;
    private boolean active;

    // Default constructor
    public Student() {
        super();
        this.active = true;
    }

    /** Student without an email address (constructor overloading). */
    public Student(int id, String firstName, String lastName, String batch) {
        super(id, firstName, lastName);   // super(...) runs the Person constructor
        this.batch = batch;
        this.active = true;
    }

    public Student(int id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = true;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Overriding: replaces Person's version for students
    @Override
    public String getDisplayName() {
        return super.getDisplayName() + " (Student, " + batch + ")";
    }

    @Override
    public String toString() {
        return super.toString() + " | Active: " + active;
    }
}
