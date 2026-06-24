package com.itj.bootcamp.taskflow.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties the CONSUMING app sets under "taskflow.greeter.*". The starter
 * ships sensible defaults so it works with zero configuration.
 */
@ConfigurationProperties(prefix = "taskflow.greeter")
public class TaskflowGreetingProperties {

    /** Turn the greeter on/off. */
    private boolean enabled = true;

    /** Who to greet. */
    private String name = "bootcamp";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
