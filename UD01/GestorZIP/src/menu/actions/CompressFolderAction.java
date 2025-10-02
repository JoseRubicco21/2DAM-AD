package menu.actions;

import java.nio.file.Paths;
import java.util.Scanner;

import menu.components.MenuAction;
import menu.state.MenuResult;

import zip.managers.CrearZip;

public class CompressFolderAction extends MenuAction {

    @Override
    public MenuResult execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
    public MenuResult execute(Scanner sc){
       
        return MenuResult.CONTINUE;
    }
}
