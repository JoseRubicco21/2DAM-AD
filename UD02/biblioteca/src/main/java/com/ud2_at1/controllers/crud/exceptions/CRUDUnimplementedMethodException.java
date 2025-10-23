package com.ud2_at1.controllers.crud.exceptions;

import com.ud2_at1.services.exceptions.TemplateException;

public class CRUDUnimplementedMethodException extends TemplateException {
     
    

    public CRUDUnimplementedMethodException(String msg){
        super(msg);
        setExceptionCode("CODE CRUD00");
    }
}
