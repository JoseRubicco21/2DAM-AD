package fichero;

import java.io.File;
import java.util.Scanner;

import menu.Menu;
import menu.MenuResult;
import menu.MenuState;
import menu.exceptions.InvalidInputException;
import menu.exceptions.InvalidOptionException;
import menu.implementations.MainMenu;

public class ManejoFicheroTexto {
    
    // Again not the main. Controllers should be separate from main. This is kinda mvc without v.
    // You can make the case for the console being the view.
    
    public void main(){
        Scanner sc = new Scanner(System.in);
        FicheroTexto file = new FicheroTexto(new File("./src/datos/file.txt"));
        Menu menu = new MainMenu(sc, file);

        menu.setState(MenuState.ACTIVE);
        while (menu.isActive()) {
            menu.display();
            try {
                int option = Integer.parseInt(sc.nextLine());
                MenuResult result = menu.getOptions()
                .get(option)
                .getAction()
                .execute(sc, file);
                if(result == MenuResult.EXIT) menu.setState(MenuState.INACTIVE);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (InvalidOptionException e){
                System.out.println(e.getMessage());
            }
        
        }
        
        sc.close();
    }
}
