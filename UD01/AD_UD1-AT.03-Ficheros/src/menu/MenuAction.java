package menu;
import java.util.Scanner;
import java.io.File;
import fichero.FicheroTexto;

public interface MenuAction {
 
    default MenuResult execute(){
        return execute(null, null);
    };
    public MenuResult execute(Scanner sc, FicheroTexto file);
    
}
