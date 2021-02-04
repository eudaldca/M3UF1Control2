package me.eudald.M3UF1Control2.models;

public enum PersonType {
    STUDENT(-1), EMPLOYEE(-2), TEACHER(-3);

    private final int value;

    PersonType(int value) {
        this.value = value;
    }
}
