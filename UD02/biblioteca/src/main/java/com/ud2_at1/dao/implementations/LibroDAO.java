package com.ud2_at1.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ud2_at1.dao.Dao;
import com.ud2_at1.dao.implementations.AutorDAO;
import com.ud2_at1.models.Autor;
import com.ud2_at1.models.Libro;
import com.ud2_at1.services.connectors.MySQLConnector;
import com.ud2_at1.services.connectors.exceptions.MySQLConnectorException;
import com.ud2_at1.services.logger.Logger;

public class LibroDAO implements Dao<Libro, Integer> {
    
    private AutorDAO autorDAO = new AutorDAO();

    @Override
    public Libro get(Integer id) {
        MySQLConnector connector;
        Libro libro = null;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement(
                "SELECT * FROM libros WHERE id = ?"
            );
            pss.setInt(1, id);
            ResultSet rs = pss.executeQuery();
            if (rs.next()) {
                // Get the autor object
                Autor autor = autorDAO.get(rs.getInt("author_id"));
                
                libro = new Libro(
                    rs.getString("title"),
                    autor,
                    rs.getDate("publication_date"),
                    rs.getString("isbn")
                );
                libro.setId(rs.getInt("id"));
            }
            Logger.info("Retrieved libro with ID: " + id);
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in get(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in get(): " + e.getMessage());
        }
        return libro;
    }

    @Override
    public List<Libro> getAll() {
        List<Libro> libros = new ArrayList<>();
        MySQLConnector connector;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM libros");
            ResultSet rs = pss.executeQuery();
            while (rs.next()) {
                // Get the autor object
                Autor autor = autorDAO.get(rs.getInt("author_id"));
                
                Libro libro = new Libro(
                    rs.getString("title"),
                    autor,
                    rs.getDate("publication_date"),
                    rs.getString("isbn")
                );
                libro.setId(rs.getInt("id"));
                libros.add(libro);
            }
            Logger.info("Retrieved " + libros.size() + " libros");
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in getAll(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getAll(): " + e.getMessage());
        }
        return libros;
    }

    @Override
    public boolean save(Libro objeto) {
        if (objeto == null) {
            Logger.error("Cannot save null libro object");
            return false;
        }
        
        MySQLConnector connector;
        boolean success = false;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            
            // If ID is 0 or negative, use auto-increment
            if (objeto.getId() <= 0) {
                PreparedStatement pss = connection.prepareStatement(
                    "INSERT INTO libros (title, author_id, publication_date, isbn) VALUES (?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
                );
                pss.setString(1, objeto.getTitle());
                pss.setInt(2, objeto.getAuthor().getId());
                pss.setDate(3, objeto.getDate());
                pss.setString(4, objeto.getISBN());
                
                int affectedRows = pss.executeUpdate();
                if (affectedRows > 0) {
                    // Get the generated ID
                    ResultSet generatedKeys = pss.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        objeto.setId(generatedKeys.getInt(1));
                    }
                    success = true;
                    Logger.success("Libro saved with generated ID: " + objeto.getId());
                }
            } else {
                // Use provided ID
                PreparedStatement pss = connection.prepareStatement(
                    "INSERT INTO libros (id, title, author_id, publication_date, isbn) VALUES (?, ?, ?, ?, ?)"
                );
                pss.setInt(1, objeto.getId());
                pss.setString(2, objeto.getTitle());
                pss.setInt(3, objeto.getAuthor().getId());
                pss.setDate(4, objeto.getDate());
                pss.setString(5, objeto.getISBN());
                
                int affectedRows = pss.executeUpdate();
                success = affectedRows > 0;
                if (success) {
                    Logger.success("Libro saved with ID: " + objeto.getId());
                }
            }
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in save(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in save(): " + e.getMessage());
            if (e.getMessage().contains("Duplicate entry")) {
                Logger.error("Duplicate ISBN detected: " + objeto.getISBN());
            }
            success = false;
        }
        return success;
    }

    @Override
    public boolean delete(Libro obj) {
        if (obj == null || obj.getId() <= 0) {
            Logger.error("Cannot delete libro: invalid object or ID");
            return false;
        }
        return deleteById(obj.getId());
    }

    @Override
    public boolean deleteAll() {
        MySQLConnector connector;
        boolean success = false;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("DELETE FROM libros");
            int affectedRows = pss.executeUpdate();
            success = true;
            Logger.warning("Deleted all libros (" + affectedRows + " rows affected)");
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in deleteAll(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in deleteAll(): " + e.getMessage());
        }
        return success;
    }

    @Override
    public boolean deleteById(Integer id) {
        MySQLConnector connector;
        boolean success = false;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("DELETE FROM libros WHERE id = ?");
            pss.setInt(1, id);
            int affectedRows = pss.executeUpdate();
            success = affectedRows > 0;
            if (success) {
                Logger.success("Libro deleted with ID: " + id);
            } else {
                Logger.warning("No libro found with ID: " + id);
            }
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in deleteById(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in deleteById(): " + e.getMessage());
        }
        return success;
    }

    @Override
    public void update(Libro obj) {
        if (obj == null || obj.getId() <= 0) {
            Logger.error("Cannot update libro: invalid object or ID");
            return;
        }
        
        MySQLConnector connector;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement(
                "UPDATE libros SET title = ?, author_id = ?, publication_date = ?, isbn = ? WHERE id = ?"
            );
            pss.setString(1, obj.getTitle());
            pss.setInt(2, obj.getAuthor().getId());
            pss.setDate(3, obj.getDate());
            pss.setString(4, obj.getISBN());
            pss.setInt(5, obj.getId());
            
            int affectedRows = pss.executeUpdate();
            if (affectedRows > 0) {
                Logger.success("Libro updated with ID: " + obj.getId());
            } else {
                Logger.warning("No libro found with ID: " + obj.getId() + " to update");
            }
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in update(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in update(): " + e.getMessage());
            if (e.getMessage().contains("Duplicate entry")) {
                Logger.error("Duplicate ISBN detected: " + obj.getISBN());
            }
        }
    }

    // Additional custom methods for Libro

    /**
     * Get libro by ISBN
     */
    public Libro getByISBN(String isbn) {
        MySQLConnector connector;
        Libro libro = null;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM libros WHERE isbn = ?");
            pss.setString(1, isbn);
            ResultSet rs = pss.executeQuery();
            if (rs.next()) {
                Autor autor = autorDAO.get(rs.getInt("author_id"));
                
                libro = new Libro(
                    rs.getString("title"),
                    autor,
                    rs.getDate("publication_date"),
                    rs.getString("isbn")
                );
                libro.setId(rs.getInt("id"));
            }
            Logger.info("Retrieved libro by ISBN: " + isbn);
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in getByISBN(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getByISBN(): " + e.getMessage());
        }
        return libro;
    }

    /**
     * Get libros by title
     */
    public List<Libro> getByTitle(String title) {
        List<Libro> libros = new ArrayList<>();
        MySQLConnector connector;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM libros WHERE title = ?");
            pss.setString(1, title);
            ResultSet rs = pss.executeQuery();
            while (rs.next()) {
                Autor autor = autorDAO.get(rs.getInt("author_id"));
                
                Libro libro = new Libro(
                    rs.getString("title"),
                    autor,
                    rs.getDate("publication_date"),
                    rs.getString("isbn")
                );
                libro.setId(rs.getInt("id"));
                libros.add(libro);
            }
            Logger.info("Retrieved " + libros.size() + " libros with title: " + title);
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in getByTitle(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getByTitle(): " + e.getMessage());
        }
        return libros;
    }

    /**
     * Get libros by author
     */
    public List<Libro> getByAuthor(Autor author) {
        if (author == null || author.getId() <= 0) {
            Logger.error("Invalid author provided");
            return new ArrayList<>();
        }
        return getByAuthorId(author.getId());
    }

    /**
     * Get libros by author ID
     */
    public List<Libro> getByAuthorId(Integer authorId) {
        List<Libro> libros = new ArrayList<>();
        MySQLConnector connector;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM libros WHERE author_id = ?");
            pss.setInt(1, authorId);
            ResultSet rs = pss.executeQuery();
            while (rs.next()) {
                Autor autor = autorDAO.get(rs.getInt("author_id"));
                
                Libro libro = new Libro(
                    rs.getString("title"),
                    autor,
                    rs.getDate("publication_date"),
                    rs.getString("isbn")
                );
                libro.setId(rs.getInt("id"));
                libros.add(libro);
            }
            Logger.info("Retrieved " + libros.size() + " libros by author ID: " + authorId);
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in getByAuthorId(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getByAuthorId(): " + e.getMessage());
        }
        return libros;
    }

    /**
     * Search libros by title (partial match)
     */
    public List<Libro> searchByTitle(String searchTerm) {
        List<Libro> libros = new ArrayList<>();
        MySQLConnector connector;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM libros WHERE title LIKE ?");
            pss.setString(1, "%" + searchTerm + "%");
            ResultSet rs = pss.executeQuery();
            while (rs.next()) {
                Autor autor = autorDAO.get(rs.getInt("author_id"));
                
                Libro libro = new Libro(
                    rs.getString("title"),
                    autor,
                    rs.getDate("publication_date"),
                    rs.getString("isbn")
                );
                libro.setId(rs.getInt("id"));
                libros.add(libro);
            }
            Logger.info("Found " + libros.size() + " libros matching title: " + searchTerm);
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in searchByTitle(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in searchByTitle(): " + e.getMessage());
        }
        return libros;
    }

    /**
     * Get libros by publication year
     */
    public List<Libro> getByPublicationYear(int year) {
        List<Libro> libros = new ArrayList<>();
        MySQLConnector connector;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement(
                "SELECT * FROM libros WHERE YEAR(publication_date) = ?"
            );
            pss.setInt(1, year);
            ResultSet rs = pss.executeQuery();
            while (rs.next()) {
                Autor autor = autorDAO.get(rs.getInt("author_id"));
                
                Libro libro = new Libro(
                    rs.getString("title"),
                    autor,
                    rs.getDate("publication_date"),
                    rs.getString("isbn")
                );
                libro.setId(rs.getInt("id"));
                libros.add(libro);
            }
            Logger.info("Retrieved " + libros.size() + " libros from year: " + year);
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in getByPublicationYear(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getByPublicationYear(): " + e.getMessage());
        }
        return libros;
    }

    /**
     * Check if a libro exists by ID
     */
    public boolean exists(Integer id) {
        return get(id) != null;
    }

    /**
     * Check if a libro exists by ISBN
     */
    public boolean existsByISBN(String isbn) {
        return getByISBN(isbn) != null;
    }

    /**
     * Get the count of all libros
     */
    public int count() {
        int count = 0;
        MySQLConnector connector;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT COUNT(*) FROM libros");
            ResultSet rs = pss.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            Logger.info("Total libros count: " + count);
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in count(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in count(): " + e.getMessage());
        }
        return count;
    }

    /**
     * Get count of libros by author
     */
    public int countByAuthor(Autor author) {
        if (author == null || author.getId() <= 0) {
            return 0;
        }
        return getByAuthor(author).size();
    }
}
