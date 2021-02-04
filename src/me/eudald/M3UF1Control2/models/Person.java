package me.eudald.M3UF1Control2.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

public abstract class Person {
    private String name;
    private String surname;
    private String dni;
    private String email;
    private LocalDate date;
    public static final DateTimeFormatter DF = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);

    public Person(String name, String surname, String dni, String email, LocalDate date) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.email = email;
        this.date = date;
    }

    public Person() {

    }

    @Override
    public String toString() {
        return String.format("Person{name='%s', surname='%s', dni='%s', email='%s', date=%s}",
                name, surname, dni, email, date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDate(String date) {
        setDate(parseDate(date));
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, Student.DF);
    }

    public String getFormattedDate() {
        return date.format(DF);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(surname, person.surname)
                && Objects.equals(dni, person.dni) && Objects.equals(email, person.email)
                && Objects.equals(date, person.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, dni, email, date);
    }
}
