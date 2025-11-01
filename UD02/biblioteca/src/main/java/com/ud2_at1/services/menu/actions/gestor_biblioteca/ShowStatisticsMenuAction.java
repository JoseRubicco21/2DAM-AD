package com.ud2_at1.services.menu.actions.gestor_biblioteca;

import com.ud2_at1.dao.implementations.AutorDAO;
import com.ud2_at1.dao.implementations.LibroDAO;
import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.state.MenuResult;

public class ShowStatisticsMenuAction extends MenuAction {

    private AutorDAO autorDAO;
    private LibroDAO libroDAO;

    public ShowStatisticsMenuAction() {
        this.autorDAO = new AutorDAO();
        this.libroDAO = new LibroDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== ESTADÍSTICAS DE LA BIBLIOTECA ===");
            
            // Get counts
            int totalAutores = autorDAO.count();
            int totalLibros = libroDAO.count();
            
            // Display statistics
            System.out.println("\n┌─────────────────────────────────────┐");
            System.out.println("│         ESTADÍSTICAS GENERALES      │");
            System.out.println("├─────────────────────────────────────┤");
            System.out.printf("│ Total de Autores:         %-8d │%n", totalAutores);
            System.out.printf("│ Total de Libros:          %-8d │%n", totalLibros);
            
            if (totalAutores > 0) {
                double avgLibrosPorAutor = (double) totalLibros / totalAutores;
                System.out.printf("│ Promedio libros/autor:    %-8.2f │%n", avgLibrosPorAutor);
            }
            
            System.out.println("└─────────────────────────────────────┘");
            
            // Additional insights
            if (totalLibros == 0 && totalAutores == 0) {
                Logger.warning("La biblioteca está vacía. Comience creando autores y libros.");
            } else if (totalLibros == 0) {
                Logger.info("Hay autores registrados pero no hay libros. Considere agregar libros.");
            } else if (totalAutores == 0) {
                Logger.warning("Hay libros pero no autores. Esto no debería ocurrir.");
            } else {
                Logger.success("La biblioteca tiene datos registrados correctamente.");
            }
            
        } catch (Exception e) {
            Logger.error("Error al obtener estadísticas: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}