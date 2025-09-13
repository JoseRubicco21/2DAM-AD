import menu.*;
import menu.actions.AugmentSalaryMenuAction;
import menu.actions.ExitMenuAction;
import menu.actions.GetNumberOfStudentsTallerThanMenuAction;

public class App {
    

    public static void main(String[] args) throws Exception {
        
        // We create the main menu instance and then build it. This is probably better done creating a
        // new Menu class that inherits from the base menu class 
        Menu mainMenu = new Menu();
        mainMenu
        .addOption(new MenuOption("Aumentar Salario.", new AugmentSalaryMenuAction()))
        .addOption(new MenuOption("Mostrar alumnos mayores de 18."))
        .addOption(new MenuOption("Mostrar alumnos con altura mayor a 1.75m", new GetNumberOfStudentsTallerThanMenuAction()))
        .addOption(new MenuOption("Salir del menu"));
    }
}
