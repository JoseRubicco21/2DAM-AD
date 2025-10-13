package com.ad.json.menu.exceptions;

public class InvalidOptionException extends Exception {


    @Override
    public String getMessage() {
        return "Invalid Menu option.";
    }
    
    
}
