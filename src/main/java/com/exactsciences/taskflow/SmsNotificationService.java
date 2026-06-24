package com.exactsciences.taskflow;

import org.springframework.stereotype.Component;

/**
 * A second implementation of the same interface. Bean name defaults to
 * "smsNotificationService" — that name is what @Qualifier targets.
 */
@Component
public class SmsNotificationService implements NotificationService {

    @Override
    public void send(String to, String message) {
        System.out.println("[SMS] to=" + to + " : " + message);
    }
}
