package com.exactsciences.taskflow;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigurationProperties(TaskflowProperties.class)
public class TaskflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskflowApplication.class, args);
    }

    @Bean
    CommandLineRunner seedTasks(TaskService taskService,
                                TaskflowProperties properties,
                                Environment environment) {
        return args -> {
            System.out.println(">> Active profiles: "
                    + Arrays.toString(environment.getActiveProfiles()));
            System.out.println(">> Greeting: " + properties.getGreeting());

            taskService.createTask("Write bootcamp materials");
            taskService.createTask("Prepare Day 3 demo");

            System.out.println(">> Current tasks:");
            taskService.findAll().forEach(task -> System.out.println("   " + task));
        };
    }
}
