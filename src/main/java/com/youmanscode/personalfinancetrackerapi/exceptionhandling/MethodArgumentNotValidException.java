package com.youmanscode.personalfinancetrackerapi.exceptionhandling;

public class MethodArgumentNotValidException extends RuntimeException{

    public MethodArgumentNotValidException(String message) {
        super(message);
    }
}
