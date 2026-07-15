package com.youmanscode.personalfinancetrackerapi.exceptionhandling;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message){
        super(message);
    }
}
