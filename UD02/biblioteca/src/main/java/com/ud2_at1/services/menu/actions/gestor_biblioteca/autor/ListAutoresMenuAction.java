package com.ud2_at1.services.menu.actions.gestor_biblioteca.autor;

import java.util.List;

import com.ud2_at1.dao.implementations.AutorDAO;
import com.ud2_at1.models.Autor;
import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.state.MenuResult;

public class ListAutoresMenuAction extends MenuAction {

    private AutorDAO autorDAO;

    public ListAutoresMenuAction() {
        this.autorDAO = new AutorDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== LISTA DE AUTORES ===");
            
            List<Autor> autores = autorDAO.getAll();
            
            if (autores.isEmpty()) {
                Logger.warning("No hay autores registrados en el sistema");
                return MenuResult.CONTINUE;
            }
            
            System.out.println("\n┌──────┬─────────────────────┬─────────────────────┐");
            System.out.println("│  ID  │       NOMBRE        │      APELLIDO       │");
            System.out.println("├──────┼─────────────────────┼─────────────────────┤");
            
            for (Autor autor : autores) {
                System.out.printf("│ %-4d │ %-19s │ %-19s │%n", 
                    autor.getId(), 
                    truncateString(autor.getFirstName(), 19),
                    truncateString(autor.getLastName(), 19)
                );
            }
            
            System.out.println("└──────┴─────────────────────┴─────────────────────┘");
            Logger.info("Total de autores: " + autores.size());
            
        } catch (Exception e) {
            Logger.error("Error al listar autores: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
    
    private String truncateString(String str, int maxLength) {
        if (str == null) return "";
        if (str.length() <= maxLength) return str;
        return str.substring(0, maxLength - 3) + "...";
    }
}