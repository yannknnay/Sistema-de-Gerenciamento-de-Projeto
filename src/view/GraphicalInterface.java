package view;

import javax.swing.*;
import java.awt.*;

import controller.ManagerProject;
import view.dialogs.*;

public class GraphicalInterface extends JFrame {
    private final ManagerProject manager;

    static {
        UIManager.put("Label.font", new Font("Tahoma", Font.BOLD, 12));
        UIManager.put("Button.font", new Font("Tahoma", Font.PLAIN, 12));
    }

    public GraphicalInterface(ManagerProject manager) {
        this.manager = manager;
        setTitle("Project Manager");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        creatInterface();
    }

    private void creatInterface() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 1, 10, 10));

        JLabel inicialText = new JLabel("Select the desired option:");
        inicialText.setHorizontalAlignment(SwingConstants.CENTER);

        JButton btnAddProject = new JButton("Add Project");
        JButton btnDeleteProject = new JButton("Delete Project");
        JButton btnAddTask = new JButton("Add Task to a Project");
        JButton btnDeleteTask = new JButton("Delete Task to a Project");
        JButton btnUpdate = new JButton("Update Status of a Task");
        JButton btnSearchProject = new JButton("Search Project by ID");
        JButton btnListProject = new JButton("List Projects");
        JButton btnExit = new JButton("Exit");


        btnAddProject.addActionListener(e -> new AddProjectDialog(this, manager).setVisible(true));
        btnDeleteProject.addActionListener(e -> new DeleteProjectDialog(this, manager).setVisible(true));
        btnAddTask.addActionListener(e -> new AddTaskDialog(this,manager).setVisible(true));
        btnDeleteTask.addActionListener(e -> new DeleteTaskDialog(this, manager).setVisible(true));
        btnUpdate.addActionListener(e -> new UpdateStatusDialog(this, manager).setVisible(true));
        btnSearchProject.addActionListener(e -> new SearchProjectDialog(this, manager).setVisible(true));
        btnListProject.addActionListener(e -> new ListProjectsDialog(this, manager).setVisible(true));
        btnExit.addActionListener(e -> System.exit(0));

        panel.add(inicialText);
        panel.add(btnAddProject);
        panel.add(btnDeleteProject);
        panel.add(btnAddTask);
        panel.add(btnDeleteTask);
        panel.add(btnUpdate);
        panel.add(btnSearchProject);
        panel.add(btnListProject);
        panel.add(btnExit);

        add(panel);
    }
}
