package com.ud2_at1.services.menu.actions.gestor_biblioteca.autor;

import java.util.Scanner;

import com.ud2_at1.dao.implementations.AutorDAO;
import com.ud2_at1.models.Autor;
import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.state.MenuResult;

public class DeleteAutorMenuAction extends MenuAction {

    private Scanner scanner;
    private AutorDAO autorDAO;

    public DeleteAutorMenuAction(Scanner scanner) {
        this.scanner = scanner;
        this.autorDAO = new AutorDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== ELIMINAR AUTOR ===");
            
            System.out.print("Ingrese el ID del autor a eliminar: ");
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
            
            // Display information before deletion
            Logger.warning("Autor a eliminar:");
            Logger.info("ID: " + autor.getId());
            Logger.info("Nombre: " + autor.getFirstName());
            Logger.info("Apellido: " + autor.getLastName());
            
            // Confirm deletion
            System.out.print("¿Está seguro que desea eliminar este autor? (s/N): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            
            if (confirm.equals("s") || confirm.equals("si")) {
                if (autorDAO.deleteById(id)) {
                    Logger.success("Autor eliminado exitosamente");
                } else {
                    Logger.error("Error al eliminar el autor");
                }
            } else {
                Logger.info("Eliminación cancelada");
            }
            
        } catch (Exception e) {
            Logger.error("Error al eliminar autor: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}