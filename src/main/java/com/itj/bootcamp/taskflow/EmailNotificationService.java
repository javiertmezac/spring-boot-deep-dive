package com.itj.bootcamp.taskflow;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Only a bean when the "dev" profile is active. Profiles now decide which
 * NotificationService implementation the container wires — no code change in
 * TaskService, just a different active profile.
 */
@Profile("dev")
@Component
public class EmailNotificationService implements NotificationService {

    @Override
    public void send(String to, String message) {
        System.out.println("[EMAIL] to=" + to + " : " + message);
    }
}
