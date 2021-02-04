package me.eudald.M3UF1Control2.models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Teacher extends Employee {
    private Set<Student> students;

    public Teacher(String name,
                   String surname,
                   String dni,
                   String email,
                   LocalDate date,
                   double salary) {
        super(name, surname, dni, email, date, salary, EmployeeType.TEACHER);
    }

    public Teacher() {
    }

    @Override
    public String toString() {
        return String.format("Teacher{Employee='%s', students=%s}", super.toString(), students);
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(students, teacher.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), students);
    }
}
