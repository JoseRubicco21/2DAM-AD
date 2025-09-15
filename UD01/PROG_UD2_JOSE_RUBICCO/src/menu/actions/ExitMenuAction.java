package menu.actions;
import java.util.Scanner;

import menu.MenuAction;
import menu.MenuResult;

public class ExitMenuAction implements MenuAction{

    @Override
    public MenuResult execute() {
        System.out.println("Saliendo del menu...");
        return MenuResult.EXIT;
    }

    @Override
    public MenuResult execute(Scanner sc) {
        System.out.println("Saliendo del menu...");
        return MenuResult.EXIT;
    }
    
}
