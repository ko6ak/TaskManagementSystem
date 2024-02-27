//package com.example.task_management_system;
//
//import com.example.task_management_system.controller.CommentController;
//import com.example.task_management_system.mapper.TaskResponseMapper;
//import com.example.task_management_system.service.CommentService;
//import com.example.task_management_system.service.TaskService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.nio.charset.StandardCharsets;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//public class CommentControllerTest {
//    @Autowired
//    TaskService taskService;
//    @Autowired
//    CommentService commentService;
//
//    MockMvc mvc;
//
//    ObjectMapper mapper = new ObjectMapper();
//
//    @BeforeEach
//    void setUp() {
//        mvc = MockMvcBuilders.standaloneSetup(new CommentController(commentService, taskService)).build();
//    }
//
//    @Test
//    void add() throws Exception {
//        UsernamePasswordAuthenticationToken authToken =
//                new UsernamePasswordAuthenticationToken(TestData.ivanWithId,null, TestData.ivanWithId.getRoles());
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//
//        mvc.perform(post("/comment/add").content(mapper.writeValueAsString(TestData.commentRequestDTO))
//                        .characterEncoding(StandardCharsets.UTF_8)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.message").value(CommentController.DONE));
//
//        String exp = mapper.writeValueAsString(TestData.taskWithUserInfoResponseDTO_6);
//        String act = mapper.writeValueAsString(TaskResponseMapper.INSTANCE.taskToTaskResponseDTO(taskService.get(2)));
//        assertThat(act).isEqualTo(exp);
//    }
//}
