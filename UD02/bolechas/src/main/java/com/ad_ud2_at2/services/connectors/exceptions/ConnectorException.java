package com.ad_ud2_at2.services.connectors.exceptions;

import com.ad_ud2_at2.services.exceptions.TemplateException;

public class ConnectorException extends TemplateException {
    
    public ConnectorException(String msg){
        super(msg);
        setExceptionCode("CODE -SQL0-");
    }
}
