package com.exactsciences.taskflow;

/**
 * Branch 01 — no Spring yet. We wire and run everything by hand in main().
 * Notice: nothing manages object lifecycles for us. WE do.
 */
public class TaskflowApplication {

    public static void main(String[] args) {
        TaskService taskService = new TaskService();
        taskService.createTask("Write bootcamp materials");
        taskService.createTask("Review pull requests");
    }
}
