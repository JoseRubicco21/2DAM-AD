package com.ud2_at1.services.menu.actions.gestor_biblioteca.autor;

import java.util.Scanner;

import com.ud2_at1.dao.implementations.AutorDAO;
import com.ud2_at1.models.Autor;
import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.state.MenuResult;

public class CreateAutorMenuAction extends MenuAction {

    private Scanner scanner;
    private AutorDAO autorDAO;

    public CreateAutorMenuAction(Scanner scanner) {
        this.scanner = scanner;
        this.autorDAO = new AutorDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== CREAR NUEVO AUTOR ===");
            
            System.out.print("Ingrese el nombre del autor: ");
            String firstName = scanner.nextLine().trim();
            
            if (firstName.isEmpty()) {
                Logger.error("El nombre no puede estar vacío");
                return MenuResult.CONTINUE;
            }
            
            System.out.print("Ingrese el apellido del autor: ");
            String lastName = scanner.nextLine().trim();
            
            if (lastName.isEmpty()) {
                Logger.error("El apellido no puede estar vacío");
                return MenuResult.CONTINUE;
            }
            
            // Check if author already exists
            Autor existingAutor = autorDAO.getByFullName(firstName, lastName);
            if (existingAutor != null) {
                Logger.warning("Ya existe un autor con ese nombre: " + firstName + " " + lastName);
                return MenuResult.CONTINUE;
            }
            
            // Create new author
            Autor nuevoAutor = new Autor(firstName, lastName);
            
            if (autorDAO.save(nuevoAutor)) {
                Logger.success("Autor creado exitosamente con ID: " + nuevoAutor.getId());
                Logger.info("Nombre completo: " + firstName + " " + lastName);
            } else {
                Logger.error("Error al crear el autor");
            }
            
        } catch (Exception e) {
            Logger.error("Error inesperado al crear autor: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}