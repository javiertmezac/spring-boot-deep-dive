package com.itj.bootcamp.taskflow;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final NotificationService notificationService;
    private final TaskflowProperties properties;

    public TaskService(TaskRepository taskRepository,
                       NotificationService notificationService,
                       TaskflowProperties properties) {
        this.taskRepository = taskRepository;
        this.notificationService = notificationService;
        this.properties = properties;
    }

    public Task createTask(String title) {
        // In-memory list and AtomicLong are gone. The DB owns the data and the id.
        Task saved = taskRepository.save(new Task(title));
        notificationService.send(properties.getRecipient(), "Task created: " + title);
        return saved;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }
}
