package me.eudald.M3UF1Control2;

import com.formdev.flatlaf.FlatDarkLaf;
import me.eudald.M3UF1Control2.models.*;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class DrivingSchoolApp {
    private JPanel appPanel;
    private JTable studentsTable;
    private JButton addStudentButton;
    private JButton editPersonButton;
    private JButton deletePersonButton;
    private JButton addEmployeeButton;
    private JButton addTeacherButton;
    private JButton statsButton;
    private final PersonTableModel tableModel;

    public DrivingSchoolApp() {
        tableModel = new PersonTableModel(testData());
        studentsTable.setModel(tableModel);
        studentsTable.setRowHeight(50);
        studentsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        resizeColumnWidth(studentsTable);

        //listeners
        editPersonButton.addActionListener(e -> {
            int selectedRow = studentsTable.getSelectedRow();
            if (selectedRow >= 0) {
                JDialog dialog = new EditPerson(tableModel,
                        selectedRow,
                        tableModel.getPersonAt(selectedRow));
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });

        addStudentButton.addActionListener(e -> addPersonDialog(new Student()));
        addEmployeeButton.addActionListener(e -> addPersonDialog(new Employee()));
        addTeacherButton.addActionListener(e -> addPersonDialog(new Teacher()));

        deletePersonButton.addActionListener(e -> {
            int selectedRow = studentsTable.getSelectedRow();
            if (selectedRow >= 0) tableModel.removePersonAt(selectedRow);
        });
        statsButton.addActionListener(e -> {
            JDialog dialog = new JDialog();
            dialog.setLayout(new GridLayout(0, 2));
            getStats(tableModel.getPeople()).forEach((key, value) -> {
                dialog.add(new JLabel(key + ": ", SwingConstants.RIGHT));
                dialog.add(new JLabel(value));
            });
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });
    }

    private void addPersonDialog(Person person) {
        EditPerson dialog = new EditPerson(tableModel, -1, person);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("P04");
        FlatDarkLaf.install();
        frame.setIconImage(new ImageIcon("assets/icon.png").getImage());
        frame.setContentPane(new DrivingSchoolApp().appPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(700, 500);
        frame.setMinimumSize(new Dimension(470, 250));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static List<Person> testData() {
        List<Person> result = new ArrayList<>();
        result.add(new Student("Carles", "Pascual", "12541254F", "carles@campus.monlau.com", LocalDate.now(), 22, true, 1200, null));
        result.add(new Teacher("Gerard", "Amiriam", "123456N", "gerard@monlau.com", LocalDate.now(), 2900));
        result.add(new Employee("Berta", "García", "12548796G", null, LocalDate.now(), 1200, EmployeeType.CLEANER));
        return result;
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) width = 300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    private Map<String, String> getStats(List<Person> people) {
        Map<String, String> result = new LinkedHashMap<>();
        DecimalFormat df = new DecimalFormat("0.##");
        result.put("Cost", df.format(people.stream()
                .filter(p -> p instanceof Employee).map(Employee.class::cast)
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum)
        ));
        result.put("Tuitions", df.format(people.stream()
                .filter(p -> p instanceof Student).map(Student.class::cast)
                .map(Student::getTuition)
                .reduce(0.0, Double::sum)
        ));

        result.put("Number of Employees", String.valueOf(people.stream().filter(p -> p instanceof Employee).count()));
        result.put("Number of Students", String.valueOf(people.stream().filter(p -> p instanceof Student).count()));
        result.put("Number of Teachers", String.valueOf(people.stream().filter(p -> p instanceof Teacher).count()));

        people.stream().filter(p -> p instanceof Student)
                .map(Student.class::cast)
                .map(Student::getTeacher).filter(Objects::nonNull)
                //group by teacher, count number of repetitions (equals number of students)
                .collect(Collectors.groupingBy(t -> t, Collectors.counting()))
                .forEach((teacher, count) -> result.put(teacher.getName(), count.toString() + " student"));

        people.stream().filter(p -> p instanceof Student)
                .map(Student.class::cast).filter(s -> s.getTeacher() != null)
                //group by teacher, count number of repetitions (equals number of students)
                .collect(Collectors.groupingBy(Student::getTeacher,
                        Collectors.mapping(Student::getName,
                                Collectors.joining(", "))))
                .forEach((teacher, s) -> result.put(teacher.getName(), s));
        return result;
    }

}