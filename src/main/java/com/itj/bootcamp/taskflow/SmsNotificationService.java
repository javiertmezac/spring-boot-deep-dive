package com.itj.bootcamp.taskflow;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Only a bean when the "prod" profile is active.
 */
@Profile("prod")
@Component
public class SmsNotificationService implements NotificationService {

    @Override
    public void send(String to, String message) {
        System.out.println("[SMS] to=" + to + " : " + message);
    }
}
