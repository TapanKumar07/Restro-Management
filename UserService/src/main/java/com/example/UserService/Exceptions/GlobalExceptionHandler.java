package com.example.UserService.Exceptions;

import com.example.UserService.Payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFound(ResourceNotFound ex) {
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).status(HttpStatus.NOT_FOUND).success(true).build();
        return new ResponseEntity<> (response, HttpStatus.NOT_FOUND);
    }
}