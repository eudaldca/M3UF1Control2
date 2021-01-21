package me.eudald.M3UF1Control2.models;

import java.time.LocalDate;
import java.util.Locale;

public class ExamRecord {
    private LocalDate date;
    private boolean passed;
    private ExamType type;

    public ExamRecord(LocalDate date, boolean passed, ExamType type) {
        this.date = date;
        this.passed = passed;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("ExamRecord{date=%s, passed=%s, type=%s}",
                date.toString(), passed, type.toString().toLowerCase(Locale.ROOT));
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public ExamType getType() {
        return type;
    }

    public void setType(ExamType type) {
        this.type = type;
    }
}
