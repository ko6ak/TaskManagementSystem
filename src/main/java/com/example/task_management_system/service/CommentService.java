package com.example.task_management_system.service;

import com.example.task_management_system.entity.Comment;
import com.example.task_management_system.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void create(Comment comment) {
        commentRepository.save(comment);
    }
}
