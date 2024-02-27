//package com.example.task_management_system;
//
//import com.example.task_management_system.controller.TaskController;
//import com.example.task_management_system.service.TaskService;
//import com.example.task_management_system.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicReference;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//public class TaskControllerTest {
//    @Autowired
//    UserService userService;
//    @Autowired
//    TaskService taskService;
//
//    MockMvc mvc;
//
//    ObjectMapper mapper = new ObjectMapper();
//
//    @BeforeEach
//    void setUp() {
//        mvc = MockMvcBuilders.standaloneSetup(new TaskController(taskService, userService)).build();
//    }
//
//    @Test
//    void getOk() throws Exception {
//        AtomicReference<String> response = new AtomicReference<>();
//
//        mvc.perform(get("/task/get").param("id", "1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(result -> response.set(result.getResponse().getContentAsString(StandardCharsets.UTF_8)));
//        String exp = mapper.writeValueAsString(TestData.taskWithUserInfoResponseDTO_1);
//        assertThat(response.get()).isEqualTo(exp);
//    }
//
//    @Test
//    void getNotFound() throws Exception {
//        mvc.perform(get("/task/get").param("id", "10"))
//                .andExpect(status().isNotFound())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.message").value(TaskController.TASK_NOT_FOUND));
//    }
//
//    @Test
//    void getAll() throws Exception {
//        AtomicReference<String> response = new AtomicReference<>();
//
//        mvc.perform(get("/task/all").param("page", "0").param("size", "20"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(result -> response.set(result.getResponse().getContentAsString(StandardCharsets.UTF_8)));
//
//        String[] temp = response.get().split("pageable");
//        String result = temp[0].substring(11, temp[0].length() - 2);
//        String exp = mapper.writeValueAsString(TestData.taskWithUserInfoResponseDTOList);
//        assertThat(result).isEqualTo(exp);
//    }
//
//    @Test
//    void saveCreate() throws Exception {
//        AtomicReference<String> response = new AtomicReference<>();
//
//        UsernamePasswordAuthenticationToken authToken =
//                new UsernamePasswordAuthenticationToken(TestData.ivanWithId,null, TestData.ivanWithId.getRoles());
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//
//        mvc.perform(post("/task/save").content(mapper.writeValueAsString(TestData.taskRequestDTO_1))
//                        .characterEncoding(StandardCharsets.UTF_8)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(result -> response.set(result.getResponse().getContentAsString(StandardCharsets.UTF_8)));
//
//        String exp = mapper.writeValueAsString(TestData.taskWithUserInfoResponseDTO_4);
//        assertThat(response.get()).isEqualTo(exp);
//    }
//
//    @Test
//    void saveUpdate() throws Exception {
//        AtomicReference<String> response = new AtomicReference<>();
//
//        UsernamePasswordAuthenticationToken authToken =
//                new UsernamePasswordAuthenticationToken(TestData.ivanWithId,null, TestData.ivanWithId.getRoles());
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//
//        mvc.perform(post("/task/save").content(mapper.writeValueAsString(TestData.taskRequestDTO_2))
//                        .characterEncoding(StandardCharsets.UTF_8)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.message").value(TaskController.TASK_UPDATED));
//
//        mvc.perform(get("/task/get").param("id", "1"))
//                .andDo(result -> response.set(result.getResponse().getContentAsString(StandardCharsets.UTF_8)));
//        String exp = mapper.writeValueAsString(TestData.taskWithUserInfoResponseDTO_5);
//        assertThat(response.get()).isEqualTo(exp);
//    }
//
//    @Test
//    void saveNotHavePermit() throws Exception {
//        AtomicReference<String> response = new AtomicReference<>();
//
//        UsernamePasswordAuthenticationToken authToken =
//                new UsernamePasswordAuthenticationToken(TestData.ivanWithId,null, TestData.ivanWithId.getRoles());
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//
//        mvc.perform(post("/task/save").content(mapper.writeValueAsString(TestData.taskRequestDTO_3))
//                        .characterEncoding(StandardCharsets.UTF_8)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.message").value(TaskController.NOT_HAVE_PERMIT));
//
//        mvc.perform(get("/task/get").param("id", "3"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(result -> response.set(result.getResponse().getContentAsString(StandardCharsets.UTF_8)));
//        String exp = mapper.writeValueAsString(TestData.taskWithUserInfoResponseDTO_3);
//        assertThat(response.get()).isEqualTo(exp);
//    }
//
//    @Test
//    void deleteOk() throws Exception {
//        UsernamePasswordAuthenticationToken authToken =
//                new UsernamePasswordAuthenticationToken(TestData.ivanWithId,null, TestData.ivanWithId.getRoles());
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//
//        taskService.create(TestData.task5);
//
//        mvc.perform(delete("/task/delete").param("id", "4"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.message").value(TaskController.DELETED));
//    }
//
//    @Test
//    void deleteNotHavePermit() throws Exception {
//        UsernamePasswordAuthenticationToken authToken =
//                new UsernamePasswordAuthenticationToken(TestData.ivanWithId,null, TestData.ivanWithId.getRoles());
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//
//        mvc.perform(delete("/task/delete").param("id", "3"))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.message").value(TaskController.NOT_HAVE_PERMIT));
//    }
//
//    @Test
//    void getTasksByAuthor() throws Exception {
//        AtomicReference<String> response = new AtomicReference<>();
//
//        mvc.perform(get("/task/get-by-author").param("page", "0").param("size", "20").param("id", "3"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(result -> response.set(result.getResponse().getContentAsString(StandardCharsets.UTF_8)));
//
//        String[] temp = response.get().split("pageable");
//        String result = temp[0].substring(11, temp[0].length() - 2);
//        String exp = mapper.writeValueAsString(List.of(TestData.taskWithUserInfoResponseDTO_3));
//        assertThat(result).isEqualTo(exp);
//    }
//
//    @Test
//    void getTasksByAuthorNotFound() throws Exception {
//        mvc.perform(get("/task/get-by-author").param("page", "0").param("size", "20").param("id", "8"))
//                .andExpect(status().isNotFound())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.message").value(TaskController.USER_NOT_FOUND));
//    }
//
//    @Test
//    void getTasksByExecutor() throws Exception {
//        AtomicReference<String> response = new AtomicReference<>();
//
//        mvc.perform(get("/task/get-by-executor").param("page", "0").param("size", "20").param("id", "2"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(result -> response.set(result.getResponse().getContentAsString(StandardCharsets.UTF_8)));
//
//        String[] temp = response.get().split("pageable");
//        String result = temp[0].substring(11, temp[0].length() - 2);
//        String exp = mapper.writeValueAsString(List.of(TestData.taskWithUserInfoResponseDTO_1));
//        assertThat(result).isEqualTo(exp);
//    }
//
//    @Test
//    void getTasksByExecutorNotFound() throws Exception {
//        mvc.perform(get("/task/get-by-executor").param("page", "0").param("size", "20").param("id", "8"))
//                .andExpect(status().isNotFound())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.message").value(TaskController.USER_NOT_FOUND));
//    }
//}
