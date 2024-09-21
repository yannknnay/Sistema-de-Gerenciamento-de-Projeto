package view.dialogs;

import controller.ManagerProject;

import javax.swing.*;
import java.awt.*;

public class DeleteProjectDialog extends JDialog {
    private JTextField idProject;
    private final ManagerProject manager;

    public DeleteProjectDialog(JFrame parent, ManagerProject manager) {
        super(parent,"Delete Project", true);
        this.manager = manager;
        setSize(200,100);
        setLocationRelativeTo(parent);
        creatInterface();
    }

    private void creatInterface() {
        setLayout(new GridLayout(2,2,10,10));

        JLabel lblId = new JLabel("Project ID:");
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        idProject = new JTextField();

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> deleteProject());

        add(lblId);
        add(idProject);
        add(new JLabel(" "));
        add(btnDelete);
    }

    private void deleteProject() {
        try{
            int id = Integer.parseInt(idProject.getText());

            if (manager.deleteProject(id)) {
                JOptionPane.showMessageDialog(this,"Project deleted", "Sucess", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,"Project not found", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "The ID must be a number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
