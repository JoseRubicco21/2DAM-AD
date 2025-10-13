package com.ad.json.menu.implementations;
import java.util.List;
import java.util.Scanner;

import com.ad.json.menu.Menu;
import com.ad.json.menu.components.MenuOption;
import com.ad.json.models.Producto;
import com.ad.json.menu.actions.*;

public class MainMenu extends Menu {


    public MainMenu(Scanner sc, List<Producto> prods){
        this.addOption(new MenuOption("Cargar productos", new GetProuctsAction(prods)));
        this.addOption(new MenuOption("Guardar productos", new SaveToJsonAction(prods)));
        this.addOption(new MenuOption("Leer y mostrar desde fichero", new ReadAndDisplayAction()));
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
