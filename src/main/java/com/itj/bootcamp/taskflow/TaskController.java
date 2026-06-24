package com.itj.bootcamp.taskflow;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController = @Controller + @ResponseBody: every return value is
 * serialized straight to the HTTP response body (JSON, via Jackson).
 *
 * Requests reach here through Spring MVC's DispatcherServlet, which matches the
 * URL+method to the right @*Mapping handler.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAll() {
        return taskService.findAll();
    }

    // For now the request body is a loose Map — stringly-typed and unvalidated.
    // Branch 07 replaces this with a proper request DTO; branch 08 validates it.
    @PostMapping
    public Task create(@RequestBody Map<String, Object> body) {
        String title = String.valueOf(body.get("title"));
        return taskService.createTask(title);
    }
}
