package com.itj.bootcamp.taskflow;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final NotificationService notificationService;

    // In-memory store for now. Replaced by a JPA repository in branch 09.
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong ids = new AtomicLong(0);

    public TaskService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // @PostConstruct runs AFTER the bean is constructed and dependencies are
    // injected, but BEFORE the context is fully ready (and before any
    // CommandLineRunner). Good for showing bean initialization order.
    @PostConstruct
    void init() {
        System.out.println(">> TaskService bean initialized (@PostConstruct)");
    }

    public Task createTask(String title) {
        Task task = new Task(ids.incrementAndGet(), title);
        tasks.add(task);
        notificationService.send("user@taskflow.dev", "Task created: " + title);
        return task;
    }

    public List<Task> findAll() {
        return List.copyOf(tasks);
    }
}
