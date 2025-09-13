import java.util.Scanner;

import menu.*;
import menu.implementations.MainMenu;

public class App {
    

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);

        // We instatiate an implementation of our menu class and set it to Active. 
        // Menus are always initialized as inactive as we might want to create them
        // in batch and then activate according to our needs.
        Menu mainMenu = new MainMenu();
        mainMenu.setState(MenuState.ACTIVE);
       
        // We use a while loop based on the MenuState to display and get the result of the action executed.
        while (mainMenu.isActive()) {
            mainMenu.display();
            int option = sc.nextInt();
            MenuResult result = mainMenu.getOptions() // Get all the options of the main menu
            .get(option) // Get the option the user chooses
            .getAction() // Get the MenuOption action of the option
            .execute(sc); // Execute the code in the action.
            
            if(result == MenuResult.EXIT) mainMenu.setState(MenuState.INACTIVE); // Switch the state of the menu depending on the result.
        }

        sc.close();
    }
}
