package com.exactsciences.taskflow;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @Primary: when more than one NotificationService bean exists and a caller
 * asks for the interface without qualifying, the container injects THIS one.
 */
@Primary
@Component
public class EmailNotificationService implements NotificationService {

    @Override
    public void send(String to, String message) {
        System.out.println("[EMAIL] to=" + to + " : " + message);
    }
}
