package menu.actions;

import java.util.Scanner;

import menu.MenuAction;
import menu.MenuResult;

public class UnimplementedMenuAction implements MenuAction {

    @Override
    public MenuResult execute() {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public MenuResult execute(Scanner sc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}