package com.ud2_at1.services.menu.actions.gestor_biblioteca.autor;

import java.util.List;
import java.util.Scanner;

import com.ud2_at1.dao.implementations.AutorDAO;
import com.ud2_at1.models.Autor;
import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.state.MenuResult;

public class SearchAutorMenuAction extends MenuAction {

    private Scanner scanner;
    private AutorDAO autorDAO;

    public SearchAutorMenuAction(Scanner scanner) {
        this.scanner = scanner;
        this.autorDAO = new AutorDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== BUSCAR AUTOR ===");
            
            System.out.println("Opciones de búsqueda:");
            System.out.println("1. Buscar por ID");
            System.out.println("2. Buscar por nombre");
            System.out.println("3. Buscar por apellido");
            System.out.println("4. Buscar por nombre completo");
            System.out.println("5. Búsqueda parcial");
            
            System.out.print("Seleccione una opción (1-5): ");
            String option = scanner.nextLine().trim();
            
            switch (option) {
                case "1":
                    searchById();
                    break;
                case "2":
                    searchByFirstName();
                    break;
                case "3":
                    searchByLastName();
                    break;
                case "4":
                    searchByFullName();
                    break;
                case "5":
                    searchPartial();
                    break;
                default:
                    Logger.error("Opción inválida");
            }
            
        } catch (Exception e) {
            Logger.error("Error en la búsqueda: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
    
    private void searchById() {
        System.out.print("Ingrese el ID del autor: ");
        String idStr = scanner.nextLine().trim();
        
        if (!idStr.matches("\\d+")) {
            Logger.error("ID inválido. Debe ser un número entero");
            return;
        }
        
        int id = Integer.parseInt(idStr);
        Autor autor = autorDAO.get(id);
        
        if (autor != null) {
            displayAutor(autor);
        } else {
            Logger.warning("No se encontró un autor con ID: " + id);
        }
    }
    
    private void searchByFirstName() {
        System.out.print("Ingrese el nombre: ");
        String firstName = scanner.nextLine().trim();
        
        if (firstName.isEmpty()) {
            Logger.error("El nombre no puede estar vacío");
            return;
        }
        
        Autor autor = autorDAO.getByFirstName(firstName);
        
        if (autor != null) {
            displayAutor(autor);
        } else {
            Logger.warning("No se encontró un autor con el nombre: " + firstName);
        }
    }
    
    private void searchByLastName() {
        System.out.print("Ingrese el apellido: ");
        String lastName = scanner.nextLine().trim();
        
        if (lastName.isEmpty()) {
            Logger.error("El apellido no puede estar vacío");
            return;
        }
        
        Autor autor = autorDAO.getByLastName(lastName);
        
        if (autor != null) {
            displayAutor(autor);
        } else {
            Logger.warning("No se encontró un autor con el apellido: " + lastName);
        }
    }
    
    private void searchByFullName() {
        System.out.print("Ingrese el nombre: ");
        String firstName = scanner.nextLine().trim();
        
        System.out.print("Ingrese el apellido: ");
        String lastName = scanner.nextLine().trim();
        
        if (firstName.isEmpty() || lastName.isEmpty()) {
            Logger.error("Nombre y apellido no pueden estar vacíos");
            return;
        }
        
        Autor autor = autorDAO.getByFullName(firstName, lastName);
        
        if (autor != null) {
            displayAutor(autor);
        } else {
            Logger.warning("No se encontró un autor con el nombre: " + firstName + " " + lastName);
        }
    }
    
    private void searchPartial() {
        System.out.print("Ingrese término de búsqueda: ");
        String searchTerm = scanner.nextLine().trim();
        
        if (searchTerm.isEmpty()) {
            Logger.error("El término de búsqueda no puede estar vacío");
            return;
        }
        
        List<Autor> autores = autorDAO.searchByName(searchTerm);
        
        if (autores.isEmpty()) {
            Logger.warning("No se encontraron autores que coincidan con: " + searchTerm);
        } else {
            Logger.info("Autores encontrados:");
            for (Autor autor : autores) {
                displayAutor(autor);
                System.out.println("---");
            }
        }
    }
    
    private void displayAutor(Autor autor) {
        Logger.info("=== INFORMACIÓN DEL AUTOR ===");
        Logger.info("ID: " + autor.getId());
        Logger.info("Nombre: " + autor.getFirstName());
        Logger.info("Apellido: " + autor.getLastName());
        Logger.info("Nombre completo: " + autor.getFirstName() + " " + autor.getLastName());
    }
}