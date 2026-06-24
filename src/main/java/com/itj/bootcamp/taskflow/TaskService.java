package com.itj.bootcamp.taskflow;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final NotificationService notificationService;

    // Depends on the INTERFACE. Two implementations exist (Email, Sms), so the
    // container needs a tie-breaker — it injects the @Primary one (Email).
    //
    // To force SMS instead, qualify the parameter and the source of TaskService
    // still doesn't care which concrete class it gets:
    //
    //   public TaskService(@Qualifier("smsNotificationService") NotificationService n)
    public TaskService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void createTask(String title) {
        System.out.println("Creating task: " + title);
        notificationService.send("user@taskflow.dev", "Task created: " + title);
    }
}
