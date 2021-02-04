package me.eudald.M3UF1Control2;

import com.formdev.flatlaf.FlatDarkLaf;
import me.eudald.M3UF1Control2.models.*;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DrivingSchoolApp {
    private JPanel appPanel;
    private JTable studentsTable;
    private JButton addStudentButton;
    private JButton editPersonButton;
    private JButton deletePersonButton;
    private JButton addEmployeeButton;
    private JButton addTeacherButton;
    private final PersonTableModel tableModel;

    public DrivingSchoolApp() {
        tableModel = new PersonTableModel(testData());
        studentsTable.setModel(tableModel);
        studentsTable.setRowHeight(50);
        studentsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


        resizeColumnWidth(studentsTable);

        editPersonButton.addActionListener(e -> {
            int selectedRow = studentsTable.getSelectedRow();
            Person selectedPerson = tableModel.getPersonAt(selectedRow);
            Class<? extends Person> p = selectedPerson.getClass();
            JDialog dialog = null;
            if (p == Student.class) dialog = new EditPerson(tableModel, selectedRow, selectedPerson);
            if (dialog != null) {
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        addStudentButton.addActionListener(e -> {
            EditPerson dialog = new EditPerson(tableModel, -1, new Student());
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });
        deletePersonButton.addActionListener(e -> tableModel.removePersonAt(studentsTable.getSelectedRow()));
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
        result.add(new Student("Carles", "Pascual", "12541254F", "carles@campus.monlau.com", LocalDate.now(), 22, true, 1200));
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
            if (width > 300)
                width = 300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

}