package com.ud2_at1.services.connectors.exceptions;

import com.ud2_at1.services.exceptions.TemplateException;

public class MySQLConnectorException extends TemplateException {
    
    public MySQLConnectorException(String msg){
        super(msg);
        setExceptionCode("CODE -SQL0-");
    }
}
