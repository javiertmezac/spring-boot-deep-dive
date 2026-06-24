package com.itj.bootcamp.taskflow.internals;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Demonstrates two internals:
 *  - the ApplicationContext IS a bean you can inject and query;
 *  - the application lifecycle publishes events you can listen for.
 */
@Component
public class StartupReporter {

    private final ApplicationContext context;

    public StartupReporter(ApplicationContext context) {
        this.context = context;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        System.out.println(">> Context ready. Total bean definitions: "
                + context.getBeanDefinitionCount());
        System.out.println(">> Explore the container live:");
        System.out.println("     /actuator/beans       (every bean + dependencies)");
        System.out.println("     /actuator/conditions  (WHY each auto-config matched/skipped)");
        System.out.println("     /actuator/health      (health checks)");
    }
}
