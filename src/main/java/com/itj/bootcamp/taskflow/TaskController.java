package com.itj.bootcamp.taskflow;

import com.itj.bootcamp.taskflow.dto.CreateTaskRequest;
import com.itj.bootcamp.taskflow.dto.TaskResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // The API now speaks DTOs only. The domain Task never crosses the boundary.
    @GetMapping
    public List<TaskResponse> getAll() {
        return taskService.findAll().stream()
                .map(TaskResponse::from)
                .toList();
    }

    @PostMapping
    public TaskResponse create(@Valid @RequestBody CreateTaskRequest request) {
        Task created = taskService.createTask(request.title());
        return TaskResponse.from(created);
    }
}
