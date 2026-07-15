package com.youmanscode.personalfinancetrackerapi.exceptionhandling;

import com.youmanscode.personalfinancetrackerapi.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound
            (ResourceNotFoundException exception) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setError(HttpStatus.NOT_FOUND.name());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors
            (MethodArgumentNotValidException exception) {

        String message = exception
                .getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.name());
        errorResponse.setMessage(message);
        errorResponse.setTimeStamp(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException
            (IllegalArgumentException argumentException) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponse.setError(HttpStatus.NOT_ACCEPTABLE.name());
        errorResponse.setMessage(argumentException.getMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(errorResponse);
    }
}
