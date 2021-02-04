package me.eudald.M3UF1Control2.models;

import java.time.LocalDate;
import java.util.Objects;

public class Student extends Person {
    private int age;
    private boolean passed;
    private double tuition;

    public Student(String name,
                   String surname,
                   String dni,
                   String email,
                   LocalDate date,
                   int age,
                   boolean passed,
                   double tuition) {
        super(name, surname, dni, email, date);
        this.age = age;
        this.passed = passed;
        this.tuition = tuition;
    }

    public Student() {
    }

    public boolean hasPassed() {
        return passed;
    }

    @Override
    public String toString() {
        return String.format("Student{Person='%s', age=%d, passed=%s, tuition=%s}",
                super.toString(), age, passed, tuition);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getTuition() {
        return tuition;
    }

    public void setTuition(double tuition) {
        this.tuition = tuition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return age == student.age && passed == student.passed && Double.compare(student.tuition, tuition) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), age, passed, tuition);
    }
}
