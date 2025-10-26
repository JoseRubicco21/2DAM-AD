package  com.ud2_at1.services.menu.exceptions;

import com.ud2_at1.services.exceptions.TemplateException;

public class InvalidInputException extends TemplateException {

    public InvalidInputException(String msg){
        super(msg);
        setExceptionCode("CODE MENU01");
    }
    
    
}
