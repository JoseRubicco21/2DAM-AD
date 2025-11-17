package com.postgres.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.postgres.dao.Dao;
import com.postgres.models.Alumno;
import com.postgres.services.connectors.SQLConnector;
import com.postgres.services.connectors.exceptions.ConnectorException;
import com.postgres.services.logger.Logger;

public class AlumnoDao implements Dao<Alumno, Integer> {

    @Override
    public Alumno get(Integer id) {
        String query = "SELECT * FROM alumnos WHERE id = ?";
        
        try (Connection connection = SQLConnector.getConnectionToDatabaseAsRoot();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToAlumno(rs);
            }
            
        } catch (SQLException | ConnectorException e) {
            Logger.error("Error getting alumno with id " + id + ": " + e.getMessage());
        }
        
        return null;
    }

    @Override
    public List<Alumno> getAll() {
        List<Alumno> alumnos = new ArrayList<>();
        String query = "SELECT * FROM alumnos ORDER BY id";
        
        try (Connection connection = SQLConnector.getConnectionToDatabaseAsRoot();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                alumnos.add(mapResultSetToAlumno(rs));
            }
            
        } catch (SQLException | ConnectorException e) {
            Logger.error("Error getting all alumnos: " + e.getMessage());
        }
        
        return alumnos;
    }

    @Override
    public boolean save(Alumno objeto) {
        String query = "INSERT INTO alumnos (nombre, age, email) VALUES (?, ?, ?)";
        
        try (Connection connection = SQLConnector.getConnectionToDatabaseAsRoot();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setString(1, objeto.getNombre());
            stmt.setInt(2, objeto.getAge());
            stmt.setString(3, objeto.getEmail());
            
            int rowsAffected = stmt.executeUpdate();
            Logger.success("Alumno saved successfully");
            return rowsAffected > 0;
            
        } catch (SQLException | ConnectorException e) {
            Logger.error("Error saving alumno: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Alumno obj) {
        return deleteById(obj.getId());
    }

    @Override
    public boolean deleteAll() {
        String query = "DELETE FROM alumnos";
        
        try (Connection connection = SQLConnector.getConnectionToDatabaseAsRoot();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            int rowsAffected = stmt.executeUpdate();
            Logger.success("All alumnos deleted successfully. Rows affected: " + rowsAffected);
            return rowsAffected > 0;
            
        } catch (SQLException | ConnectorException e) {
            Logger.error("Error deleting all alumnos: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        String query = "DELETE FROM alumnos WHERE id = ?";
        
        try (Connection connection = SQLConnector.getConnectionToDatabaseAsRoot();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                Logger.success("Alumno with id " + id + " deleted successfully");
                return true;
            } else {
                Logger.warning("No alumno found with id: " + id);
                return false;
            }
            
        } catch (SQLException | ConnectorException e) {
            Logger.error("Error deleting alumno with id " + id + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public void update(Alumno obj) {
        String query = "UPDATE alumnos SET nombre = ?, age = ?, email = ? WHERE id = ?";
        
        try (Connection connection = SQLConnector.getConnectionToDatabaseAsRoot();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setString(1, obj.getNombre());
            stmt.setInt(2, obj.getAge());
            stmt.setString(3, obj.getEmail());
            stmt.setInt(4, obj.getId());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                Logger.success("Alumno updated successfully");
            } else {
                Logger.warning("No alumno found with id: " + obj.getId());
            }
            
        } catch (SQLException | ConnectorException e) {
            Logger.error("Error updating alumno: " + e.getMessage());
        }
    }

    /**
     * Additional utility method to check if an email already exists
     */
    public boolean emailExists(String email) {
        String query = "SELECT COUNT(*) FROM alumnos WHERE email = ?";
        
        try (Connection connection = SQLConnector.getConnectionToDatabaseAsRoot();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException | ConnectorException e) {
            Logger.error("Error checking email existence: " + e.getMessage());
        }
        
        return false;
    }

    /**
     * Additional utility method to get alumnos by age range
     */
    public List<Alumno> getByAgeRange(int minAge, int maxAge) {
        List<Alumno> alumnos = new ArrayList<>();
        String query = "SELECT * FROM alumnos WHERE age BETWEEN ? AND ? ORDER BY age, nombre";
        
        try (Connection connection = SQLConnector.getConnectionToDatabaseAsRoot();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setInt(1, minAge);
            stmt.setInt(2, maxAge);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                alumnos.add(mapResultSetToAlumno(rs));
            }
            
        } catch (SQLException | ConnectorException e) {
            Logger.error("Error getting alumnos by age range: " + e.getMessage());
        }
        
        return alumnos;
    }

    /**
     * Additional utility method to search alumnos by name (partial match)
     */
    public List<Alumno> searchByName(String namePattern) {
        List<Alumno> alumnos = new ArrayList<>();
        String query = "SELECT * FROM alumnos WHERE nombre LIKE ? ORDER BY nombre";
        
        try (Connection connection = SQLConnector.getConnectionToDatabaseAsRoot();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setString(1, "%" + namePattern + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                alumnos.add(mapResultSetToAlumno(rs));
            }
            
        } catch (SQLException | ConnectorException e) {
            Logger.error("Error searching alumnos by name: " + e.getMessage());
        }
        
        return alumnos;
    }

    /**
     * Maps a ResultSet row to an Alumno object
     */
    private Alumno mapResultSetToAlumno(ResultSet rs) throws SQLException {
        Alumno alumno = new Alumno();
        alumno.setId(rs.getInt("id"));
        alumno.setNombre(rs.getString("nombre"));
        alumno.setEmail(rs.getString("email"));
        alumno.setAge(rs.getInt("age"));
        return alumno;
    }
}
    