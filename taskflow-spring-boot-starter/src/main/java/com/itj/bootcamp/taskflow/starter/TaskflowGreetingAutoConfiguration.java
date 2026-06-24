package com.itj.bootcamp.taskflow.starter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * The heart of a starter. Spring Boot finds this class via the
 * AutoConfiguration.imports file and applies it during startup.
 *
 * The conditions decide WHETHER the beans are created — this is exactly what you
 * saw in /actuator/conditions on Day 2.
 */
@AutoConfiguration
@EnableConfigurationProperties(TaskflowGreetingProperties.class)
@ConditionalOnProperty(
        prefix = "taskflow.greeter",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true)
public class TaskflowGreetingAutoConfiguration {

    /**
     * @ConditionalOnMissingBean = "back off if the app already defined its own".
     * This is why starters are overridable: the user's bean always wins.
     */
    @Bean
    @ConditionalOnMissingBean
    public TaskflowGreetingService taskflowGreetingService(TaskflowGreetingProperties properties) {
        return new TaskflowGreetingService(properties.getName());
    }
}
