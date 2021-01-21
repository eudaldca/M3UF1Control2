package me.eudald.M3UF1Control2;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

public class DrivingSchoolApp {
    private JPanel appPanel;
    private JTable studentsTable;
    private JButton addPersonButton;
    private JButton editPersonButton;
    StudentTableModel tableModel = new StudentTableModel();

    public DrivingSchoolApp() {
        studentsTable.setModel(tableModel);
/*        editPersonButton.addActionListener(e -> {
            int selectedRow = studentsTable.getSelectedRow();
            EditPerson dialog = new EditStudent(tableModel.getStudentAt(selectedRow), selectedRow, tableModel);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        });
        addPersonButton.addActionListener(e -> {
            EditPerson dialog = new EditStudent(null, null, tableModel);
            dialog.pack();
            dialog.setVisible(true);
        });*/
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

}