package com.ud2_at1.services.menu.exceptions;

public class InvalidOptionException extends Exception {


    @Override
    public String getMessage() {
        return "Invalid Menu option.";
    }
    
    
}
