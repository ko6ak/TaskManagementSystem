package com.example.task_management_system.controller;

import com.example.task_management_system.dto.request.CommentRequestDTO;
import com.example.task_management_system.dto.response.MessageResponseDTO;
import com.example.task_management_system.entity.Comment;
import com.example.task_management_system.entity.Task;
import com.example.task_management_system.entity.User;
import com.example.task_management_system.service.CommentService;
import com.example.task_management_system.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Comment Controller", description = "Контроллер для работы с комментариями")
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    public static final String DONE = "Done!";

    private final CommentService commentService;
    private final TaskService taskService;

    @Operation(
            summary = "Добавление комментария для задания.",
            description = "Доступно всем залогиненным пользователям.")
    @PostMapping("/add")
    public ResponseEntity<MessageResponseDTO> add(@RequestBody CommentRequestDTO commentRequestDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = taskService.getRef(commentRequestDTO.getTaskId());
        commentService.create(new Comment(commentRequestDTO.getMessage(), user, task));
        return ResponseEntity.ok(new MessageResponseDTO(DONE));
    }
}
