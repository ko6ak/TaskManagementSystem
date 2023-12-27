package com.example.task_management_system.mapper;

import com.example.task_management_system.dto.response.TaskWithCommentsResponseDTO;
import com.example.task_management_system.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskWithCommentsResponseMapper {
    TaskWithCommentsResponseMapper INSTANCE = Mappers.getMapper(TaskWithCommentsResponseMapper.class);

    List<TaskWithCommentsResponseDTO> map(List<Task> tasks);

    TaskWithCommentsResponseDTO taskToTaskWithCommentsResponseDTO(Task task);
}
