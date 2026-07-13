package com.itj.bootcamp.taskflow;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    // Query derivation: Spring Data parses this METHOD NAME and writes the SQL
    // for you — "where lower(title) like lower('%' || ?1 || '%')" — and the
    // Pageable adds LIMIT/OFFSET/ORDER BY. No @Query, no implementation.
    Page<Task> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
