package menu.implementations;
import menu.Menu;
import menu.actions.CompressFolderAction;
import menu.components.MenuOption;

public class MainMenu extends Menu {


    // We create the constructor we're the implementation details of the menu is actually set. This is to have a set state.
    public MainMenu(){
        this.addOption(new MenuOption("Comprimir archivos", new CompressFolderAction()));
    
    }


    @Override
    public void display() {
       for (int i = 0; i < options.size(); i++) {
            System.out.printf("[%d]: %s%n", i, this.options.get(i).getDescription());
       }
    }
}
