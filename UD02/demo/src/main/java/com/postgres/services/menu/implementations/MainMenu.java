package com.postgres.services.menu.implementations;

import java.util.Scanner;

import com.postgres.services.menu.Menu;
import com.postgres.services.menu.actions.CreateAlumnoAction;
import com.postgres.services.menu.actions.DeleteAlumnoAction;
import com.postgres.services.menu.actions.ExitMenuAction;
import com.postgres.services.menu.actions.ReadAllAlumnosAction;
import com.postgres.services.menu.actions.ReadAlumnoByIdAction;
import com.postgres.services.menu.actions.UpdateAlumnoAction;
import com.postgres.services.menu.components.MenuOption;
import com.postgres.services.menu.exceptions.InvalidInputException;
import com.postgres.services.menu.exceptions.InvalidOptionException;



public class MainMenu extends Menu {

    Scanner sc;
    
    // We create the constructor where the implementation details of the menu is actually set. This is to have a set state.
    public MainMenu(Scanner sc){
        this.sc = sc;
        this.addOption(new MenuOption("Salir", new ExitMenuAction()));
        this.addOption(new MenuOption("Crear alumno", new CreateAlumnoAction(sc)));
        this.addOption(new MenuOption("Leer alumnos", new ReadAllAlumnosAction()));
        this.addOption(new MenuOption("Leer alumno por id", new ReadAlumnoByIdAction(sc)));
        this.addOption(new MenuOption("Actualizar alumno", new UpdateAlumnoAction(sc)));
        this.addOption(new MenuOption("Borrar alumno", new DeleteAlumnoAction(sc)));
    }

    @Override
    public Menu display() {
        System.out.println("\n=== MENÚ PRINCIPAL - Alumnos ===");
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