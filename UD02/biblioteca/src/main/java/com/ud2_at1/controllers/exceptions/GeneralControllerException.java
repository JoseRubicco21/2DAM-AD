package com.ud2_at1.controllers.exceptions;

import com.ud2_at1.services.exceptions.TemplateException;

public class GeneralControllerException extends TemplateException {
    public GeneralControllerException(String msg){
        super(msg);
        setExceptionCode("CODE CTRL00");
    }
}
