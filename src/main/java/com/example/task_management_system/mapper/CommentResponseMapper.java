package com.example.task_management_system.mapper;

import com.example.task_management_system.dto.response.CommentResponseDTO;
import com.example.task_management_system.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentResponseMapper {
    CommentResponseMapper INSTANCE = Mappers.getMapper(CommentResponseMapper.class);

    List<CommentResponseDTO> map(List<Comment> comments);
}
