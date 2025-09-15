package menu;
import java.util.Scanner;

public interface MenuAction {
 
    default MenuResult execute(){
        return execute(null);
    };

    public MenuResult execute(Scanner sc);
     
}
