package com.itj.bootcamp.taskflow;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskflowApplication {

    public static void main(String[] args) {
        // run() returns the ApplicationContext — the container that now owns
        // and wires every bean. We never call `new TaskService()` again.
        ConfigurableApplicationContext context =
                SpringApplication.run(TaskflowApplication.class, args);

        System.out.println("Beans managed by the context: "
                + context.getBeanDefinitionCount());
    }

    // A @Bean defined in a @Configuration-style class. The container calls this
    // method, sees it needs a TaskService, finds that bean, and injects it.
    @Bean
    CommandLineRunner runner(TaskService taskService) {
        return args -> {
            taskService.createTask("Write bootcamp materials");
            taskService.createTask("Review pull requests");
        };
    }
}
