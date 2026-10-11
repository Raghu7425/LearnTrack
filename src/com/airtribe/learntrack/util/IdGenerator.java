package com.airtribe.learntrack.util;

/**
 * Hands out unique IDs. Counters are static so they are shared
 * across the whole application, not per object.
 */
public class IdGenerator {

    private static int studentIdCounter = 1;
    private static int courseIdCounter = 101;
    private static int enrollmentIdCounter = 1001;

    // private constructor: nobody needs to create an object of this class
    private IdGenerator() {
    }

    public static int getNextStudentId() {
        return studentIdCounter++;
    }

    public static int getNextCourseId() {
        return courseIdCounter++;
    }

    public static int getNextEnrollmentId() {
        return enrollmentIdCounter++;
    }
}
