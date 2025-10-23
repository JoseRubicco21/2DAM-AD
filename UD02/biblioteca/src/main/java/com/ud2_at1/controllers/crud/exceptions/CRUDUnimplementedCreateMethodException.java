package com.ud2_at1.controllers.crud.exceptions;

public class CRUDUnimplementedCreateMethodException extends CRUDUnimplementedMethodException {

    public CRUDUnimplementedCreateMethodException(String msg) {
        super(msg);
        setExceptionCode("CODE CRUD01");
    }
    
}
