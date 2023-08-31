package com.example.projectbase.exception;

public class ForeignKeyConstraintException extends RuntimeException {
    public ForeignKeyConstraintException(String errorMessage){
        super(errorMessage);
    }
}
