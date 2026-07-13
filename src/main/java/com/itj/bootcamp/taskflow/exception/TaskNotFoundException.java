package com.itj.bootcamp.taskflow.exception;

import java.util.UUID;

/**
 * A domain-meaningful exception. The handler maps it to HTTP 404 — the service
 * stays HTTP-agnostic and just says "this doesn't exist".
 */
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(UUID id) {
        super("Task not found: " + id);
    }
}
