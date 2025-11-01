package com.ud2_at1.services.menu.actions.gestor_biblioteca.autor;

import java.util.Scanner;

import com.ud2_at1.dao.implementations.AutorDAO;
import com.ud2_at1.models.Autor;
import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.state.MenuResult;

public class UpdateAutorMenuAction extends MenuAction {

    private Scanner scanner;
    private AutorDAO autorDAO;

    public UpdateAutorMenuAction(Scanner scanner) {
        this.scanner = scanner;
        this.autorDAO = new AutorDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== ACTUALIZAR AUTOR ===");
            
            System.out.print("Ingrese el ID del autor a actualizar: ");
            String idStr = scanner.nextLine().trim();
            
            if (!idStr.matches("\\d+")) {
                Logger.error("ID inválido. Debe ser un número entero");
                return MenuResult.CONTINUE;
            }
            
            int id = Integer.parseInt(idStr);
            Autor autor = autorDAO.get(id);
            
            if (autor == null) {
                Logger.error("No se encontró un autor con ID: " + id);
                return MenuResult.CONTINUE;
            }
            
            // Display current information
            Logger.info("Autor actual:");
            Logger.info("ID: " + autor.getId());
            Logger.info("Nombre: " + autor.getFirstName());
            Logger.info("Apellido: " + autor.getLastName());
            
            // Update first name
            System.out.print("Nuevo nombre (actual: " + autor.getFirstName() + ") [Enter para mantener]: ");
            String newFirstName = scanner.nextLine().trim();
            if (!newFirstName.isEmpty()) {
                autor.setFirstName(newFirstName);
            }
            
            // Update last name
            System.out.print("Nuevo apellido (actual: " + autor.getLastName() + ") [Enter para mantener]: ");
            String newLastName = scanner.nextLine().trim();
            if (!newLastName.isEmpty()) {
                autor.setLastName(newLastName);
            }
            
            // Confirm update
            System.out.print("¿Confirmar actualización? (s/N): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            
            if (confirm.equals("s") || confirm.equals("si")) {
                autorDAO.update(autor);
                Logger.success("Autor actualizado exitosamente");
                Logger.info("Nuevo nombre completo: " + autor.getFirstName() + " " + autor.getLastName());
            } else {
                Logger.info("Actualización cancelada");
            }
            
        } catch (Exception e) {
            Logger.error("Error al actualizar autor: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}