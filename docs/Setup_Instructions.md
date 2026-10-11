# Setup Instructions

## JDK version used

```
openjdk version "21.0.12.1" 2026-08-18 LTS
OpenJDK Runtime Environment Temurin-21.0.12.1+1
```

## Installing the JDK

1. Download Eclipse Temurin JDK 21 (LTS) from https://adoptium.net and run the installer.
2. Check the install in a terminal:
   ```bash
   java -version
   javac -version
   ```
   Both commands should print version 21.

## Hello World

`HelloWorld.java`:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Compile and run:

```bash
javac HelloWorld.java
java HelloWorld
```

Output:

```
Hello, World!
```

`javac` compiles the source file into bytecode (`HelloWorld.class`). `java` starts the JVM, which loads that class and runs its `main` method.

<!-- TODO: add a screenshot of the Hello World run here, e.g. ![Hello World run](images/hello_world.png) -->

## Running LearnTrack

See the [README](../README.md#how-to-compile-and-run).
