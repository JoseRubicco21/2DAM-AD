package menu.actions;

import java.util.Scanner;

import menu.components.MenuAction;
import menu.state.MenuResult;


public class CompressFolderMenuAction extends MenuAction {

    private Scanner sc;

    public CompressFolderMenuAction(Scanner sc){
        this.sc = sc;
    }

    @Override
    public MenuResult execute() {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
    public MenuResult execute(Scanner sc){
       
        String folderName = this.getFolderName(sc);
        

        return MenuResult.CONTINUE;
    }

    private String getFolderName(Scanner sc){
        System.out.println("Introduce el nombre del direcotorio que quieres escribir");
        String result = sc.nextLine();
        return result;
    }
}
