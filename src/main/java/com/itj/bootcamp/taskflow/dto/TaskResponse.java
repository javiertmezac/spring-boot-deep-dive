package com.itj.bootcamp.taskflow.dto;

import com.itj.bootcamp.taskflow.Task;

import java.time.Instant;
import java.util.UUID;

/**
 * The output contract. Clients depend on THIS shape, not on our internal Task
 * object — so we can refactor the domain/entity without breaking the API.
 */
public record TaskResponse(UUID id, String title, boolean completed, Instant entryDate) {

    public static TaskResponse from(Task task) {
        return new TaskResponse(task.getTaskId(), task.getTitle(), task.isCompleted(), task.getEntryDate());
    }
}
