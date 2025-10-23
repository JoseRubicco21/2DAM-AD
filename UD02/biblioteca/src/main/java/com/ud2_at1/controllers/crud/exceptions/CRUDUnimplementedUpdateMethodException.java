package com.ud2_at1.controllers.crud.exceptions;

public class CRUDUnimplementedUpdateMethodException extends CRUDUnimplementedMethodException {

    public CRUDUnimplementedUpdateMethodException(String msg) {
        super(msg);
        setExceptionCode("CODE CRUD03");
    }
    
}
