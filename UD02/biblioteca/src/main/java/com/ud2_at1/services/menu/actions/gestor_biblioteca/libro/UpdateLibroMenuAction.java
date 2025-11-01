package com.ud2_at1.services.menu.actions.gestor_biblioteca.libro;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.ud2_at1.dao.implementations.AutorDAO;
import com.ud2_at1.dao.implementations.LibroDAO;
import com.ud2_at1.models.Autor;
import com.ud2_at1.models.Libro;
import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.state.MenuResult;

public class UpdateLibroMenuAction extends MenuAction {

    private Scanner scanner;
    private LibroDAO libroDAO;
    private AutorDAO autorDAO;
    private SimpleDateFormat dateFormat;

    public UpdateLibroMenuAction(Scanner scanner) {
        this.scanner = scanner;
        this.libroDAO = new LibroDAO();
        this.autorDAO = new AutorDAO();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== ACTUALIZAR LIBRO ===");
            
            System.out.print("Ingrese el ID del libro a actualizar: ");
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
            
            // Display current information
            displayCurrentInfo(libro);
            
            // Update fields
            updateTitle(libro);
            updateAuthor(libro);
            updatePublicationDate(libro);
            updateISBN(libro);
            
            // Confirm update
            System.out.print("¿Confirmar actualización? (s/N): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            
            if (confirm.equals("s") || confirm.equals("si")) {
                libroDAO.update(libro);
                Logger.success("Libro actualizado exitosamente");
                displayCurrentInfo(libro);
            } else {
                Logger.info("Actualización cancelada");
            }
            
        } catch (Exception e) {
            Logger.error("Error al actualizar libro: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
    
    private void displayCurrentInfo(Libro libro) {
        Logger.info("=== INFORMACIÓN ACTUAL ===");
        Logger.info("ID: " + libro.getId());
        Logger.info("Título: " + libro.getTitle());
        if (libro.getAuthor() != null) {
            Logger.info("Autor: " + libro.getAuthor().getFirstName() + " " + libro.getAuthor().getLastName());
        }
        Logger.info("Fecha de publicación: " + (libro.getDate() != null ? libro.getDate() : "N/A"));
        Logger.info("ISBN: " + libro.getISBN());
    }
    
    private void updateTitle(Libro libro) {
        System.out.print("Nuevo título (actual: " + libro.getTitle() + ") [Enter para mantener]: ");
        String newTitle = scanner.nextLine().trim();
        if (!newTitle.isEmpty()) {
            libro.setTitle(newTitle);
            Logger.info("Título actualizado");
        }
    }
    
    private void updateAuthor(Libro libro) {
        System.out.print("¿Cambiar autor? (s/N): ");
        String changeAuthor = scanner.nextLine().trim().toLowerCase();
        
        if (changeAuthor.equals("s") || changeAuthor.equals("si")) {
            List<Autor> autores = autorDAO.getAll();
            
            if (autores.isEmpty()) {
                Logger.warning("No hay autores disponibles");
                return;
            }
            
            System.out.println("Autores disponibles:");
            for (int i = 0; i < autores.size(); i++) {
                Autor autor = autores.get(i);
                System.out.printf("%d. %s %s (ID: %d)%n", 
                    i + 1, autor.getFirstName(), autor.getLastName(), autor.getId());
            }
            
            System.out.print("Seleccione nuevo autor (número): ");
            String selection = scanner.nextLine().trim();
            
            try {
                int index = Integer.parseInt(selection) - 1;
                if (index >= 0 && index < autores.size()) {
                    libro.setAuthor(autores.get(index));
                    Logger.info("Autor actualizado");
                } else {
                    Logger.error("Selección inválida");
                }
            } catch (NumberFormatException e) {
                Logger.error("Debe ingresar un número válido");
            }
        }
    }
    
    private void updatePublicationDate(Libro libro) {
        String currentDate = libro.getDate() != null ? libro.getDate().toString() : "N/A";
        System.out.print("Nueva fecha de publicación (actual: " + currentDate + ") [yyyy-MM-dd o Enter para mantener]: ");
        String newDateStr = scanner.nextLine().trim();
        
        if (!newDateStr.isEmpty()) {
            try {
                java.util.Date parsedDate = dateFormat.parse(newDateStr);
                libro.setDate(new Date(parsedDate.getTime()));
                Logger.info("Fecha de publicación actualizada");
            } catch (ParseException e) {
                Logger.error("Formato de fecha inválido. Use yyyy-MM-dd");
            }
        }
    }
    
    private void updateISBN(Libro libro) {
        System.out.print("Nuevo ISBN (actual: " + libro.getISBN() + ") [Enter para mantener]: ");
        String newISBN = scanner.nextLine().trim();
        
        if (!newISBN.isEmpty()) {
            if (!newISBN.matches("\\d{13}")) {
                Logger.error("El ISBN debe tener exactamente 13 dígitos");
                return;
            }
            
            // Check if ISBN already exists (but not for the current book)
            Libro existingBook = libroDAO.getByISBN(newISBN);
            if (existingBook != null && existingBook.getId() != libro.getId()) {
                Logger.error("Ya existe otro libro con ese ISBN: " + newISBN);
                return;
            }
            
            libro.setISBN(newISBN);
            Logger.info("ISBN actualizado");
        }
    }
}