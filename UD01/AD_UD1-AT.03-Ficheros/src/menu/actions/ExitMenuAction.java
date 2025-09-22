package menu.actions;
import java.io.File;
import java.util.Scanner;

import fichero.FicheroTexto;
import menu.MenuAction;
import menu.MenuResult;

public class ExitMenuAction implements MenuAction{

    @Override
    public MenuResult execute() {
        System.out.println("Saliendo del menu...");
        return MenuResult.EXIT;
    }

    @Override
    public MenuResult execute(Scanner sc, FicheroTexto file) {
        System.out.println("Saliendo del menu...");
        return MenuResult.EXIT;
    }
}
