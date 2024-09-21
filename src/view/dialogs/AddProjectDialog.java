package view.dialogs;

import controller.ManagerProject;

import javax.swing.*;
import java.awt.*;

public class AddProjectDialog extends JDialog {
    private JTextField id;
    private JTextField name;
    private JTextArea description;
    private final ManagerProject manager;

    public AddProjectDialog(JFrame parent, ManagerProject manager) {
        super(parent,"Add Project", true);
        this.manager = manager;
        setSize(300, 200);
        setLocationRelativeTo(parent);
        creatInterface();
    }

    private void creatInterface() {
        setLayout(new GridLayout(4, 2, 10,10));

        JLabel lblId = new JLabel("ID:");
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        id = new JTextField();

        JLabel lblName = new JLabel("Name:");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        name = new JTextField();

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
        description = new JTextArea();
        description.setLineWrap(true);
        description.setWrapStyleWord(true);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> saveProject());

        add(lblId);
        add(id);
        add(lblName);
        add(name);
        add(lblDescription);
        add(description);
        add(new JScrollPane(description));
        add(new JLabel(""));
        add(btnSave);
    }

    private void saveProject() {
        try {
            int idProject = Integer.parseInt(id.getText());
            String nameProject = name.getText();
            String descriptionProject = description.getText();

            if (nameProject.isEmpty() || descriptionProject.isEmpty()) {
                JOptionPane.showMessageDialog(this,"Fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            manager.addProject(idProject, nameProject, descriptionProject);
            JOptionPane.showMessageDialog(this,"Project added", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "The ID must be a number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
