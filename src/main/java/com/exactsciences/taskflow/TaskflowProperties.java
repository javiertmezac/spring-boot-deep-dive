package com.exactsciences.taskflow;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Type-safe binding of all "taskflow.*" properties. Beats scattering
 * @Value("${...}") everywhere: one place, validated, auto-completed by the IDE
 * (thanks to spring-boot-configuration-processor).
 *
 * Registered via @EnableConfigurationProperties on TaskflowApplication.
 */
@ConfigurationProperties(prefix = "taskflow")
public class TaskflowProperties {

    /** Default recipient for task notifications. */
    private String recipient = "user@taskflow.dev";

    /** Startup greeting — overridden per profile to prove which one is active. */
    private String greeting = "Hello from the base config";

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
