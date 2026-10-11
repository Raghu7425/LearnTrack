package com.airtribe.learntrack.constants;

/**
 * Menu option numbers, so Main has no magic numbers in its switch statements.
 */
public class MenuOptions {

    public static final int EXIT = 0;
    public static final int BACK = 0;

    // Main menu
    public static final int STUDENT_MENU = 1;
    public static final int COURSE_MENU = 2;
    public static final int ENROLLMENT_MENU = 3;

    // Student menu
    public static final int ADD_STUDENT = 1;
    public static final int VIEW_STUDENTS = 2;
    public static final int SEARCH_STUDENT = 3;
    public static final int UPDATE_STUDENT = 4;
    public static final int DEACTIVATE_STUDENT = 5;
    public static final int REMOVE_STUDENT = 6;

    // Course menu
    public static final int ADD_COURSE = 1;
    public static final int VIEW_COURSES = 2;
    public static final int ACTIVATE_COURSE = 3;
    public static final int DEACTIVATE_COURSE = 4;

    // Enrollment menu
    public static final int ENROLL_STUDENT = 1;
    public static final int VIEW_STUDENT_ENROLLMENTS = 2;
    public static final int COMPLETE_ENROLLMENT = 3;
    public static final int CANCEL_ENROLLMENT = 4;

    // private constructor: nobody needs to create an object of this class
    private MenuOptions() {
    }
}
