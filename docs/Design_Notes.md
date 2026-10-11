# Design Notes

## Why ArrayList instead of an array?

An array has a fixed size chosen when it is created. In LearnTrack we don't know in advance how many students, courses or enrollments an admin will add. With an array we would have to guess a size, track how many slots are used, and copy everything into a bigger array when it fills up.

`ArrayList` grows automatically, and it has ready-made methods such as `add`, `remove`, `isEmpty` and `size`. That keeps the repository classes short and easy to read. Because the lists are generic (`ArrayList<Student>`), the compiler also checks that only the right type of object goes in.

## Where static members are used, and why

| Where | What | Why |
|---|---|---|
| `util.IdGenerator` | `private static int studentIdCounter`, `courseIdCounter`, `enrollmentIdCounter` and `static getNext...Id()` methods | IDs must be unique across the whole application. A static counter belongs to the class, so there is exactly one counter no matter how many objects are created. |
| `util.InputValidator` | `static` validation methods | They don't need any object state, so there is no reason to create an instance. |
| `constants.MenuOptions`, `constants.AppConstants` | `public static final` constants | Fixed values shared by everyone. Using names like `MenuOptions.ADD_STUDENT` instead of `1` makes the `switch` statements readable. |
| `Main` | `static` services and `Scanner` | `main` is static, so the objects it uses directly are created once as static fields. |

Utility and constants classes have a `private` constructor so nobody can create instances of them by mistake.

## Where inheritance is used, and what we gained

`Person` is the base class with the fields every person has: `id`, `firstName`, `lastName`, `email`. `Student` and `Trainer` both `extend Person`:

- **Less duplication:** the shared fields, getters and setters are written once in `Person`.
- **`super` in constructors:** `Student` calls `super(id, firstName, lastName, email)` to let `Person` set up the shared fields, and then sets its own `batch` and `active`.
- **Method overriding / polymorphism:** `Person.getDisplayName()` returns the full name. `Student` overrides it to add the batch, and `Trainer` overrides it to add the expertise. A `Person` variable holding either a `Student` or a `Trainer` calls the right version at runtime.
- **Easy to extend:** adding another kind of person (for example an Admin) only needs a new subclass.

## Other design choices

- **Layers:** `entity` holds data, `repository` stores it, `service` holds business rules, and `Main` only handles menus and input. Each layer can be changed without touching the others.
- **Custom exceptions:** services throw `EntityNotFoundException` for unknown IDs and `InvalidInputException` for broken rules. `Main` catches them and prints a clean message, so the program never crashes on bad input.
- **Overloading:** `Student` and `Person` have overloaded constructors (with and without email), `Enrollment` has an overloaded constructor (with or without a date), and `StudentService.addStudent` is overloaded the same way.
- **Enum for status:** `EnrollmentStatus` prevents typos like `"COMPLETD"` that a plain String would allow.
- **Soft delete:** "Deactivate" sets `active = false` so history (such as enrollments) is kept. "Remove" is offered separately to show a real delete.

## Reference

The code deliberately sticks to the topics in *Java from the Ground Up*. Comments in the code point to the matching module, for example:

| Concept | Where in LearnTrack | Module |
|---|---|---|
| Static vs instance members | `IdGenerator` counters | 5.2 |
| Constructors, `this(...)` | `Person`, `Student`, `Enrollment` | 5.3 |
| Packages, access modifiers, encapsulation | all packages, private fields + getters/setters | 7 |
| Inheritance, `super(...)` | `Person` → `Student`, `Trainer` | 8 |
| Overloading and overriding | `addStudent(...)`, `getDisplayName()` | 9.2, 9.3 |
| try-catch, custom exceptions | `Main.readInt`, `EntityNotFoundException` | 10.3, 10.7 |
| Enums | `EnrollmentStatus` | 11.5 |
| `ArrayList` | repositories | 11.6 |
| Reading input with `Scanner` | `Main.readLine`, `Main.readInt` | 4.5 |
