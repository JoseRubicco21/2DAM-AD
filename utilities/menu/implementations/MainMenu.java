package menu.implementations;
import java.io.File;
import java.util.Scanner;

import fichero.FicheroTexto;
import menu.Menu;
import menu.MenuOption;
import menu.actions.ReadFileMenuAction;
import menu.actions.WriteFileMenuAction;
import menu.actions.ExitMenuAction;

public class MainMenu extends Menu {


    // We create the constructor we're the implementation details of the menu is actually set. This is to have a set state.
    public MainMenu(Scanner sc, FicheroTexto file){
    }


    @Override
    public void display() {
       for (int i = 0; i < options.size(); i++) {
            System.out.printf("[%d]: %s%n", i, this.options.get(i).getDescription());
       }
    }
}
