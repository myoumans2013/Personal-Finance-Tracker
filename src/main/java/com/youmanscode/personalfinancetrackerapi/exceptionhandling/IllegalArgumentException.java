package com.youmanscode.personalfinancetrackerapi.exceptionhandling;

public class IllegalArgumentException extends RuntimeException{
    public IllegalArgumentException(String message) {
        super(message);
    }
}
