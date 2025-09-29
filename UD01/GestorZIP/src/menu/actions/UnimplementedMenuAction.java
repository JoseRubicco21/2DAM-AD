package menu.actions;

import menu.components.MenuAction;
import menu.state.MenuResult;

public class UnimplementedMenuAction extends MenuAction {

    @Override
    public MenuResult execute() {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    
}