package com.itj.bootcamp.taskflow;

/**
 * Depend on this interface, not a concrete class. Now switching Email -> SMS
 * is a wiring decision (annotations / config), NOT a source edit to TaskService.
 */
public interface NotificationService {

    void send(String to, String message);
}
