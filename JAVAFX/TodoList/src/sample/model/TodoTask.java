package sample.model;

import java.time.LocalDate;

public class TodoTask {

    private String nameOfTodoTask;
    private String details;
    private LocalDate deadline;

    public TodoTask(String nameOfTodoTask, String details, LocalDate deadline) {
        this.nameOfTodoTask = nameOfTodoTask;
        this.details = details;
        this.deadline = deadline;
    }

    public String getNameOfTodoTask() {
        return nameOfTodoTask;
    }

    public void setNameOfTodoTask(String nameOfTodoTask) {
        this.nameOfTodoTask = nameOfTodoTask;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return nameOfTodoTask;
    }
}
