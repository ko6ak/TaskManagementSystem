package com.example.task_management_system.mapper;

import com.example.task_management_system.dto.response.TaskResponseDTO;
import com.example.task_management_system.dto.response.TaskWithUserInfoResponseDTO;
import com.example.task_management_system.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskWithUserInfoResponseMapper {
    TaskWithUserInfoResponseMapper INSTANCE = Mappers.getMapper(TaskWithUserInfoResponseMapper.class);

    List<TaskWithUserInfoResponseDTO> map(List<Task> tasks);

    TaskWithUserInfoResponseDTO taskToTaskWithUserInfoResponseDTO(Task task);
}
