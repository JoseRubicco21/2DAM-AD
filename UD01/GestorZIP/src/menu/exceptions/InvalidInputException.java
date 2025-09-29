package menu.exceptions;

public class InvalidInputException extends Exception {

    @Override
    public String getMessage() {
        return "The format of the input is not adequate. Please enter an Integer.";
    }
    
    
}
