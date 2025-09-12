package menu.actions;

import menu.MenuAction;

public class UnimplementedMenuAction implements MenuAction {

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}