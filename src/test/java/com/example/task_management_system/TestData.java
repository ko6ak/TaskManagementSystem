//package com.example.task_management_system;
//
//import com.example.task_management_system.dto.request.CommentRequestDTO;
//import com.example.task_management_system.dto.request.LoginRequestDTO;
//import com.example.task_management_system.dto.request.RegisterRequestDTO;
//import com.example.task_management_system.dto.request.TaskRequestDTO;
//import com.example.task_management_system.dto.response.CommentResponseDTO;
//import com.example.task_management_system.dto.response.TaskWithUserInfoResponseDTO;
//import com.example.task_management_system.dto.response.UserResponseDTO;
//import com.example.task_management_system.entity.*;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class TestData {
//    private static final ObjectMapper mapper = new ObjectMapper();
//
//    public static User ivanWithId = new User(1, "Ivan", "ivan@ya.ru", "$2a$10$4p8Ppk362xiOcJH5dnuFCuDt6oStyqip4F0vWjgwD582G.bq.Ul9q",
//            Collections.singleton(Role.USER));
//    public static User lenaWithId = new User(3, "Lena", "lena@ya.ru", "$2a$10$4p8Ppk362xiOcJH5dnuFCuDt6oStyqip4F0vWjgwD582G.bq.Ul9q",
//            Collections.singleton(Role.USER));
//
//    public static Task task2 = new Task("Task#2", "second task", Status.IN_DELAY, Priority.MIDDLE, ivanWithId, lenaWithId);
//    public static Task task5 = new Task("Task#5", "fourth task", Status.FINISHED, Priority.HIGH, ivanWithId, ivanWithId);
//
//    public static UserResponseDTO userResponseDTO_1 = new UserResponseDTO(1, "Ivan", "ivan@ya.ru");
//    public static UserResponseDTO userResponseDTO_2 = new UserResponseDTO(2, "Serg", "serg@ya.ru");
//    public static UserResponseDTO userResponseDTO_3 = new UserResponseDTO(3, "Lena", "lena@ya.ru");
//
//    public static CommentResponseDTO commentResponseDTO_1 = new CommentResponseDTO(1, "Good job, team!", userResponseDTO_1);
//
//    public static CommentRequestDTO commentRequestDTO = new CommentRequestDTO("Any Text.", 1, 2);
//    public static CommentResponseDTO commentResponseDTO_2 = new CommentResponseDTO(2, "Any Text.", userResponseDTO_1);
//
//    public static TaskWithUserInfoResponseDTO taskWithUserInfoResponseDTO_1 = new TaskWithUserInfoResponseDTO(1, "Task#1", "first task",
//            "IN_PROGRESS", "LOW", userResponseDTO_1, userResponseDTO_2, new ArrayList<>());
//    public static TaskWithUserInfoResponseDTO taskWithUserInfoResponseDTO_2 = new TaskWithUserInfoResponseDTO(2, "Task#2", "second task",
//            "IN_DELAY", "MIDDLE", userResponseDTO_1, userResponseDTO_3, new ArrayList<>());
//    public static TaskWithUserInfoResponseDTO taskWithUserInfoResponseDTO_3 = new TaskWithUserInfoResponseDTO(3, "Task#3", "third task",
//            "FINISHED", "HIGH", userResponseDTO_3, userResponseDTO_3, List.of(commentResponseDTO_1));
//
//    public static TaskRequestDTO taskRequestDTO_1 = new TaskRequestDTO("Task#4", "fourth task",
//            "IN_DELAY", "LOW", 1, 1);
//
//    public static TaskWithUserInfoResponseDTO taskWithUserInfoResponseDTO_4 = new TaskWithUserInfoResponseDTO(4, "Task#4", "fourth task",
//            "IN_DELAY", "LOW", userResponseDTO_1, userResponseDTO_1, new ArrayList<>());
//
//    public static TaskRequestDTO taskRequestDTO_2 = new TaskRequestDTO(1, "Task#8", "eighth task",
//            "FINISHED", "LOW", 1, 2);
//
//    public static TaskWithUserInfoResponseDTO taskWithUserInfoResponseDTO_5 = new TaskWithUserInfoResponseDTO(1, "Task#8", "eighth task",
//            "FINISHED", "LOW", userResponseDTO_1, userResponseDTO_2, new ArrayList<>());
//
//    public static TaskRequestDTO taskRequestDTO_3 = new TaskRequestDTO(3, "Task#8", "eighth task",
//            "FINISHED", "LOW", 3, 2);
//
//    public static TaskWithUserInfoResponseDTO taskWithUserInfoResponseDTO_6 = new TaskWithUserInfoResponseDTO(2, "Task#2", "second task",
//            "IN_DELAY", "MIDDLE", userResponseDTO_1, userResponseDTO_3, List.of(commentResponseDTO_2));
//
//    public static RegisterRequestDTO registerRequestDTO_1 = new RegisterRequestDTO("Petr", "petr@ya.ru", "12345");
//    public static RegisterRequestDTO registerRequestDTO_2 = new RegisterRequestDTO("Petr", "ivan@ya.ru", "12345");
//
//    public static UserResponseDTO userResponseDTO = new UserResponseDTO(4, "Petr", "petr@ya.ru");
//
//    public static LoginRequestDTO loginRequestDTO = new LoginRequestDTO("ivan@ya.ru", "12345");
//
//    public static List<TaskWithUserInfoResponseDTO> taskWithUserInfoResponseDTOList = new ArrayList<>();
//
//    public static String list;
//
//    static {
//        Collections.addAll(taskWithUserInfoResponseDTOList, taskWithUserInfoResponseDTO_1, taskWithUserInfoResponseDTO_2, taskWithUserInfoResponseDTO_3);
//        try {
//            list = mapper.writeValueAsString(TestData.taskWithUserInfoResponseDTOList);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
