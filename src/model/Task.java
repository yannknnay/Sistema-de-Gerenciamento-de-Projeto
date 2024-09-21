package model;

import java.time.LocalDate;

public class Task {
    private int id;
    private String name;
    private String description;
    private Status status;
    private LocalDate startDate;
    private LocalDate endDate;

    public Task(int idTask, String nameTask, String description, LocalDate startDate, LocalDate endDate) {

        this.id = idTask;
        this.name = nameTask;
        this.description = description;
        this.status = Status.PENDING;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status novoStatus) {
        this.status = novoStatus;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

}
