package com.example.projectbase.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String errorMessage){
        super(errorMessage);
    }
}
