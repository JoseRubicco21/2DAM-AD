package menu.actions;
import menu.MenuAction;

public class ExitMenuAction implements MenuAction{

    @Override
    public void execute() {
        System.out.println("Saliendo del menu...");
    }
    
}
