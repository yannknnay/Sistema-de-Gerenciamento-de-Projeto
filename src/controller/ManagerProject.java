package controller;

import java.util.HashMap;
import java.util.Map;

import model.*;

public class ManagerProject {
    private Map<Integer, Project> projects;

    public ManagerProject() {
        projects = new HashMap<Integer, Project>();
    }

    public Project getProject(int idProject) {
        return projects.get(idProject);
    }

    public void addProject(int idProject, String nameProject, String description) {
        Project project = new Project(idProject, nameProject, description);
        projects.put(idProject, project);
    }

    public boolean deleteProject(int idProject) {
        return projects.remove(idProject) != null;
    }

    public Map<Integer, Project> listProjects() {
        return projects;
    }

    public String getFormattedProjectDetails(Project project) {
        StringBuilder sb = new StringBuilder();

        sb.append("===== Project Details =====\n");
        sb.append("Project ID: ").append(project.getId()).append("\n");
        sb.append("Name: ").append(project.getNameProject()).append("\n");
        sb.append("Description: ").append(project.getDescription()).append("\n");
        sb.append("Tasks:\n");

        sb.append(project.getFormattedTaskDetails());

        return sb.toString();
    }

    public String getFormattedAllProjectsDetails() {
        StringBuilder sb = new StringBuilder();

        for (Project project : projects.values()) {
            sb.append(getFormattedProjectDetails(project));
            sb.append("\n");
        }

        return sb.toString();
    }
}
