package com.ud2_at1.services.menu.implementations;

import java.util.Scanner;

import com.ud2_at1.services.menu.Menu;
import com.ud2_at1.services.menu.actions.ExitMenuAction;
import com.ud2_at1.services.menu.actions.gestor_biblioteca.libro.*;
import com.ud2_at1.services.menu.components.MenuOption;
import com.ud2_at1.services.menu.exceptions.InvalidInputException;
import com.ud2_at1.services.menu.exceptions.InvalidOptionException;

public class GestorLibrosMenu extends Menu {
 
    public GestorLibrosMenu(Scanner sc){
        this.addOption(new MenuOption("Crear Libro", new CreateLibroMenuAction(sc)));
        this.addOption(new MenuOption("Listar Libros", new ListLibrosMenuAction()));
        this.addOption(new MenuOption("Buscar Libro", new SearchLibroMenuAction(sc)));
        this.addOption(new MenuOption("Actualizar Libro", new UpdateLibroMenuAction(sc)));
        this.addOption(new MenuOption("Eliminar Libro", new DeleteLibroMenuAction(sc)));
        this.addOption(new MenuOption("Volver al Menú Principal", new ExitMenuAction()));
    }


    @Override
    public Menu display() {
        System.out.println("\n=== GESTOR DE LIBROS ===");
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
