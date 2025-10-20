package com.exercise.services.menu.exceptions;

public class InvalidOptionException extends Exception {


    @Override
    public String getMessage() {
        return "Invalid Menu option.";
    }
    
    
}
