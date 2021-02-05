package me.eudald.M3UF1Control2.models;

import java.time.LocalDate;

public class Teacher extends Employee {
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
        return String.format("Teacher{Employee='%s'}", super.toString());
    }
}
