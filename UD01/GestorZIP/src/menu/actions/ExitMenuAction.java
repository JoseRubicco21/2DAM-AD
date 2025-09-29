package menu.actions;
import menu.components.MenuAction;
import menu.state.MenuResult;

public class ExitMenuAction extends MenuAction{

    @Override
    public MenuResult execute() {
        System.out.println("Saliendo del menu...");
        return MenuResult.EXIT;
    }
}
