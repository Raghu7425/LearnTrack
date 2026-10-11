package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
import java.util.ArrayList;

/**
 * Business rules for courses.
 */
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(String courseName, String description, int durationInWeeks)
            throws InvalidInputException {
        String name = InputValidator.requireNonEmpty(courseName, "Course name");
        int weeks = InputValidator.requirePositive(durationInWeeks, "Duration");
        Course course = new Course(IdGenerator.getNextCourseId(), name, description.trim(), weeks);
        courseRepository.save(course);
        return course;
    }

    public ArrayList<Course> listCourses() {
        return courseRepository.findAll();
    }

    public Course findCourseById(int id) throws EntityNotFoundException {
        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new EntityNotFoundException("Course", id);
        }
        return course;
    }

    public void activateCourse(int id) throws EntityNotFoundException, InvalidInputException {
        Course course = findCourseById(id);
        if (course.isActive()) {
            throw new InvalidInputException("Course " + id + " is already active.");
        }
        course.setActive(true);
    }

    public void deactivateCourse(int id) throws EntityNotFoundException, InvalidInputException {
        Course course = findCourseById(id);
        if (!course.isActive()) {
            throw new InvalidInputException("Course " + id + " is already inactive.");
        }
        course.setActive(false);
    }
}
