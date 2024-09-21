package view.dialogs;

import controller.ManagerProject;

import javax.swing.*;
import java.awt.*;

public class ListProjectsDialog extends JDialog {
    private final ManagerProject manager;
    private JTextArea textArea;

    public ListProjectsDialog(JFrame parent, ManagerProject manager) {
        super(parent, "List Projects", true);
        this.manager = manager;
        setSize(600, 400);
        setLocationRelativeTo(parent);
        createInterface();
    }

    private void createInterface() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> dispose());

        add(scrollPane, BorderLayout.CENTER);
        add(btnClose, BorderLayout.SOUTH);

        listAllProjects();
    }

    private void listAllProjects() {
        String allProjectsDetails = manager.getFormattedAllProjectsDetails();

        textArea.setText(allProjectsDetails);
    }
}
