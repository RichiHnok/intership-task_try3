package com.richi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler{
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handle(EntityNotFoundException exp){
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exp.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handle(AuthenticationException exp){
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(exp.getMessage());
    }

    // @ExceptionHandler({ AccessDeniedException.class })
    // public ResponseEntity<Object> handleAccessDeniedException(
    //   Exception ex, WebRequest request) {
    //     return ResponseEntity
    //         .status(HttpStatus.FORBIDDEN)
    //         .body(ex.getMessage());
    // }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handle(AccessDeniedException exp){
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(exp.getMessage());
    }
}
