package com.itj.bootcamp.taskflow;

import com.itj.bootcamp.taskflow.exception.TaskNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public java.util.List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Page<Task> search(String query, Pageable pageable) {
        if (query == null || query.isBlank()) {
            return taskRepository.findAll(pageable);
        }
        return taskRepository.findByTitleContainingIgnoreCase(query, pageable);
    }

    public Task getById(Long id) {
        // A missing id throws TaskNotFoundException, which GlobalExceptionHandler
        // maps to a clean HTTP 404. The service stays HTTP-agnostic.
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
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
