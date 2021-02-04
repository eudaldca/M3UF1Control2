package me.eudald.M3UF1Control2.models;

import java.time.LocalDate;
import java.util.Objects;

public class Employee extends Person {
    private double salary;
    private EmployeeType type;

    public Employee(String name,
                    String surname,
                    String dni,
                    String email,
                    LocalDate date,
                    double salary,
                    EmployeeType type) {
        super(name, surname, dni, email, date);
        this.salary = salary;
        this.type = type;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return String.format("Employee{Person='%s', salary=%s, type='%s'}", super.toString(), salary, type.toString());
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 && type == employee.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary, type);
    }
}

