package com.itj.bootcamp.taskflow.dto;

import com.itj.bootcamp.taskflow.Task;

/**
 * The output contract. Clients depend on THIS shape, not on our internal Task
 * object — so we can refactor the domain/entity without breaking the API.
 */
public record TaskResponse(Long id, String title, boolean completed) {

    public static TaskResponse from(Task task) {
        return new TaskResponse(task.getId(), task.getTitle(), task.isCompleted());
    }
}
