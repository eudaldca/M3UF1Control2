package me.eudald.M3UF1Control2;

import me.eudald.M3UF1Control2.models.Person;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {
    private final String[] columnNames = new String[]{"name", "surname", "dni", "email", "date"};
    private final List<Person> people;

    public StudentTableModel(List<Person> people) {
        this.people = people;
    }

    public StudentTableModel() {
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
                return people.get(rowIndex).getDate().toString();
        }
        return null;
    }
}
