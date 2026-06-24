package com.itj.bootcamp.taskflow;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final NotificationService notificationService;
    private final TaskflowProperties properties;

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong ids = new AtomicLong(0);

    public TaskService(NotificationService notificationService, TaskflowProperties properties) {
        this.notificationService = notificationService;
        this.properties = properties;
    }

    @PostConstruct
    void init() {
        System.out.println(">> TaskService bean initialized (@PostConstruct)");
    }

    public Task createTask(String title) {
        Task task = new Task(ids.incrementAndGet(), title);
        tasks.add(task);
        // Recipient now comes from configuration, not a hard-coded string.
        notificationService.send(properties.getRecipient(), "Task created: " + title);
        return task;
    }

    public List<Task> findAll() {
        return List.copyOf(tasks);
    }
}
