package com.example.task_management_system.service;

import com.example.task_management_system.dto.response.TaskResponseDTO;
import com.example.task_management_system.entity.Task;
import com.example.task_management_system.mapper.TaskResponseMapper;
import com.example.task_management_system.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public void update(Task task) {
        taskRepository.save(task);
    }

    public Task get(long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void delete(long id) {
        taskRepository.deleteById(id);
    }

    public Page<TaskResponseDTO> getByAuthorId(long id, Pageable pageable) {
        Page<Task> page = taskRepository.findAllByAuthorId(id, pageable);
        return new PageImpl<>(TaskResponseMapper.INSTANCE.map(page.getContent()), pageable, page.getTotalElements());
    }

    public Page<TaskResponseDTO> getByExecutorId(long id, Pageable pageable) {
        Page<Task> page = taskRepository.findAllByExecutorId(id, pageable);
        return new PageImpl<>(TaskResponseMapper.INSTANCE.map(page.getContent()), pageable, page.getTotalElements());
    }

    public Page<TaskResponseDTO> getAll(Pageable pageable) {
        Page<Task> page = taskRepository.findAll(pageable);
        return new PageImpl<>(TaskResponseMapper.INSTANCE.map(page.getContent()), pageable, page.getTotalElements());
    }
}
