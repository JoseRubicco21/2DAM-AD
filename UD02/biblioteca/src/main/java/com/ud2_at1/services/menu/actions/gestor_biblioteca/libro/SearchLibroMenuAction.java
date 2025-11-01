package com.ud2_at1.services.menu.actions.gestor_biblioteca.libro;

import java.util.List;
import java.util.Scanner;

import com.ud2_at1.dao.implementations.AutorDAO;
import com.ud2_at1.dao.implementations.LibroDAO;
import com.ud2_at1.models.Autor;
import com.ud2_at1.models.Libro;
import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.state.MenuResult;

public class SearchLibroMenuAction extends MenuAction {

    private Scanner scanner;
    private LibroDAO libroDAO;
    private AutorDAO autorDAO;

    public SearchLibroMenuAction(Scanner scanner) {
        this.scanner = scanner;
        this.libroDAO = new LibroDAO();
        this.autorDAO = new AutorDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== BUSCAR LIBRO ===");
            
            System.out.println("Opciones de búsqueda:");
            System.out.println("1. Buscar por ID");
            System.out.println("2. Buscar por ISBN");
            System.out.println("3. Buscar por título");
            System.out.println("4. Buscar por autor");
            System.out.println("5. Buscar por año de publicación");
            System.out.println("6. Búsqueda parcial por título");
            
            System.out.print("Seleccione una opción (1-6): ");
            String option = scanner.nextLine().trim();
            
            switch (option) {
                case "1":
                    searchById();
                    break;
                case "2":
                    searchByISBN();
                    break;
                case "3":
                    searchByTitle();
                    break;
                case "4":
                    searchByAuthor();
                    break;
                case "5":
                    searchByYear();
                    break;
                case "6":
                    searchPartialTitle();
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
        System.out.print("Ingrese el ID del libro: ");
        String idStr = scanner.nextLine().trim();
        
        if (!idStr.matches("\\d+")) {
            Logger.error("ID inválido. Debe ser un número entero");
            return;
        }
        
        int id = Integer.parseInt(idStr);
        Libro libro = libroDAO.get(id);
        
        if (libro != null) {
            displayLibro(libro);
        } else {
            Logger.warning("No se encontró un libro con ID: " + id);
        }
    }
    
    private void searchByISBN() {
        System.out.print("Ingrese el ISBN: ");
        String isbn = scanner.nextLine().trim();
        
        if (isbn.isEmpty()) {
            Logger.error("El ISBN no puede estar vacío");
            return;
        }
        
        Libro libro = libroDAO.getByISBN(isbn);
        
        if (libro != null) {
            displayLibro(libro);
        } else {
            Logger.warning("No se encontró un libro con ISBN: " + isbn);
        }
    }
    
    private void searchByTitle() {
        System.out.print("Ingrese el título exacto: ");
        String title = scanner.nextLine().trim();
        
        if (title.isEmpty()) {
            Logger.error("El título no puede estar vacío");
            return;
        }
        
        List<Libro> libros = libroDAO.getByTitle(title);
        
        if (libros.isEmpty()) {
            Logger.warning("No se encontraron libros con el título: " + title);
        } else {
            Logger.info("Libros encontrados:");
            for (Libro libro : libros) {
                displayLibro(libro);
                System.out.println("---");
            }
        }
    }
    
    private void searchByAuthor() {
        List<Autor> autores = autorDAO.getAll();
        
        if (autores.isEmpty()) {
            Logger.warning("No hay autores registrados");
            return;
        }
        
        System.out.println("Autores disponibles:");
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
                Autor selectedAuthor = autores.get(index);
                List<Libro> libros = libroDAO.getByAuthor(selectedAuthor);
                
                if (libros.isEmpty()) {
                    Logger.warning("No se encontraron libros de " + selectedAuthor.getFirstName() + " " + selectedAuthor.getLastName());
                } else {
                    Logger.info("Libros de " + selectedAuthor.getFirstName() + " " + selectedAuthor.getLastName() + ":");
                    for (Libro libro : libros) {
                        displayLibro(libro);
                        System.out.println("---");
                    }
                }
            } else {
                Logger.error("Selección inválida");
            }
        } catch (NumberFormatException e) {
            Logger.error("Debe ingresar un número válido");
        }
    }
    
    private void searchByYear() {
        System.out.print("Ingrese el año de publicación: ");
        String yearStr = scanner.nextLine().trim();
        
        if (!yearStr.matches("\\d{4}")) {
            Logger.error("Año inválido. Debe ser un número de 4 dígitos");
            return;
        }
        
        int year = Integer.parseInt(yearStr);
        List<Libro> libros = libroDAO.getByPublicationYear(year);
        
        if (libros.isEmpty()) {
            Logger.warning("No se encontraron libros publicados en el año: " + year);
        } else {
            Logger.info("Libros publicados en " + year + ":");
            for (Libro libro : libros) {
                displayLibro(libro);
                System.out.println("---");
            }
        }
    }
    
    private void searchPartialTitle() {
        System.out.print("Ingrese término de búsqueda en el título: ");
        String searchTerm = scanner.nextLine().trim();
        
        if (searchTerm.isEmpty()) {
            Logger.error("El término de búsqueda no puede estar vacío");
            return;
        }
        
        List<Libro> libros = libroDAO.searchByTitle(searchTerm);
        
        if (libros.isEmpty()) {
            Logger.warning("No se encontraron libros que coincidan con: " + searchTerm);
        } else {
            Logger.info("Libros encontrados:");
            for (Libro libro : libros) {
                displayLibro(libro);
                System.out.println("---");
            }
        }
    }
    
    private void displayLibro(Libro libro) {
        Logger.info("=== INFORMACIÓN DEL LIBRO ===");
        Logger.info("ID: " + libro.getId());
        Logger.info("Título: " + libro.getTitle());
        if (libro.getAuthor() != null) {
            Logger.info("Autor: " + libro.getAuthor().getFirstName() + " " + libro.getAuthor().getLastName());
        }
        Logger.info("Fecha de publicación: " + (libro.getDate() != null ? libro.getDate() : "N/A"));
        Logger.info("ISBN: " + libro.getISBN());
    }
}