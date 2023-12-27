//package com.example.task_management_system;
//
//import com.example.task_management_system.controller.AuthenticationController;
//import com.example.task_management_system.service.AuthenticationService;
//import com.example.task_management_system.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.nio.charset.StandardCharsets;
//import java.util.concurrent.atomic.AtomicReference;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//public class AuthenticationControllerTest {
//
//    @Autowired
//    AuthenticationService authenticationService;
//    @Autowired
//    UserService userService;
//
//    MockMvc mvc;
//
//    ObjectMapper mapper = new ObjectMapper();
//
//    @BeforeEach
//    void setUp() {
//        mvc = MockMvcBuilders.standaloneSetup(new AuthenticationController(authenticationService, userService)).build();
//    }
//
//    @Test
//    void registerOk() throws Exception {
//        AtomicReference<String> response = new AtomicReference<>();
//
//        mvc.perform(post("/auth/register").content(mapper.writeValueAsString(TestData.registerRequestDTO_1))
//                        .characterEncoding(StandardCharsets.UTF_8)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(result -> response.set(result.getResponse().getContentAsString(StandardCharsets.UTF_8)));
//
//        String exp = mapper.writeValueAsString(TestData.userResponseDTO);
//        assertThat(response.get()).isEqualTo(exp);
//    }
//
//    @Test
//    void registerExistEmail() throws Exception {
//        mvc.perform(post("/auth/register").content(mapper.writeValueAsString(TestData.registerRequestDTO_2))
//                        .characterEncoding(StandardCharsets.UTF_8)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.message").value(AuthenticationController.EMAIL_EXIST));
//    }
//
//    @Test
//    void loginOk() throws Exception {
//        mvc.perform(post("/auth/login").content(mapper.writeValueAsString(TestData.loginRequestDTO))
//                        .characterEncoding(StandardCharsets.UTF_8)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.token").isNotEmpty());
//    }
//}
