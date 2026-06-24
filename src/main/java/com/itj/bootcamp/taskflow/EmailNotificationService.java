package com.itj.bootcamp.taskflow;

/**
 * Plain Java. No Spring annotations.
 * TaskService creates this directly with `new` — that is the problem.
 */
public class EmailNotificationService {

    public void send(String to, String message) {
        System.out.println("[EMAIL] to=" + to + " : " + message);
    }
}
