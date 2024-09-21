package view.dialogs;

import controller.ManagerProject;
import model.*;

import javax.swing.*;
import java.awt.*;

public class UpdateStatusDialog extends JDialog {
    private JTextField projectId;
    private JTextField taskId;
    private JComboBox<Status> statusOptions;
    private final ManagerProject manager;

    public UpdateStatusDialog(JFrame parent, ManagerProject manager) {
        super(parent, "Update Task Status", true);
        this.manager = manager;
        setSize(400, 200);
        setLocationRelativeTo(parent);
        createInterface();
    }

    private void createInterface() {
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblProjectId = new JLabel("Project ID:");
        lblProjectId.setHorizontalAlignment(SwingConstants.CENTER);
        projectId = new JTextField();

        JLabel lblTaskId = new JLabel("Task ID:");
        lblTaskId.setHorizontalAlignment(SwingConstants.CENTER);
        taskId = new JTextField();

        JLabel lblStatus = new JLabel("New Status:");
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);


        statusOptions = new JComboBox<>(Status.values());

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> updateStatus());

        add(lblProjectId);
        add(projectId);
        add(lblTaskId);
        add(taskId);
        add(lblStatus);
        add(statusOptions);
        add(new JLabel());
        add(btnUpdate);
    }

    private void updateStatus() {
        Integer idProject = checkNumber(this.projectId.getText(), "Project ID");
        if (idProject == null) return;

        Project project = manager.getProject(idProject);
        if (project == null) {
            JOptionPane.showMessageDialog(this, "Project not found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Integer idTask = checkNumber(this.taskId.getText(), "Task ID");
        if (idTask == null) return;

        Task task = project.searchTask(idTask);
        if (task == null) {
            JOptionPane.showMessageDialog(this, "Task not found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Status currentStatus = task.getStatus();
        Status newStatus = (Status) statusOptions.getSelectedItem();

        int confirmation = JOptionPane.showConfirmDialog(this,
                "Current Status: " + currentStatus + "\nUpdate to: " + newStatus + "?",
                "Confirm Update", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            task.setStatus(newStatus);
            JOptionPane.showMessageDialog(this, "Task status updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(this, field + " must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
