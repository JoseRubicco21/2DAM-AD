package menu.implementations;
import java.util.Scanner;

import menu.Menu;
import menu.actions.CreateEquipoMenuAction;
import menu.actions.ExitMenuAction;
import menu.actions.LoadClasificationMenuAction;
import menu.actions.SaveClasificationAction;
import menu.actions.ShowEquipoAction;
import menu.components.MenuOption;
import models.Clasificacion;

public class MainMenu extends Menu {

    private Scanner sc;
    private Clasificacion cls;

    public MainMenu(Scanner sc, Clasificacion cls){
        this.addOption(new MenuOption("Añadir equipo", new CreateEquipoMenuAction(sc, cls)));
        this.addOption(new MenuOption("Mostrar equipos en memoria", new ShowEquipoAction(cls)));
        this.addOption(new MenuOption("Guardar clasificación", new SaveClasificationAction(cls)));
        this.addOption(new MenuOption("Cargar clasificación", new LoadClasificationMenuAction(cls)));
        this.addOption(new MenuOption("Salir", new ExitMenuAction()));
    }

    @Override
    public Menu display() {
       for (int i = 0; i < options.size(); i++) {
            System.out.printf("[%d]: %s%n", i, this.options.get(i).getDescription());
       }
       return this;
    }

}
