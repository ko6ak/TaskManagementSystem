package com.example.task_management_system.exception;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException ex) {

        Map<String, List<String>> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.merge(
                        error.getField(),
                        Collections.singletonList(error.getDefaultMessage()),
                        (list1, list2) -> {
                                List<String> result = new ArrayList<>(list1);
                                result.addAll(list2);
                                return result;}));

        return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(errors);
    }
}
