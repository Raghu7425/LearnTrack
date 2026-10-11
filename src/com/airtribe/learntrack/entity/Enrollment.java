package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.EnrollmentStatus;
import java.time.LocalDate;

public class Enrollment {

    private int id;
    private int studentId;
    private int courseId;
    private LocalDate enrollmentDate;   // LocalDate holds a date such as 2026-10-11
    private EnrollmentStatus status;

    /** New enrollment dated today with status ACTIVE. */
    public Enrollment(int id, int studentId, int courseId) {
        this(id, studentId, courseId, LocalDate.now());
    }

    public Enrollment(int id, int studentId, int courseId, LocalDate enrollmentDate) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = EnrollmentStatus.ACTIVE;
    }

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Student ID: " + studentId
                + " | Course ID: " + courseId
                + " | Date: " + enrollmentDate
                + " | Status: " + status;
    }
}
