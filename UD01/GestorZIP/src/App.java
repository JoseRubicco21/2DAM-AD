import java.util.Scanner;

import menu.implementations.MainMenu;
import menu.state.MenuResult;
import menu.state.MenuState;
import menu.Menu;
import menu.exceptions.InvalidInputException;
import menu.exceptions.InvalidOptionException;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Menu menu = new MainMenu(sc);

        // Activate the menu for usage as it always Initialized as Inactive.
        menu.setState(MenuState.ACTIVE);

        // Menu flow
        while (menu.isActive()) {
            try{
                menu.display();
                menu.choose(sc);
                menu.execute();
            if(menu.getLastResult() == MenuResult.EXIT) menu.setState(MenuState.INACTIVE);
            } catch(InvalidInputException e){
                System.err.println(e.getMessage());
            } catch(InvalidOptionException e){
                System.err.println(e.getMessage());
            }
        }
    }
}
