package com.ad.json.menu.implementations;
import java.util.Scanner;

import com.ad.json.menu.Menu;
import com.ad.json.menu.components.MenuOption;
import com.ad.json.menu.actions.*;

public class MainMenu extends Menu {

    private Scanner sc;

    public MainMenu(Scanner sc){
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
