package com.example.task_management_system.repository;

import com.example.task_management_system.entity.Status;
import com.example.task_management_system.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @EntityGraph(attributePaths = {"author", "executor"})
    Page<Task> findAllByAuthorId(long id, Pageable pageable);

    @EntityGraph(attributePaths = {"author", "executor"})
    Page<Task> findAllByExecutorId(long id, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Task t set t.status=:status where t.id=:id")
    void saveById(long id, Status status);

    @EntityGraph(attributePaths = {"author", "executor"})
    Page<Task> findAll(Pageable pageable);

    @Query("select t from Task t")
    Page<Task> findAllWithoutUserInfo(Pageable pageable);

    @EntityGraph(attributePaths = {"author", "executor"})
    Optional<Task> findById(long id);
}
