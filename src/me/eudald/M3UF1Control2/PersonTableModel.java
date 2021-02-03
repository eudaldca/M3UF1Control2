package me.eudald.M3UF1Control2;

import me.eudald.M3UF1Control2.models.Person;

import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PersonTableModel extends AbstractTableModel {
    private final String[] columnNames = new String[]{"name", "surname", "dni", "email", "date"};
    private final List<Person> people;
    private static final DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ROOT);

    public PersonTableModel(List<Person> people) {
        this.people = people;
    }

    public PersonTableModel() {
        people = new ArrayList<>();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return people.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return people.get(rowIndex).getName();
            case 1:
                return people.get(rowIndex).getSurname();
            case 2:
                return people.get(rowIndex).getDni();
            case 3:
                return people.get(rowIndex).getEmail();
            case 4:
                return people.get(rowIndex).getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        }
        return null;
    }
}
