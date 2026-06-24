package com.itj.bootcamp.taskflow;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * No implementation needed. Spring Data generates a proxy at runtime that
 * provides save / findAll / findById / delete / count and more, for free.
 *
 * This IS the repository pattern — and the bean you @Autowire is a dynamic proxy.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
}
