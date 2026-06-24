package com.itj.bootcamp.taskflow.starter;

/**
 * The library bean the starter contributes. The consuming app never declares
 * this — auto-configuration provides it.
 */
public class TaskflowGreetingService {

    private final String name;

    public TaskflowGreetingService(String name) {
        this.name = name;
    }

    public String greet() {
        return "Hello, " + name + "! (this bean came from taskflow-spring-boot-starter)";
    }
}
