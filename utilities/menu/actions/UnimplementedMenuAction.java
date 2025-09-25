package menu.actions;

import java.io.File;
import java.util.Scanner;

import fichero.FicheroTexto;
import menu.MenuAction;
import menu.MenuResult;

public class UnimplementedMenuAction implements MenuAction {

    @Override
    public MenuResult execute() {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public MenuResult execute(Scanner sc, FicheroTexto file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

   
    
}