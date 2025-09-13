package menu.implementations;
import menu.Menu;
import menu.MenuOption;
import menu.actions.AugmentSalaryMenuAction;
import menu.actions.ExitMenuAction;
import menu.actions.GetNumberOfStudentsTallerThanMenuAction;
import menu.actions.UnimplementedMenuAction;

public class MainMenu extends Menu {
    
    // We create the constructor we're the implementation details of the menu is actually set. This is to have a set state.
    public MainMenu(){
        this
        .addOption(new MenuOption("", new AugmentSalaryMenuAction()))
        .addOption(new MenuOption("", new GetNumberOfStudentsTallerThanMenuAction()))
        .addOption(new MenuOption("", new UnimplementedMenuAction()))
        .addOption(new MenuOption("", new ExitMenuAction()));
    }

    @Override
    public void display() {
       options.forEach((opt, index) -> {
        System.out.printf("[%d] %s%n", index, opt.getDescription);
       });
    }
}
