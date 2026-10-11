package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
import java.util.ArrayList;

/**
 * Business rules for students.
 */
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /** Adds a student without an email address. */
    public Student addStudent(String firstName, String lastName, String batch)
            throws InvalidInputException {
        Student student = new Student(IdGenerator.getNextStudentId(),
                InputValidator.requireNonEmpty(firstName, "First name"),
                InputValidator.requireNonEmpty(lastName, "Last name"),
                InputValidator.requireNonEmpty(batch, "Batch"));
        studentRepository.save(student);
        return student;
    }

    /** Method overloading: same name, extra email parameter. Adds a student with an email address. */
    public Student addStudent(String firstName, String lastName, String email, String batch)
            throws InvalidInputException {
        Student student = new Student(IdGenerator.getNextStudentId(),
                InputValidator.requireNonEmpty(firstName, "First name"),
                InputValidator.requireNonEmpty(lastName, "Last name"),
                InputValidator.validateEmail(email),
                InputValidator.requireNonEmpty(batch, "Batch"));
        studentRepository.save(student);
        return student;
    }

    public ArrayList<Student> listStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentById(int id) throws EntityNotFoundException {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new EntityNotFoundException("Student", id);
        }
        return student;
    }

    /** Updates name, email and batch. Blank values keep the current value. */
    public Student updateStudent(int id, String firstName, String lastName, String email, String batch)
            throws EntityNotFoundException, InvalidInputException {
        Student student = findStudentById(id);
        if (!isBlank(firstName)) {
            student.setFirstName(firstName.trim());
        }
        if (!isBlank(lastName)) {
            student.setLastName(lastName.trim());
        }
        if (!isBlank(email)) {
            student.setEmail(InputValidator.validateEmail(email));
        }
        if (!isBlank(batch)) {
            student.setBatch(batch.trim());
        }
        return student;
    }

    public void deactivateStudent(int id) throws EntityNotFoundException, InvalidInputException {
        Student student = findStudentById(id);
        if (!student.isActive()) {
            throw new InvalidInputException("Student " + id + " is already inactive.");
        }
        student.setActive(false);
    }

    public void removeStudent(int id) throws EntityNotFoundException {
        if (!studentRepository.delete(id)) {
            throw new EntityNotFoundException("Student", id);
        }
    }

    private boolean isBlank(String value) {
        return value.trim().isEmpty();
    }
}
