package com.example.task_management_system.controller;

import com.example.task_management_system.dto.request.LoginRequestDTO;
import com.example.task_management_system.dto.request.RegisterRequestDTO;
import com.example.task_management_system.dto.response.AuthenticationResponseDTO;
import com.example.task_management_system.dto.response.MessageResponseDTO;
import com.example.task_management_system.dto.response.UserResponseDTO;
import com.example.task_management_system.mapper.UserResponseMapper;
import com.example.task_management_system.service.AuthenticationService;
import com.example.task_management_system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication Controller", description = "Контроллер для регистрации и входа.")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    public static final String EMAIL_EXIST = "User with this email already exist.";

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Operation(summary = "Регистрация пользователя.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = MessageResponseDTO.class), mediaType = "application/json") }) })
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO request) {
        if (userService.isExist(request.getEmail()))
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(new MessageResponseDTO(EMAIL_EXIST));

        UserResponseDTO userResponseDTO = UserResponseMapper.INSTANCE.userToUserResponseDTO(authenticationService.register(request));
        return ResponseEntity.ok(userResponseDTO);
    }

    @Operation(summary = "Вход пользователя.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = AuthenticationResponseDTO.class), mediaType = "application/json") }) })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
