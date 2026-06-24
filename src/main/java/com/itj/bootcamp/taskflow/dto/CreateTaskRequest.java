package com.itj.bootcamp.taskflow.dto;

/**
 * The input contract for creating a task. A record = immutable, concise, and
 * exactly the fields a client may send. Replaces the loose Map from branch 06.
 */
public record CreateTaskRequest(String title) {
}
