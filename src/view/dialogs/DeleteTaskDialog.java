package view.dialogs;

import controller.ManagerProject;
import model.Project;

import javax.swing.*;
import java.awt.*;

public class DeleteTaskDialog extends JDialog {
    private JTextField projectId;
    private JTextField taskId;
    private final ManagerProject manager;

    public DeleteTaskDialog(JFrame parent, ManagerProject manager) {
        super(parent, "Delete Task", true);
        this.manager = manager;
        setSize(400, 200);
        setLocationRelativeTo(parent);
        creatInterface();
    }

    private void creatInterface() {
        setLayout(new GridLayout(3, 2,10,10));

        JLabel lblProjectId = new JLabel("Project ID:");
        lblProjectId.setHorizontalAlignment(SwingConstants.CENTER);
        projectId = new JTextField();

        JLabel lblTaskId = new JLabel("Task ID:");
        lblTaskId.setHorizontalAlignment(SwingConstants.CENTER);
        taskId = new JTextField();

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> deleteTask());

        add(lblProjectId);
        add(projectId);
        add(lblTaskId);
        add(taskId);
        add(new JLabel());
        add(btnDelete);

    }

    private void deleteTask() {
        Integer idProject = checkNumber(this.projectId.getText(), "Project ID");
        if (idProject == null) return;

        Project project = manager.getProject(idProject);
        if (project == null) {
            JOptionPane.showMessageDialog(this, "Project not found", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Integer idTask = checkNumber(this.taskId.getText(), "Task ID");
        if (idTask == null) return;

        if (project.deleteTask(idTask)) {
            JOptionPane.showMessageDialog(this,"Task deleted", "Sucess", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,"Task not found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    private Integer checkNumber(String input, String field) {
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, field + " cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, field + " must be a valid number.", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
