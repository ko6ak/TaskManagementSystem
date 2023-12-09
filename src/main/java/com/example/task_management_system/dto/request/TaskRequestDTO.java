package com.example.task_management_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {
    private long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String status;
    @NotBlank
    private String priority;
    @NotNull
    @Positive
    private long authorId;
    @NotNull
    @Positive
    private long executorId;
    private List<Long> commentIds;

    public TaskRequestDTO(String title, String description, String status, String priority, long authorId, long executorId) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.authorId = authorId;
        this.executorId = executorId;
    }

    public TaskRequestDTO(long id, String title, String description, String status, String priority, long authorId, long executorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.authorId = authorId;
        this.executorId = executorId;
    }
}
