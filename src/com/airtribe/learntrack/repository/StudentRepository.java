package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;

/**
 * In-memory storage for students.
 */
public class StudentRepository {

    private final ArrayList<Student> students = new ArrayList<>();

    public void save(Student student) {
        students.add(student);
    }

    public Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> findAll() {
        return students;
    }

    public boolean delete(int id) {
        Student student = findById(id);
        if (student == null) {
            return false;
        }
        students.remove(student);
        return true;
    }
}
