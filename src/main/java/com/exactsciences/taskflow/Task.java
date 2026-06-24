package com.exactsciences.taskflow;

/**
 * Plain in-memory domain object for now. Becomes a JPA entity in branch 09.
 */
public class Task {

    private final Long id;
    private final String title;
    private boolean completed;

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
        this.completed = false;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{id=" + id + ", title='" + title + "', completed=" + completed + "}";
    }
}
