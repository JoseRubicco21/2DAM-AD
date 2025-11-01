package com.ud2_at1.services.menu.implementations;

import java.util.Scanner;

import com.ud2_at1.services.menu.Menu;
import com.ud2_at1.services.menu.actions.ExitMenuAction;
import com.ud2_at1.services.menu.actions.gestor_biblioteca.CreateBibliotecaMenuAction;
import com.ud2_at1.services.menu.components.MenuOption;
import com.ud2_at1.services.menu.exceptions.InvalidInputException;
import com.ud2_at1.services.menu.exceptions.InvalidOptionException;

public class GestorBibliotecaMenu extends Menu {


    public GestorBibliotecaMenu(Scanner sc){

        this.addOption(new MenuOption("Crear Base de datos", new CreateBibliotecaMenuAction(sc)));
        this.addOption(new MenuOption("Salir", new ExitMenuAction()));
    }


    @Override
    public Menu display() {
       for (int i = 0; i < options.size(); i++) {
            System.out.printf("[%d]: %s%n", i, this.options.get(i).getDescription());
       }
       return this;
    }

    @Override
    public int validateInput(String str) throws InvalidOptionException, InvalidInputException {
        if(!str.matches("\\b\\d+\\b")) throw new InvalidInputException("El dato no corresponde al formato estipulado.");
        int option = Integer.parseInt(str);
        if(option > options.size() || option < 0) throw new InvalidOptionException("Opción invalida del menu.");
        return option;
    }

}
