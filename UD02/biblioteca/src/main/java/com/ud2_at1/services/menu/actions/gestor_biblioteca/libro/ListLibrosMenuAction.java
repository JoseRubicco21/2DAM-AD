package com.ud2_at1.services.menu.actions.gestor_biblioteca.libro;

import java.util.List;

import com.ud2_at1.dao.implementations.LibroDAO;
import com.ud2_at1.models.Libro;
import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.state.MenuResult;

public class ListLibrosMenuAction extends MenuAction {

    private LibroDAO libroDAO;

    public ListLibrosMenuAction() {
        this.libroDAO = new LibroDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== LISTA DE LIBROS ===");
            
            List<Libro> libros = libroDAO.getAll();
            
            if (libros.isEmpty()) {
                Logger.warning("No hay libros registrados en el sistema");
                return MenuResult.CONTINUE;
            }
            
            System.out.println("\n┌──────┬─────────────────────┬─────────────────────┬─────────────────┬───────────────┐");
            System.out.println("│  ID  │       TÍTULO        │       AUTOR         │      FECHA      │     ISBN      │");
            System.out.println("├──────┼─────────────────────┼─────────────────────┼─────────────────┼───────────────┤");
            
            for (Libro libro : libros) {
                String authorName = libro.getAuthor() != null ? 
                    libro.getAuthor().getFirstName() + " " + libro.getAuthor().getLastName() : "N/A";
                
                System.out.printf("│ %-4d │ %-19s │ %-19s │ %-15s │ %-13s │%n", 
                    libro.getId(), 
                    truncateString(libro.getTitle(), 19),
                    truncateString(authorName, 19),
                    libro.getDate() != null ? libro.getDate().toString() : "N/A",
                    libro.getISBN() != null ? libro.getISBN() : "N/A"
                );
            }
            
            System.out.println("└──────┴─────────────────────┴─────────────────────┴─────────────────┴───────────────┘");
            Logger.info("Total de libros: " + libros.size());
            
        } catch (Exception e) {
            Logger.error("Error al listar libros: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
    
    private String truncateString(String str, int maxLength) {
        if (str == null) return "";
        if (str.length() <= maxLength) return str;
        return str.substring(0, maxLength - 3) + "...";
    }
}