package com.youmanscode.personalfinancetrackerapi.exceptionhandling;

import com.youmanscode.personalfinancetrackerapi.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException exception) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(404);
        errorResponse.setError("NOT_FOUND");
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }
}
