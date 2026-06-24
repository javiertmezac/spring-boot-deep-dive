package com.itj.bootcamp.taskflow;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final EmailNotificationService notificationService;

    // Constructor injection: TaskService no longer CREATES its dependency,
    // it DECLARES what it needs. The container provides it. That is the
    // "inversion" in Inversion of Control.
    public TaskService(EmailNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void createTask(String title) {
        System.out.println("Creating task: " + title);
        notificationService.send("user@taskflow.dev", "Task created: " + title);
    }
}
