# LearnTrack — Student & Course Management System

LearnTrack is a menu-driven console application written in Core Java. An admin can manage **students**, **courses** and **enrollments**. All data is kept in memory using `ArrayList`.

## Features

**Student management:** add (with or without email), view all, search by ID, update, deactivate, remove.
**Course management:** add, view all, activate, deactivate.
**Enrollment management:** enroll a student in a course, view a student's enrollments, mark completed, cancel.

Invalid input (letters instead of numbers, unknown menu options, IDs that don't exist, blank names, bad emails) shows a clear error message instead of crashing.

## Requirements

- JDK 17 or later (developed with Temurin JDK 21)

## How to compile and run

From the `LearnTrack` folder:

```bash
javac -d bin $(find src -name "*.java")
java -cp bin com.airtribe.learntrack.Main
```

On Windows (PowerShell):

```powershell
javac -d bin (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp bin com.airtribe.learntrack.Main
```

In an IDE, open the `LearnTrack` folder and run `Main.java`.

## Project structure

```
src/com/airtribe/learntrack/
├── Main.java                  Console menus: reads input, calls services
├── entity/                    Person, Student, Trainer, Course, Enrollment
├── repository/                In-memory ArrayList storage
├── service/                   Business rules
├── exception/                 EntityNotFoundException, InvalidInputException
├── util/                      IdGenerator, InputValidator
├── constants/                 MenuOptions, AppConstants
└── enums/                     EnrollmentStatus
docs/
├── Setup_Instructions.md
├── JVM_Basics.md
└── Design_Notes.md
```

## Class diagram

```mermaid
classDiagram
    class Person {
        -int id
        -String firstName
        -String lastName
        -String email
        +Person(id, firstName, lastName)
        +Person(id, firstName, lastName, email)
        +getDisplayName() String
    }
    class Student {
        -String batch
        -boolean active
        +Student(id, firstName, lastName, batch)
        +Student(id, firstName, lastName, email, batch)
        +getDisplayName() String
    }
    class Trainer {
        -String expertise
        +getDisplayName() String
    }
    class Course {
        -int id
        -String courseName
        -String description
        -int durationInWeeks
        -boolean active
    }
    class Enrollment {
        -int id
        -int studentId
        -int courseId
        -LocalDate enrollmentDate
        -EnrollmentStatus status
    }
    class EnrollmentStatus {
        <<enumeration>>
        ACTIVE
        COMPLETED
        CANCELLED
    }
    class StudentRepository {
        -ArrayList~Student~ students
    }
    class CourseRepository {
        -ArrayList~Course~ courses
    }
    class EnrollmentRepository {
        -ArrayList~Enrollment~ enrollments
    }
    class StudentService {
        +addStudent(firstName, lastName, batch) Student
        +addStudent(firstName, lastName, email, batch) Student
        +listStudents() ArrayList
        +findStudentById(id) Student
        +updateStudent(...) Student
        +deactivateStudent(id)
        +removeStudent(id)
    }
    class CourseService {
        +addCourse(name, description, weeks) Course
        +listCourses() ArrayList
        +findCourseById(id) Course
        +activateCourse(id)
        +deactivateCourse(id)
    }
    class EnrollmentService {
        +enrollStudent(studentId, courseId) Enrollment
        +getEnrollmentsForStudent(studentId) ArrayList
        +updateEnrollmentStatus(id, status)
    }
    class IdGenerator {
        <<utility>>
        -static int studentIdCounter
        -static int courseIdCounter
        -static int enrollmentIdCounter
        +static getNextStudentId() int
        +static getNextCourseId() int
        +static getNextEnrollmentId() int
    }
    class Main {
        +main(String[] args)
    }

    Person <|-- Student
    Person <|-- Trainer
    Enrollment --> EnrollmentStatus
    Enrollment ..> Student : studentId
    Enrollment ..> Course : courseId
    StudentRepository o-- Student
    CourseRepository o-- Course
    EnrollmentRepository o-- Enrollment
    StudentService --> StudentRepository
    CourseService --> CourseRepository
    EnrollmentService --> EnrollmentRepository
    EnrollmentService --> StudentService
    EnrollmentService --> CourseService
    StudentService ..> IdGenerator
    CourseService ..> IdGenerator
    EnrollmentService ..> IdGenerator
    Main --> StudentService
    Main --> CourseService
    Main --> EnrollmentService
```

## Documentation

- [Setup instructions](docs/Setup_Instructions.md)
- [JVM basics](docs/JVM_Basics.md)
- [Design notes](docs/Design_Notes.md)
