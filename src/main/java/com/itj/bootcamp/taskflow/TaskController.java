package com.itj.bootcamp.taskflow;

import com.itj.bootcamp.taskflow.ai.TaskAiService;
import com.itj.bootcamp.taskflow.dto.CreateTaskRequest;
import com.itj.bootcamp.taskflow.dto.TaskResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskAiService taskAiService;

    public TaskController(TaskService taskService, TaskAiService taskAiService) {
        this.taskService = taskService;
        this.taskAiService = taskAiService;
    }

    // Spring resolves Pageable from ?page=&size=&sort= automatically.
    // Optional ?q= filters by title.
    @GetMapping
    public Page<TaskResponse> getAll(@RequestParam(required = false) String q,
                                     Pageable pageable) {
        return taskService.search(q, pageable).map(TaskResponse::from);
    }

    @GetMapping("/{id}")
    public TaskResponse getById(@PathVariable UUID id) {
        return TaskResponse.from(taskService.getById(id));
    }

    @PostMapping
    public TaskResponse create(@Valid @RequestBody CreateTaskRequest request) {
        return TaskResponse.from(taskService.createTask(request.title()));
    }

    @PostMapping("/{id}/complete")
    public TaskResponse complete(@PathVariable UUID id) {
        return TaskResponse.from(taskService.completeTask(id));
    }

    // Ask the LLM to suggest a priority order for all current tasks.
    @PostMapping("/prioritize")
    public String prioritize() {
        return taskAiService.prioritize(taskService.findAll());
    }
}
