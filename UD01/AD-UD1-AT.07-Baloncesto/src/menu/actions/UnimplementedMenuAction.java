package menu.actions;

import java.io.File;
import java.util.Scanner;

import menu.components.MenuAction;
import menu.state.MenuResult;

public class UnimplementedMenuAction extends MenuAction {

    @Override
    public MenuResult execute() {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    
}