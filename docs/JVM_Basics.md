# JVM Basics

## JDK, JRE and JVM

- **JVM (Java Virtual Machine):** the program that actually runs Java code. It reads bytecode, manages memory (including garbage collection) and turns bytecode into instructions for the real CPU.
- **JRE (Java Runtime Environment):** the JVM plus the standard class libraries (`java.lang`, `java.util`, and so on). It is everything needed to *run* a Java program.
- **JDK (Java Development Kit):** the JRE plus developer tools such as the compiler `javac`, the debugger and `jar`. It is what you need to *write and build* Java programs.

In short: **JDK ⊃ JRE ⊃ JVM**.

## What is bytecode?

When you run `javac Main.java`, the compiler does not produce machine code for your particular processor. Instead it produces `Main.class`, which contains **bytecode**: a compact set of instructions designed for the JVM. The JVM then interprets this bytecode, and compiles frequently used parts to native machine code at runtime (the JIT, or just-in-time compiler) for speed.

## "Write once, run anywhere"

Because Java source is compiled to bytecode rather than to machine code for one operating system, the same `.class` files can run on any machine that has a JVM: Windows, macOS or Linux. Each platform has its own JVM that knows how to translate bytecode for that system.

So a developer writes and compiles the program once, and it runs everywhere a compatible JVM exists, without rewriting or recompiling it for each operating system. This is what "write once, run anywhere" means.
