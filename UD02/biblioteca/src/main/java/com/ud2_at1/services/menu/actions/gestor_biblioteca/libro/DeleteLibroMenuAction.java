package com.ud2_at1.services.menu.actions.gestor_biblioteca.libro;

import java.util.Scanner;

import com.ud2_at1.dao.implementations.LibroDAO;
import com.ud2_at1.models.Libro;
import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.state.MenuResult;

public class DeleteLibroMenuAction extends MenuAction {

    private Scanner scanner;
    private LibroDAO libroDAO;

    public DeleteLibroMenuAction(Scanner scanner) {
        this.scanner = scanner;
        this.libroDAO = new LibroDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== ELIMINAR LIBRO ===");
            
            System.out.print("Ingrese el ID del libro a eliminar: ");
            String idStr = scanner.nextLine().trim();
            
            if (!idStr.matches("\\d+")) {
                Logger.error("ID inválido. Debe ser un número entero");
                return MenuResult.CONTINUE;
            }
            
            int id = Integer.parseInt(idStr);
            Libro libro = libroDAO.get(id);
            
            if (libro == null) {
                Logger.error("No se encontró un libro con ID: " + id);
                return MenuResult.CONTINUE;
            }
            
            // Display information before deletion
            Logger.warning("Libro a eliminar:");
            Logger.info("ID: " + libro.getId());
            Logger.info("Título: " + libro.getTitle());
            if (libro.getAuthor() != null) {
                Logger.info("Autor: " + libro.getAuthor().getFirstName() + " " + libro.getAuthor().getLastName());
            }
            Logger.info("Fecha de publicación: " + (libro.getDate() != null ? libro.getDate() : "N/A"));
            Logger.info("ISBN: " + libro.getISBN());
            
            // Confirm deletion
            System.out.print("¿Está seguro que desea eliminar este libro? (s/N): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            
            if (confirm.equals("s") || confirm.equals("si")) {
                if (libroDAO.deleteById(id)) {
                    Logger.success("Libro eliminado exitosamente");
                } else {
                    Logger.error("Error al eliminar el libro");
                }
            } else {
                Logger.info("Eliminación cancelada");
            }
            
        } catch (Exception e) {
            Logger.error("Error al eliminar libro: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}