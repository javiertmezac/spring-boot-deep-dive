package com.itj.bootcamp.taskflow;

public class TaskService {

    // Tight coupling: TaskService decides WHICH notification implementation
    // to use, and constructs it itself. To switch to SMS you must edit THIS file.
    private final EmailNotificationService notificationService =
            new EmailNotificationService();

    public void createTask(String title) {
        System.out.println("Creating task: " + title);
        notificationService.send("user@taskflow.dev", "Task created: " + title);
    }
}
