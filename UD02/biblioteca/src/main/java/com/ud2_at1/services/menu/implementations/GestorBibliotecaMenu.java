package com.ud2_at1.services.menu.implementations;

import java.util.Scanner;

import com.ud2_at1.services.menu.Menu;
import com.ud2_at1.services.menu.actions.ExitMenuAction;
import com.ud2_at1.services.menu.actions.gestor_biblioteca.CreateBibliotecaMenuAction;
import com.ud2_at1.services.menu.actions.gestor_biblioteca.ShowStatisticsMenuAction;
import com.ud2_at1.services.menu.actions.gestor_biblioteca.autor.*;
import com.ud2_at1.services.menu.actions.gestor_biblioteca.libro.*;
import com.ud2_at1.services.menu.components.MenuOption;
import com.ud2_at1.services.menu.exceptions.InvalidInputException;
import com.ud2_at1.services.menu.exceptions.InvalidOptionException;

public class GestorBibliotecaMenu extends Menu {

    public GestorBibliotecaMenu(Scanner sc){
        
        // Autor operations
        this.addOption(new MenuOption("=== GESTIÓN DE AUTORES ===", null));
        this.addOption(new MenuOption("Crear Autor", new CreateAutorMenuAction(sc)));
        this.addOption(new MenuOption("Listar Autores", new ListAutoresMenuAction()));
        this.addOption(new MenuOption("Buscar Autor", new SearchAutorMenuAction(sc)));
        this.addOption(new MenuOption("Actualizar Autor", new UpdateAutorMenuAction(sc)));
        this.addOption(new MenuOption("Eliminar Autor", new DeleteAutorMenuAction(sc)));
        
        // Libro operations
        this.addOption(new MenuOption("=== GESTIÓN DE LIBROS ===", null));
        this.addOption(new MenuOption("Crear Libro", new CreateLibroMenuAction(sc)));
        this.addOption(new MenuOption("Listar Libros", new ListLibrosMenuAction()));
        this.addOption(new MenuOption("Buscar Libro", new SearchLibroMenuAction(sc)));
        this.addOption(new MenuOption("Actualizar Libro", new UpdateLibroMenuAction(sc)));
        this.addOption(new MenuOption("Eliminar Libro", new DeleteLibroMenuAction(sc)));
        
        // System operations
        this.addOption(new MenuOption("=== SISTEMA ===", null));
        this.addOption(new MenuOption("Estadísticas", new ShowStatisticsMenuAction()));
        this.addOption(new MenuOption("Crear Base de datos", new CreateBibliotecaMenuAction(sc)));
        this.addOption(new MenuOption("Salir", new ExitMenuAction()));
    }


    @Override
    public Menu display() {
        System.out.println("\n=== GESTOR DE BIBLIOTECA ===");
        for (int i = 0; i < options.size(); i++) {
            // Skip displaying separator options (those with null actions)
            if (this.options.get(i).getAction() != null) {
                System.out.printf("[%d]: %s%n", i, this.options.get(i).getDescription());
            } else {
                System.out.println("\n" + this.options.get(i).getDescription());
            }
        }
        return this;
    }

    @Override
    public int validateInput(String str) throws InvalidOptionException, InvalidInputException {
        if(!str.matches("\\b\\d+\\b")) throw new InvalidInputException("El dato no corresponde al formato estipulado.");
        int option = Integer.parseInt(str);
        if(option >= options.size() || option < 0) throw new InvalidOptionException("Opción invalida del menu.");
        
        // Check if the selected option has a null action (separator)
        if (options.get(option).getAction() == null) {
            throw new InvalidOptionException("Opción no seleccionable.");
        }
        
        return option;
    }

}
