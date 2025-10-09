import java.util.Scanner;

import menu.Menu;
import menu.implementations.MainMenu;
import menu.state.MenuState;
import models.Clasificacion;

public class App {
    public static void main(String[] args) throws Exception {
        Clasificacion cls = Clasificacion.loadClasificacion();
        Scanner sc = new Scanner(System.in);        
        Menu menu = new MainMenu(sc, cls);

        menu.setState(MenuState.ACTIVE);

        while (menu.isActive()) {
            menu.display().choose(sc).execute();
        }

    }
}
