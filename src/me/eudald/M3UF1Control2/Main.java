package me.eudald.M3UF1Control2;

import me.eudald.M3UF1Control2.models.Employee;
import me.eudald.M3UF1Control2.models.EmployeeType;
import me.eudald.M3UF1Control2.models.Student;
import me.eudald.M3UF1Control2.models.Teacher;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Student carles = new Student("Carles",
                "Pascual",
                "12541254F",
                "carles@campus.monlau.com",
                LocalDate.now(),
                22,
                null,
                1200);
        Teacher gerard = new Teacher("Gerard", "Amiriam", "123456N", "gerard@monlau.com", LocalDate.now(), 2900);
        Employee berta = new Employee("Berta", "García", "12548796G", null, LocalDate.now(), 1200, EmployeeType.CLEANER);
        System.out.println();
        System.out.println(gerard.toString());
        System.out.println(berta.toString());
        System.out.println(carles.toString());
        System.out.println(carles.getDate().toString());
    }
}
