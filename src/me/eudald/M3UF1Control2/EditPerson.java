package me.eudald.M3UF1Control2;

import me.eudald.M3UF1Control2.models.Employee;
import me.eudald.M3UF1Control2.models.EmployeeType;
import me.eudald.M3UF1Control2.models.Person;
import me.eudald.M3UF1Control2.models.Student;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

    private final PersonTableModel model;
    private final int row;
    private final Person person;

    public EditPerson(PersonTableModel model, int row, Person person) {
        this.model = model;
        this.row = row;
        this.person = person;
        if (this.person != null) setValues(this.person);

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
        if (model != null) {
            if (person != null) model.updatePersonAt(row, getValues());
            else model.addPerson(getValues());
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        EditPerson dialog = new EditPerson(null, -1, new Employee());
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }


    private Person getValues() {
        if ((person != null && person instanceof Student))
            new Student(
                    nameTextField.getText(),
                    surnameTextField.getText(),
                    dniTextField.getText(),
                    emailTextField.getText(),
                    Student.parseDate(dateTextField.getText()),
                    (int) ageSpinner.getValue(),
                    passedCheckbox.isSelected(),
                    ((Integer) tuitionSpinner.getValue()).doubleValue()
            );
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
        } else if (person instanceof Employee) {
            salarySpinner.setValue(((Employee) person).getSalary());
            positionComboBox.setSelectedItem(((Employee) person).getType());
        }
    }
}
