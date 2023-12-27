package com.example.task_management_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskWithUserInfoResponseDTO {
    private long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private UserResponseDTO author;
    private UserResponseDTO executor;
}
