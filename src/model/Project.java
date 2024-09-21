package model;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private int idProject;
    private String nameProject;
    private String description;
    private List<Task> listTask;

    public Project(int idProject, String nameProject, String description) {
        this.idProject = idProject;
        this.nameProject = nameProject;
        this.description = description;
        this.listTask = new ArrayList<>();
    }

    public int getId() {
        return idProject;
    }

    public String getNameProject() {
        return nameProject;
    }

    public String getDescription() {
        return description;
    }

    public List<Task> getTasks() {
        return listTask;
    }

    public void addTask(Task task) {
        this.listTask.add(task);
    }

    public boolean deleteTask(int idTask) {
        return listTask.removeIf(task -> task.getId() == idTask);
    }

    public Task searchTask(int idTarefa) {
        return listTask.stream()
                .filter(task -> task.getId() == idTarefa)
                .findFirst()
                .orElse(null);
    }

    public String getFormattedTaskDetails() {
        StringBuilder sb = new StringBuilder();

        if (listTask.isEmpty()) {
            sb.append("\tNo tasks available.\n");
        } else {
            listTask.forEach(task -> {
                sb.append("\tTask ID: ").append(task.getId()).append("\n");
                sb.append("\tName: ").append(task.getName()).append("\n");
                sb.append("\tDescription: ").append(task.getDescription()).append("\n");
                sb.append("\tStatus: ").append(task.getStatus()).append("\n");
                sb.append("\tStart Date: ").append(task.getStartDate()).append("\n");
                sb.append("\tEnd Date: ").append(task.getEndDate()).append("\n");
                sb.append("\t==========================\n");
            });
        }

        return sb.toString();
    }

}
