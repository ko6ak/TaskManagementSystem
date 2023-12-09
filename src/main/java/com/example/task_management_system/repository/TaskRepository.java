package com.example.task_management_system.repository;

import com.example.task_management_system.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAllByAuthorId(long id, Pageable pageable);
    Page<Task> findAllByExecutorId(long id, Pageable pageable);
}
