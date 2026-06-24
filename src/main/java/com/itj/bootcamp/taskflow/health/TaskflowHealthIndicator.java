package com.itj.bootcamp.taskflow.health;

import com.itj.bootcamp.taskflow.TaskRepository;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Implementing HealthIndicator and registering it as a bean is all it takes —
 * Actuator auto-detects it and folds it into /actuator/health. The component
 * name is the bean name minus the "HealthIndicator" suffix → "taskflow".
 *
 * This is the consumer side of the same auto-configuration story: Actuator's
 * HealthEndpoint auto-configuration discovers every HealthIndicator bean.
 */
@Component
public class TaskflowHealthIndicator implements HealthIndicator {

    private final TaskRepository taskRepository;

    public TaskflowHealthIndicator(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Health health() {
        try {
            long count = taskRepository.count();
            return Health.up()
                    .withDetail("store", "H2 (in-memory)")
                    .withDetail("taskCount", count)
                    .build();
        } catch (Exception ex) {
            // A failing data store makes the whole app report DOWN.
            return Health.down(ex).build();
        }
    }
}
