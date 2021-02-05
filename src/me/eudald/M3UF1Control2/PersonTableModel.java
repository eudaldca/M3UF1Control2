package me.eudald.M3UF1Control2;

import me.eudald.M3UF1Control2.models.Person;
import me.eudald.M3UF1Control2.models.Teacher;

import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PersonTableModel extends AbstractTableModel {
    private final String[] columnNames = new String[]{"name", "surname", "dni", "email", "date"};
    private final List<Person> people;

    public List<Person> getPeople() {
        return people;
    }

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

    public void addPerson(Person... person) {
        this.people.addAll(Arrays.asList(person));
        fireTableRowsInserted(people.size(), people.size() - 1 + person.length);
    }

    public Person getPersonAt(int row) {
        return people.get(row);
    }

    public void updatePersonAt(int row, Person person) {
        people.set(row, person);
        this.fireTableRowsUpdated(row, row);
    }

    public void removePersonAt(int row) {
        people.remove(row);
        fireTableRowsDeleted(row, row);
    }
}
