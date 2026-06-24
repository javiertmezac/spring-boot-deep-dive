package com.itj.bootcamp.taskflow;

import org.springframework.stereotype.Component;

/**
 * Now a Spring-managed bean. The container creates exactly one instance
 * (singleton scope) and hands it to whoever needs it.
 */
@Component
public class EmailNotificationService {

    public void send(String to, String message) {
        System.out.println("[EMAIL] to=" + to + " : " + message);
    }
}
