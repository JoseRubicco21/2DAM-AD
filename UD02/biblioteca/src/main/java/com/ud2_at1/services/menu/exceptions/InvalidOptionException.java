package com.ud2_at1.services.menu.exceptions;

import com.ud2_at1.services.exceptions.TemplateException;

public class InvalidOptionException extends TemplateException {

    public InvalidOptionException(String msg){
        super(msg);
        setExceptionCode("CODE MENU00");
    }
    
    
}
