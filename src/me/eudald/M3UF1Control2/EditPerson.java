package me.eudald.M3UF1Control2;

import me.eudald.M3UF1Control2.models.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class EditPerson extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField dniTextField;
    private JTextField emailTextField;
    private JTextField dateTextField;
    private JSpinner ageSpinner;
    private JCheckBox passedCheckbox;
    private JSpinner tuitionSpinner;
    private JSpinner salarySpinner;
    private JComboBox<EmployeeType> positionComboBox;
    private JPanel employeePanel;
    private JPanel studentPanel;
    private JLabel positionLabel;
    private JComboBox<Teacher> teacherComboBox;

    private final PersonTableModel model;
    private final int row;
    private final Person person;

    public EditPerson(PersonTableModel model, int row, Person person) {
        this.model = model;
        this.row = row;
        this.person = person;

        //add items to combo boxes
        Arrays.stream(EmployeeType.values()).forEach(positionComboBox::addItem);
        if (model != null) model.getPeople()
                .stream().filter(Teacher.class::isInstance)
                .map(Teacher.class::cast)
                .forEach(teacherComboBox::addItem);

        positionComboBox.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            BasicComboBoxRenderer bcr = new BasicComboBoxRenderer();
            if (value != null) bcr.setText(value.getName());
            return bcr;
        });
        teacherComboBox.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            BasicComboBoxRenderer bcr = new BasicComboBoxRenderer();
            if (value != null) bcr.setText(value.getName());
            return bcr;
        });
        if (row < 0) {
            teacherComboBox.setSelectedItem(null);
            positionComboBox.setSelectedItem(null);
        }

        if (this.person.getName() != null) setValues(this.person);

        employeePanel.setVisible(this.person instanceof Employee);
        studentPanel.setVisible(this.person instanceof Student);

        positionLabel.setVisible(!(this.person instanceof Teacher));
        positionComboBox.setVisible(!(this.person instanceof Teacher));


        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        try {
            if (model != null) {
                if (row >= 0) model.updatePersonAt(row, getValues());
                else model.addPerson(getValues());
            }
            dispose();
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Incorrect date format" +
                    "\nCorrect format is dd/MM/yy");
        }
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        EditPerson dialog = new EditPerson(null, -1, new Student());
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }


    private double getDouble(Object value) {
        if (value instanceof Double) return (double) value;
        else if (value instanceof Integer) return ((Integer) value).doubleValue();
        return 0;
    }

    private Person getValues() throws DateTimeParseException {
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String dni = dniTextField.getText();
        String email = emailTextField.getText();
        LocalDate date = Student.parseDate(dateTextField.getText());
        if (person != null)
            if (person instanceof Student) {
                Object value = tuitionSpinner.getValue();
                return new Student(name, surname, dni, email, date, (int) ageSpinner.getValue(),
                        passedCheckbox.isSelected(), getDouble(value), ((Teacher) teacherComboBox.getSelectedItem()));
            }
        if (person instanceof Employee) {
            Object value = salarySpinner.getValue();
            double salary = value instanceof Double ? (double) value : ((Integer) value).doubleValue();
            if (person instanceof Teacher)
                return new Teacher(name, surname, dni, email, date, salary);
            return new Employee(name, surname, dni, email, date, salary, (EmployeeType) positionComboBox.getSelectedItem());
        }
        return null;
    }

    private void setValues(Person person) {
        nameTextField.setText(person.getName());
        surnameTextField.setText(person.getSurname());
        dniTextField.setText(person.getDni());
        emailTextField.setText(person.getEmail());
        dateTextField.setText(person.getFormattedDate());
        if (person instanceof Student) {
            ageSpinner.setValue(((Student) person).getAge());
            passedCheckbox.setSelected(((Student) person).hasPassed());
            tuitionSpinner.setValue(((Student) person).getTuition());
            teacherComboBox.setSelectedItem(((Student) person).getTeacher());
        } else if (person instanceof Employee) {
            salarySpinner.setValue(((Employee) person).getSalary());
            positionComboBox.setSelectedItem(((Employee) person).getType());
        }
    }
}
