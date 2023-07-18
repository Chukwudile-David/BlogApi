package com.example.blogapp.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BlogExceptionHandler {

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<?> AccessException(UnauthorizedAccessException unauthorizedAccessException) {
        return ResponseEntity.ok().body("you are not authorized");
    }
}
