package menu.implementations;
import menu.Menu;
import menu.MenuOption;
import menu.actions.AugmentSalaryMenuAction;
import menu.actions.ExitMenuAction;
import menu.actions.GetNumberOfStudentsOfAge;
import menu.actions.GetNumberOfStudentsTallerThan;
import menu.actions.UnimplementedMenuAction;

public class MainMenu extends Menu {
    
    // We create the constructor we're the implementation details of the menu is actually set. This is to have a set state.
    public MainMenu(){
        this
        .addOption(new MenuOption("Aumento de salario", new AugmentSalaryMenuAction()))
        .addOption(new MenuOption("Estudiantes mayores de 18 años", new GetNumberOfStudentsOfAge()))
        .addOption(new MenuOption("Estudiantes mas altos que 1.75m", new GetNumberOfStudentsTallerThan()))
        .addOption(new MenuOption("Salir", new ExitMenuAction()));
    }

    @Override
    public void display() {
       for (int i = 0; i < options.size(); i++) {
            System.out.printf("[%d]: %s%n", i, this.options.get(i).getDescription());
       }
    }
}
