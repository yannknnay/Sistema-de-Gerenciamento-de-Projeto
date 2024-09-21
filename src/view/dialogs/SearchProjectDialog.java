package view.dialogs;

import controller.ManagerProject;
import model.Project;

import javax.swing.*;
import java.awt.*;

public class SearchProjectDialog extends JDialog {
    private final ManagerProject manager;
    private JTextField projectIdField;
    private JTextArea textArea;

    public SearchProjectDialog(JFrame parent, ManagerProject manager) {
        super(parent, "Search Project", true);
        this.manager = manager;
        setSize(600, 400);
        setLocationRelativeTo(parent);
        createInterface();
    }

    private void createInterface() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel lblProjectId = new JLabel("Project ID:");
        projectIdField = new JTextField(10);
        JButton btnSearch = new JButton("Search");

        topPanel.add(lblProjectId);
        topPanel.add(projectIdField);
        topPanel.add(btnSearch);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);


        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> dispose());

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btnClose, BorderLayout.SOUTH);


        btnSearch.addActionListener(e -> searchProject());
    }

    private void searchProject() {
        Integer idProject = checkNumber(projectIdField.getText(), "Project ID");
        if (idProject == null) return;

        Project project = manager.getProject(idProject);
        if (project == null) {
            JOptionPane.showMessageDialog(this, "Project not found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        displayProjectDetails(project);
    }

    private void displayProjectDetails(Project project) {
        String projectDetails = manager.getFormattedProjectDetails(project);

        textArea.setText(projectDetails);
    }

    private Integer checkNumber(String input, String field) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, field + " must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}

