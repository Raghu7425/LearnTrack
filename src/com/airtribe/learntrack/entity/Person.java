package com.airtribe.learntrack.entity;

/**
 * Base class for every person in the system (students, trainers).
 */
public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Person() {
    }

    // Constructor overloading: a Person without an email.
    // this(...) calls the other constructor below.
    public Person(int id, String firstName, String lastName) {
        this(id, firstName, lastName, null);
    }

    public Person(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Name shown in listings. Subclasses override this to add their role.
     */
    public String getDisplayName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        String emailText = "-";
        if (email != null) {
            emailText = email;
        }
        return "ID: " + id + " | Name: " + getDisplayName() + " | Email: " + emailText;
    }
}
