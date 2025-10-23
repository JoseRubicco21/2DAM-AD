package com.ud2_at1.controllers.crud.exceptions;

public class CRUDUnimplementedReadMethodException extends CRUDUnimplementedMethodException {

    public CRUDUnimplementedReadMethodException(String msg) {
        super(msg);
        setExceptionCode("CODE CRUD02");
    }
    
}
