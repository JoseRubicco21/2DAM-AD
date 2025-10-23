package com.ud2_at1.controllers.crud.exceptions;

public class CRUDUnimplementedDeleteMethodException extends CRUDUnimplementedMethodException {

    public CRUDUnimplementedDeleteMethodException(String msg) {
        super(msg);
        setExceptionCode("CODE CRUD04");
    }
    
}
