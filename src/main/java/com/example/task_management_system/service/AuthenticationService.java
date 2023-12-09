package com.example.task_management_system.service;

import com.example.task_management_system.dto.request.RegisterRequestDTO;
import com.example.task_management_system.dto.request.LoginRequestDTO;
import com.example.task_management_system.dto.response.AuthenticationResponseDTO;
import com.example.task_management_system.entity.Role;
import com.example.task_management_system.entity.User;
import com.example.task_management_system.exception.EmailNotFoundException;
import com.example.task_management_system.jwt.JwtService;
import com.example.task_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public User register(RegisterRequestDTO request) {
        User user = new User(request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()), Collections.singleton(Role.USER));
        return userRepository.save(user);
    }

    public AuthenticationResponseDTO authenticate(LoginRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new EmailNotFoundException("User with this email not found."));
        String token = jwtService.generateToken(user);
        user.setToken(token);
        userRepository.save(user);

        return new AuthenticationResponseDTO(token);
    }
}
