package zip.exceptions;
import java.lang.Exception;

public class isDirectoryException extends Exception {

    public isDirectoryException(){

    }

    @Override
    public String getMessage() {
        return "File is a directory";
    };
   
    
    
}
