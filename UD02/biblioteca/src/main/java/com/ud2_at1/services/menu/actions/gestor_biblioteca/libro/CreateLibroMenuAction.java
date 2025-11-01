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

public class CreateLibroMenuAction extends MenuAction {

    private Scanner scanner;
    private LibroDAO libroDAO;
    private AutorDAO autorDAO;
    private SimpleDateFormat dateFormat;

    public CreateLibroMenuAction(Scanner scanner) {
        this.scanner = scanner;
        this.libroDAO = new LibroDAO();
        this.autorDAO = new AutorDAO();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== CREAR NUEVO LIBRO ===");
            
            // Get title
            System.out.print("Ingrese el título del libro: ");
            String title = scanner.nextLine().trim();
            
            if (title.isEmpty()) {
                Logger.error("El título no puede estar vacío");
                return MenuResult.CONTINUE;
            }
            
            // Select author
            Autor author = selectAuthor();
            if (author == null) {
                return MenuResult.CONTINUE;
            }
            
            // Get publication date
            Date publicationDate = getPublicationDate();
            if (publicationDate == null) {
                return MenuResult.CONTINUE;
            }
            
            // Get ISBN
            String isbn = getISBN();
            if (isbn == null) {
                return MenuResult.CONTINUE;
            }
            
            // Create new book
            Libro nuevoLibro = new Libro(title, author, publicationDate, isbn);
            
            if (libroDAO.save(nuevoLibro)) {
                Logger.success("Libro creado exitosamente con ID: " + nuevoLibro.getId());
                Logger.info("Título: " + title);
                Logger.info("Autor: " + author.getFirstName() + " " + author.getLastName());
                Logger.info("ISBN: " + isbn);
            } else {
                Logger.error("Error al crear el libro");
            }
            
        } catch (Exception e) {
            Logger.error("Error inesperado al crear libro: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
    
    private Autor selectAuthor() {
        System.out.println("\n=== SELECCIONAR AUTOR ===");
        System.out.println("1. Buscar autor existente");
        System.out.println("2. Crear nuevo autor");
        
        System.out.print("Seleccione una opción (1-2): ");
        String option = scanner.nextLine().trim();
        
        switch (option) {
            case "1":
                return findExistingAuthor();
            case "2":
                return createNewAuthor();
            default:
                Logger.error("Opción inválida");
                return null;
        }
    }
    
    private Autor findExistingAuthor() {
        List<Autor> autores = autorDAO.getAll();
        
        if (autores.isEmpty()) {
            Logger.warning("No hay autores registrados. Debe crear uno nuevo.");
            return createNewAuthor();
        }
        
        System.out.println("\nAutores disponibles:");
        for (int i = 0; i < autores.size(); i++) {
            Autor autor = autores.get(i);
            System.out.printf("%d. %s %s (ID: %d)%n", 
                i + 1, autor.getFirstName(), autor.getLastName(), autor.getId());
        }
        
        System.out.print("Seleccione un autor (número): ");
        String selection = scanner.nextLine().trim();
        
        try {
            int index = Integer.parseInt(selection) - 1;
            if (index >= 0 && index < autores.size()) {
                return autores.get(index);
            } else {
                Logger.error("Selección inválida");
                return null;
            }
        } catch (NumberFormatException e) {
            Logger.error("Debe ingresar un número válido");
            return null;
        }
    }
    
    private Autor createNewAuthor() {
        System.out.print("Nombre del autor: ");
        String firstName = scanner.nextLine().trim();
        
        if (firstName.isEmpty()) {
            Logger.error("El nombre no puede estar vacío");
            return null;
        }
        
        System.out.print("Apellido del autor: ");
        String lastName = scanner.nextLine().trim();
        
        if (lastName.isEmpty()) {
            Logger.error("El apellido no puede estar vacío");
            return null;
        }
        
        Autor nuevoAutor = new Autor(firstName, lastName);
        
        if (autorDAO.save(nuevoAutor)) {
            Logger.success("Nuevo autor creado: " + firstName + " " + lastName);
            return nuevoAutor;
        } else {
            Logger.error("Error al crear el autor");
            return null;
        }
    }
    
    private Date getPublicationDate() {
        System.out.print("Fecha de publicación (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine().trim();
        
        if (dateStr.isEmpty()) {
            Logger.error("La fecha no puede estar vacía");
            return null;
        }
        
        try {
            java.util.Date parsedDate = dateFormat.parse(dateStr);
            return new Date(parsedDate.getTime());
        } catch (ParseException e) {
            Logger.error("Formato de fecha inválido. Use yyyy-MM-dd (ej: 2023-12-25)");
            return null;
        }
    }
    
    private String getISBN() {
        System.out.print("ISBN (13 dígitos): ");
        String isbn = scanner.nextLine().trim();
        
        if (isbn.isEmpty()) {
            Logger.error("El ISBN no puede estar vacío");
            return null;
        }
        
        if (!isbn.matches("\\d{13}")) {
            Logger.error("El ISBN debe tener exactamente 13 dígitos");
            return null;
        }
        
        // Check if ISBN already exists
        if (libroDAO.existsByISBN(isbn)) {
            Logger.error("Ya existe un libro con ese ISBN: " + isbn);
            return null;
        }
        
        return isbn;
    }
}