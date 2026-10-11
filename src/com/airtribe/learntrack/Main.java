package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Console UI: shows menus, reads input and calls the services.
 * No business rules live here.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final StudentService studentService =
            new StudentService(new StudentRepository());
    private static final CourseService courseService =
            new CourseService(new CourseRepository());
    private static final EnrollmentService enrollmentService =
            new EnrollmentService(new EnrollmentRepository(), studentService, courseService);

    public static void main(String[] args) {
        System.out.println(AppConstants.APP_NAME);
        int choice;
        do {
            System.out.println("\n" + AppConstants.DIVIDER);
            System.out.println("MAIN MENU");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment Management");
            System.out.println("0. Exit");
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case MenuOptions.STUDENT_MENU:
                    studentMenu();
                    break;
                case MenuOptions.COURSE_MENU:
                    courseMenu();
                    break;
                case MenuOptions.ENROLLMENT_MENU:
                    enrollmentMenu();
                    break;
                case MenuOptions.EXIT:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != MenuOptions.EXIT);
        scanner.close();
    }

    // ================= STUDENT MENU =================

    private static void studentMenu() {
        int choice;
        do {
            System.out.println("\n--- STUDENT MANAGEMENT ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Deactivate Student");
            System.out.println("6. Remove Student");
            System.out.println("0. Back");
            choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case MenuOptions.ADD_STUDENT:
                        addStudent();
                        break;
                    case MenuOptions.VIEW_STUDENTS:
                        printStudents();
                        break;
                    case MenuOptions.SEARCH_STUDENT:
                        System.out.println(studentService.findStudentById(readInt("Student ID: ")));
                        break;
                    case MenuOptions.UPDATE_STUDENT:
                        updateStudent();
                        break;
                    case MenuOptions.DEACTIVATE_STUDENT:
                        studentService.deactivateStudent(readInt("Student ID to deactivate: "));
                        System.out.println("Student deactivated.");
                        break;
                    case MenuOptions.REMOVE_STUDENT:
                        studentService.removeStudent(readInt("Student ID to remove: "));
                        System.out.println("Student removed.");
                        break;
                    case MenuOptions.BACK:
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (EntityNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != MenuOptions.BACK);
    }

    private static void addStudent() throws InvalidInputException {
        String firstName = readLine("First name: ");
        String lastName = readLine("Last name: ");
        String email = readLine("Email (leave blank to skip): ");
        String batch = readLine("Batch: ");

        Student student;
        if (email.isEmpty()) {
            student = studentService.addStudent(firstName, lastName, batch);
        } else {
            student = studentService.addStudent(firstName, lastName, email, batch);
        }
        System.out.println("Student added with ID " + student.getId()
                + ": " + student.getDisplayName());
    }

    private static void updateStudent() throws EntityNotFoundException, InvalidInputException {
        int id = readInt("Student ID to update: ");
        studentService.findStudentById(id);
        System.out.println("Leave a field blank to keep its current value.");
        Student student = studentService.updateStudent(id,
                readLine("New first name: "),
                readLine("New last name: "),
                readLine("New email: "),
                readLine("New batch: "));
        System.out.println("Updated: " + student);
    }

    // ================= COURSE MENU =================

    private static void courseMenu() {
        int choice;
        do {
            System.out.println("\n--- COURSE MANAGEMENT ---");
            System.out.println("1. Add Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Activate Course");
            System.out.println("4. Deactivate Course");
            System.out.println("0. Back");
            choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case MenuOptions.ADD_COURSE:
                        addCourse();
                        break;
                    case MenuOptions.VIEW_COURSES:
                        printCourses();
                        break;
                    case MenuOptions.ACTIVATE_COURSE:
                        courseService.activateCourse(readInt("Course ID to activate: "));
                        System.out.println("Course activated.");
                        break;
                    case MenuOptions.DEACTIVATE_COURSE:
                        courseService.deactivateCourse(readInt("Course ID to deactivate: "));
                        System.out.println("Course deactivated.");
                        break;
                    case MenuOptions.BACK:
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (EntityNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != MenuOptions.BACK);
    }

    private static void addCourse() throws InvalidInputException {
        String name = readLine("Course name: ");
        String description = readLine("Description: ");
        int duration = readInt("Duration in weeks: ");
        Course course = courseService.addCourse(name, description, duration);
        System.out.println("Course added with ID " + course.getId());
    }

    // ================= ENROLLMENT MENU =================

    private static void enrollmentMenu() {
        int choice;
        do {
            System.out.println("\n--- ENROLLMENT MANAGEMENT ---");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. View Enrollments for a Student");
            System.out.println("3. Mark Enrollment Completed");
            System.out.println("4. Cancel Enrollment");
            System.out.println("0. Back");
            choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case MenuOptions.ENROLL_STUDENT:
                        enrollStudent();
                        break;
                    case MenuOptions.VIEW_STUDENT_ENROLLMENTS:
                        printEnrollments(readInt("Student ID: "));
                        break;
                    case MenuOptions.COMPLETE_ENROLLMENT:
                        enrollmentService.updateEnrollmentStatus(
                                readInt("Enrollment ID: "), EnrollmentStatus.COMPLETED);
                        System.out.println("Enrollment marked as COMPLETED.");
                        break;
                    case MenuOptions.CANCEL_ENROLLMENT:
                        enrollmentService.updateEnrollmentStatus(
                                readInt("Enrollment ID: "), EnrollmentStatus.CANCELLED);
                        System.out.println("Enrollment CANCELLED.");
                        break;
                    case MenuOptions.BACK:
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (EntityNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != MenuOptions.BACK);
    }

    private static void enrollStudent() throws EntityNotFoundException, InvalidInputException {
        int studentId = readInt("Student ID: ");
        int courseId = readInt("Course ID: ");
        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
        System.out.println("Enrolled. Enrollment ID: " + enrollment.getId());
    }

    // ================= PRINT HELPERS =================

    private static void printStudents() {
        ArrayList<Student> students = studentService.listStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void printCourses() {
        ArrayList<Course> courses = courseService.listCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static void printEnrollments(int studentId) throws EntityNotFoundException {
        ArrayList<Enrollment> enrollments = enrollmentService.getEnrollmentsForStudent(studentId);
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments for this student.");
            return;
        }
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }

    // ================= INPUT HELPERS =================

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /** Keeps asking until the user types a whole number. */
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a whole number.");
            }
        }
    }
}
