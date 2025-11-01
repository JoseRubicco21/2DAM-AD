package com.ad_ud2_at2.services.connectors.exceptions;

import com.ad_ud2_at2.services.exceptions.TemplateException;

public class MySQLConnectorException extends TemplateException {
    
    public MySQLConnectorException(String msg){
        super(msg);
        setExceptionCode("CODE -SQL0-");
    }
}
