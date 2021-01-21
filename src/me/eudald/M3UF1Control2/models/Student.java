package me.eudald.M3UF1Control2.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Student extends Person {
    private int age;
    private List<ExamRecord> exams;
    private double tuition;

    public Student(String name,
                   String surname,
                   String dni,
                   String email,
                   LocalDate date,
                   int age,
                   List<ExamRecord> exams,
                   double tuition) {
        super(name, surname, dni, email, date);
        this.age = age;
        this.exams = exams;
        this.tuition = tuition;
    }

    public boolean hasPassed(ExamType type) {
        return exams.stream()
                .filter(e -> e.getType() == type) //filter only set type
                .anyMatch(ExamRecord::isPassed); //return true if they passed
    }

    @Override
    public String toString() {
        String examsString = exams == null
                ? ""
                : String.format(" exams{%s},",
                exams.stream().map(ExamRecord::toString).collect(Collectors.joining(",")));
        return String.format("Student{Person='%s', age=%d,%s tuition=%s}",
                super.toString(), age, examsString,
                tuition);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<ExamRecord> getExams() {
        return exams;
    }

    public void setExams(List<ExamRecord> exams) {
        this.exams = exams;
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
        return age == student.age && Double.compare(student.tuition, tuition) == 0
                && Objects.equals(exams, student.exams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), age, exams, tuition);
    }
}
