package com.itj.bootcamp.taskflow;

import com.itj.bootcamp.taskflow.dto.CreateTaskRequest;
import com.itj.bootcamp.taskflow.dto.TaskResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping
    public List<TaskResponse> getAll() {
        return taskService.findAll().stream().map(TaskResponse::from).toList();
    }

    @GetMapping("/{id}")
    public TaskResponse getById(@PathVariable Long id) {
        return TaskResponse.from(taskService.getById(id));
    }

    @PostMapping
    public TaskResponse create(@Valid @RequestBody CreateTaskRequest request) {
        return TaskResponse.from(taskService.createTask(request.title()));
    }

    @PostMapping("/{id}/complete")
    public TaskResponse complete(@PathVariable Long id) {
        return TaskResponse.from(taskService.completeTask(id));
    }
}
