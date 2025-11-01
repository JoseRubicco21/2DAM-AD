package com.ad_ud2_at2.services.menu.implementations;

import java.util.Scanner;

import com.ad_ud2_at2.services.menu.Menu;
import com.ad_ud2_at2.services.menu.actions.ExitMenuAction;
import com.ad_ud2_at2.services.menu.actions.main_menu.GestorClientesMenuRunAction;
import com.ad_ud2_at2.services.menu.actions.main_menu.GestorProductosMenuRunAction;
import com.ad_ud2_at2.services.menu.actions.main_menu.GestorPedidosMenuRunAction;
import com.ad_ud2_at2.services.menu.components.MenuOption;
import com.ad_ud2_at2.services.menu.exceptions.InvalidInputException;
import com.ad_ud2_at2.services.menu.exceptions.InvalidOptionException;

public class MainMenu extends Menu {

    Scanner sc;
    
    // We create the constructor where the implementation details of the menu is actually set. This is to have a set state.
    public MainMenu(Scanner sc){
        this.sc = sc;
        this.addOption(new MenuOption("Gestionar Clientes", new GestorClientesMenuRunAction(sc)));
        this.addOption(new MenuOption("Gestionar Productos", new GestorProductosMenuRunAction(sc)));
        this.addOption(new MenuOption("Gestionar Pedidos", new GestorPedidosMenuRunAction(sc)));
        this.addOption(new MenuOption("Salir", new ExitMenuAction()));
    }

    @Override
    public Menu display() {
        System.out.println("\n=== MENÚ PRINCIPAL - BOLECHAS ===");
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("[%d]: %s%n", i, this.options.get(i).getDescription());
        }
        return this;
    }

    @Override
    public int validateInput(String str) throws InvalidOptionException, InvalidInputException {
        if(!str.matches("\\b\\d+\\b")) throw new InvalidInputException("El dato no corresponde al formato estipulado.");
        int option = Integer.parseInt(str);
        if(option >= options.size() || option < 0) throw new InvalidOptionException("Opción invalida del menu.");
        return option;
    }
}