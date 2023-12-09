package com.example.task_management_system.mapper;

import com.example.task_management_system.dto.response.UserResponseDTO;
import com.example.task_management_system.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserResponseMapper {
    UserResponseMapper INSTANCE = Mappers.getMapper(UserResponseMapper.class);

    UserResponseDTO userToUserResponseDTO(User user);
}
