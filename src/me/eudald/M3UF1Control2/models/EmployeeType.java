package me.eudald.M3UF1Control2.models;

public enum EmployeeType {
    TEACHER("Teacher"),
    CLEANER("Cleaner");

    private final String name;

    EmployeeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
