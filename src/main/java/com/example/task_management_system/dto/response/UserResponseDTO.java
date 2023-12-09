package com.example.task_management_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserResponseDTO {
    private long id;
    private String name;
    private String email;
}
