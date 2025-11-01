package com.ud2_at1.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ud2_at1.services.logger.Logger;

import com.ud2_at1.dao.Dao;
import com.ud2_at1.models.Autor;
import com.ud2_at1.services.connectors.MySQLConnector;
import com.ud2_at1.services.connectors.exceptions.MySQLConnectorException;

public class AutorDAO implements Dao<Autor, Integer> {

    @Override
    public Autor get(Integer id) {
        MySQLConnector connector;
        Autor autor = null;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM autores WHERE id = ?");
            pss.setInt(1, id);
            ResultSet rs = pss.executeQuery();
            if (rs.next()) {
                autor = new Autor(
                    rs.getString("first_name"),
                    rs.getString("last_name")
                );
                autor.setId(rs.getInt("id"));
            }
            Logger.info("Retrieved autor with ID: " + id);
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in get(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in get(): " + e.getMessage());
        }

        return autor;
    }

    @Override
    public List<Autor> getAll() {
        List<Autor> autores = new ArrayList<Autor>();
        MySQLConnector connector;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM autores");
            ResultSet rs = pss.executeQuery();
            while (rs.next()) {
                Autor autor = new Autor(
                    rs.getString("first_name"),
                    rs.getString("last_name")
                );
                autor.setId(rs.getInt("id"));
                autores.add(autor);
            }
            Logger.info("Retrieved " + autores.size() + " autores");
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in getAll(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getAll(): " + e.getMessage());
        }

        return autores;
    }

    @Override
    public boolean save(Autor objeto) {
        MySQLConnector connector;
        boolean success = false;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            
            // If ID is 0 or negative, use auto-increment (don't include ID in INSERT)
            if (objeto.getId() <= 0) {
                PreparedStatement pss = connection.prepareStatement(
                    "INSERT INTO autores (first_name, last_name) VALUES (?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
                );
                pss.setString(1, objeto.getFirstName());
                pss.setString(2, objeto.getLastName());
                
                int affectedRows = pss.executeUpdate();
                if (affectedRows > 0) {
                    // Get the generated ID
                    ResultSet generatedKeys = pss.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        objeto.setId(generatedKeys.getInt(1));
                    }
                    success = true;
                    Logger.success("Autor saved with generated ID: " + objeto.getId());
                }
            } else {
                // Use provided ID
                PreparedStatement pss = connection.prepareStatement(
                    "INSERT INTO autores (id, first_name, last_name) VALUES (?, ?, ?)"
                );
                pss.setInt(1, objeto.getId());
                pss.setString(2, objeto.getFirstName());
                pss.setString(3, objeto.getLastName());
                
                int affectedRows = pss.executeUpdate();
                success = affectedRows > 0;
                if (success) {
                    Logger.success("Autor saved with ID: " + objeto.getId());
                }
            }
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in save(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in save(): " + e.getMessage());
            success = false;
        }
        return success;
    }

    @Override
    public boolean delete(Autor obj) {
        if (obj == null || obj.getId() <= 0) {
            Logger.error("Cannot delete autor: invalid object or ID");
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
            PreparedStatement pss = connection.prepareStatement("DELETE FROM autores");
            int affectedRows = pss.executeUpdate();
            success = true;
            Logger.warning("Deleted all autores (" + affectedRows + " rows affected)");
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
            PreparedStatement pss = connection.prepareStatement("DELETE FROM autores WHERE id = ?");
            pss.setInt(1, id);
            int affectedRows = pss.executeUpdate();
            success = affectedRows > 0;
            if (success) {
                Logger.success("Autor deleted with ID: " + id);
            } else {
                Logger.warning("No autor found with ID: " + id);
            }
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in deleteById(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in deleteById(): " + e.getMessage());
        }
        return success;
    }

    @Override
    public void update(Autor obj) {
        if (obj == null || obj.getId() <= 0) {
            Logger.error("Cannot update autor: invalid object or ID");
            return;
        }
        
        MySQLConnector connector;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement(
                "UPDATE autores SET first_name = ?, last_name = ? WHERE id = ?"
            );
            pss.setString(1, obj.getFirstName());
            pss.setString(2, obj.getLastName());
            pss.setInt(3, obj.getId());
            
            int affectedRows = pss.executeUpdate();
            if (affectedRows > 0) {
                Logger.success("Autor updated with ID: " + obj.getId());
            } else {
                Logger.warning("No autor found with ID: " + obj.getId() + " to update");
            }
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in update(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in update(): " + e.getMessage());
        }
    }

    // Additional custom methods
    public Autor getByFirstName(String name) {
        MySQLConnector connector;
        Autor autor = null;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM autores WHERE first_name = ?");
            pss.setString(1, name);
            ResultSet rs = pss.executeQuery();
            if (rs.next()) {
                autor = new Autor(
                    rs.getString("first_name"),
                    rs.getString("last_name")
                );
                autor.setId(rs.getInt("id"));
            }
            Logger.info("Retrieved autor by first name: " + name);
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in getByFirstName(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getByFirstName(): " + e.getMessage());
        }

        return autor;
    }

    /**
     * Get autor by last name
     */
    public Autor getByLastName(String lastName) {
        MySQLConnector connector;
        Autor autor = null;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM autores WHERE last_name = ?");
            pss.setString(1, lastName);
            ResultSet rs = pss.executeQuery();
            if (rs.next()) {
                autor = new Autor(
                    rs.getString("first_name"),
                    rs.getString("last_name")
                );
                autor.setId(rs.getInt("id"));
            }
            Logger.info("Retrieved autor by last name: " + lastName);
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in getByLastName(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getByLastName(): " + e.getMessage());
        }

        return autor;
    }

    /**
     * Get autores by full name (first + last)
     */
    public Autor getByFullName(String firstName, String lastName) {
        MySQLConnector connector;
        Autor autor = null;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement(
                "SELECT * FROM autores WHERE first_name = ? AND last_name = ?"
            );
            pss.setString(1, firstName);
            pss.setString(2, lastName);
            ResultSet rs = pss.executeQuery();
            if (rs.next()) {
                autor = new Autor(
                    rs.getString("first_name"),
                    rs.getString("last_name")
                );
                autor.setId(rs.getInt("id"));
            }
            Logger.info("Retrieved autor by full name: " + firstName + " " + lastName);
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in getByFullName(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getByFullName(): " + e.getMessage());
        }

        return autor;
    }

    /**
     * Search autores by partial name match (useful for autocomplete)
     */
    public List<Autor> searchByName(String searchTerm) {
        List<Autor> autores = new ArrayList<>();
        MySQLConnector connector;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement(
                "SELECT * FROM autores WHERE first_name LIKE ? OR last_name LIKE ?"
            );
            String searchPattern = "%" + searchTerm + "%";
            pss.setString(1, searchPattern);
            pss.setString(2, searchPattern);
            ResultSet rs = pss.executeQuery();
            
            while (rs.next()) {
                Autor autor = new Autor(
                    rs.getString("first_name"),
                    rs.getString("last_name")
                );
                autor.setId(rs.getInt("id"));
                autores.add(autor);
            }
            Logger.info("Found " + autores.size() + " autores matching: " + searchTerm);
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in searchByName(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in searchByName(): " + e.getMessage());
        }

        return autores;
    }

    /**
     * Check if an autor exists by ID
     */
    public boolean exists(Integer id) {
        return get(id) != null;
    }

    /**
     * Get the count of all autores
     */
    public int count() {
        int count = 0;
        MySQLConnector connector;
        try {
            connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT COUNT(*) FROM autores");
            ResultSet rs = pss.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            Logger.info("Total autores count: " + count);
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in count(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in count(): " + e.getMessage());
        }
        return count;
    }
}
