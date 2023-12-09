package com.example.task_management_system.mapper;

import com.example.task_management_system.dto.response.TaskResponseDTO;
import com.example.task_management_system.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskResponseMapper {
    TaskResponseMapper INSTANCE = Mappers.getMapper(TaskResponseMapper.class);

    List<TaskResponseDTO> map(List<Task> tasks);

    TaskResponseDTO taskToTaskResponseDTO(Task task);
}
