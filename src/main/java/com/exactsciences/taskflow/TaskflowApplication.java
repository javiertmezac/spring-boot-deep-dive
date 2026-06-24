package com.exactsciences.taskflow;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskflowApplication.class, args);
    }

    // CommandLineRunner.run() executes once, AFTER the context is fully started.
    // Classic use: seed data, warm caches, print startup diagnostics.
    @Bean
    CommandLineRunner seedTasks(TaskService taskService) {
        return args -> {
            System.out.println(">> CommandLineRunner: seeding tasks");
            taskService.createTask("Write bootcamp materials");
            taskService.createTask("Review pull requests");
            taskService.createTask("Prepare Day 3 demo");

            System.out.println(">> Current tasks:");
            taskService.findAll().forEach(task -> System.out.println("   " + task));
        };
    }
}
