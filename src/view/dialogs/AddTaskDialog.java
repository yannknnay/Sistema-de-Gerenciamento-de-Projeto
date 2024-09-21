package view.dialogs;

import controller.ManagerProject;
import model.Task;
import model.Project;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class AddTaskDialog extends JDialog {
    private JTextField idProject;
    private JTextField idTask;
    private JTextField nameTask;
    private JTextArea description;
    private JTextField startDate;
    private JTextField endDate;
    private final ManagerProject manager;

    public AddTaskDialog(JFrame parent, ManagerProject manager) {
        super(parent, "Add Task", true);
        this.manager = manager;
        setSize(400, 400);
        setLocationRelativeTo(parent);
        creatInterface();
    }

    private void creatInterface() {
        setLayout(new GridLayout(7, 2, 10, 10));

        JLabel lblIdProject = new JLabel("Project ID:");
        lblIdProject.setHorizontalAlignment(SwingConstants. CENTER);
        idProject = new JTextField();

        JLabel lblIdTask = new JLabel("Task ID:");
        lblIdTask.setHorizontalAlignment(SwingConstants.CENTER);
        idTask = new JTextField();

        JLabel lblNameTask = new JLabel("Task Name:");
        lblNameTask.setHorizontalAlignment(SwingConstants.CENTER);
        nameTask = new JTextField();

        JLabel lblDescription = new JLabel("Task Description:");
        lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
        description = new JTextArea();
        description.setLineWrap(true);
        description.setWrapStyleWord(true);

        JLabel lblStartDate = new JLabel("Start Date (YYYY-MM-DD):");
        lblStartDate.setHorizontalAlignment(SwingConstants.CENTER);
        startDate = new JTextField();

        JLabel lblEndDate = new JLabel("End Date (YYYY-MM-DD):");
        lblEndDate.setHorizontalAlignment(SwingConstants.CENTER);
        endDate = new JTextField();


        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> addTask());

        add(lblIdProject);
        add(idProject);
        add(lblIdTask);
        add(idTask);
        add(lblNameTask);
        add(nameTask);
        add(lblDescription);
        add(new JScrollPane(description));
        add(lblStartDate);
        add(startDate);
        add(lblEndDate);
        add(endDate);
        add(new JLabel());
        add(btnSave);
    }

    private void addTask() {
        // Validate project existence
        Integer idProject = checkNumber(this.idProject.getText(), "Project ID");
        if (idProject == null) return;

        Project project = manager.getProject(idProject);
        if (project == null) {
            JOptionPane.showMessageDialog(this, "Project not found", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate Task
        Integer idTask = checkNumber(this.idTask.getText(), "Task ID");
        if (idTask == null) return;


        String nameTask = this.nameTask.getText();
        String description = this.description.getText();
        if (nameTask.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill in all fields", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate LocalDates
        LocalDate startDate = checkDate(this.startDate.getText(), "Start Date");
        LocalDate endDate = checkDate(this.endDate.getText(), "End Date");
        if (startDate == null || endDate == null) return;

        // Task creation
        Task task = new Task(idTask, nameTask, description, startDate, endDate);
        project.addTask(task);

        JOptionPane.showMessageDialog(this, "Task added", "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();
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

    private LocalDate checkDate(String input, String campo) {
        try {
            return LocalDate.parse(input);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, campo + " must be in YYYY-MM-DD format.", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}
