package menu.actions;

import java.io.File;
import java.util.Scanner;

import fichero.FicheroTexto;
import menu.MenuAction;
import menu.MenuResult;

public class ReadFileMenuAction implements MenuAction {

    @Override
    public MenuResult execute(Scanner sc, FicheroTexto file) {
        file.leer();
        return MenuResult.CONTINUE;
    }
    
}
