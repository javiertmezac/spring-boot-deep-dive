package com.itj.bootcamp.taskflow;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service layer owns business rules and transaction boundaries.
 * Controllers stay thin (HTTP only); repositories stay dumb (data only).
 *
 * Class-level @Transactional(readOnly = true) is a safe default; write methods
 * override it with a read/write transaction.
 */
@Service
@Transactional(readOnly = true)
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

    @Transactional
    public Task createTask(String title) {
        Task saved = taskRepository.save(new Task(title));
        notificationService.send(properties.getRecipient(), "Task created: " + title);
        return saved;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task getById(Long id) {
        // Temporary: a missing id throws NoSuchElementException -> HTTP 500.
        // Branch 12 introduces TaskNotFoundException + @ControllerAdvice -> 404.
        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task not found: " + id));
    }

    @Transactional
    public Task completeTask(Long id) {
        Task task = getById(id);
        task.setCompleted(true);
        // Inside a transaction, JPA dirty-checking flushes the change on commit;
        // the explicit save() documents intent.
        return taskRepository.save(task);
    }
}
