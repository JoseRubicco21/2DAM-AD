package com.ad_ud2_at2.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ad_ud2_at2.dao.Dao;
import com.ad_ud2_at2.models.Cliente;
import com.ad_ud2_at2.services.connectors.MySQLConnector;
import com.ad_ud2_at2.services.connectors.exceptions.MySQLConnectorException;
import com.ad_ud2_at2.services.logger.Logger;

public class ClienteDAO implements Dao<Cliente, String> {

    @Override
    public Cliente get(String dni) {
        Cliente cliente = null;
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM clientes WHERE dni = ?");
            pss.setString(1, dni);
            ResultSet rs = pss.executeQuery();
            
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
            }
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in get(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in get(): " + e.getMessage());
        }
        return cliente;
    }

    @Override
    public List<Cliente> getAll() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM clientes");
            ResultSet rs = pss.executeQuery();
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                clientes.add(cliente);
            }
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in getAll(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getAll(): " + e.getMessage());
        }
        return clientes;
    }

    @Override
    public boolean save(Cliente objeto) {
        if (objeto == null || objeto.getDni() == null || objeto.getDni().trim().isEmpty()) {
            Logger.error("Cannot save cliente: DNI is null or empty");
            return false;
        }
        
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("INSERT INTO clientes (dni, nombre) VALUES (?, ?)");
            pss.setString(1, objeto.getDni());
            pss.setString(2, objeto.getNombre());
            
            int affectedRows = pss.executeUpdate();
            if (affectedRows > 0) {
                Logger.success("Cliente saved successfully: " + objeto.getDni());
                return true;
            }
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in save(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in save(): " + e.getMessage());
        }
        return false;
    }

    @Override
    public void update(Cliente obj) {
        if (obj == null || obj.getDni() == null || obj.getDni().trim().isEmpty()) {
            Logger.error("Cannot update cliente: DNI is null or empty");
            return;
        }
        
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("UPDATE clientes SET nombre = ? WHERE dni = ?");
            pss.setString(1, obj.getNombre());
            pss.setString(2, obj.getDni());
            
            int affectedRows = pss.executeUpdate();
            if (affectedRows > 0) {
                Logger.success("Cliente updated successfully: " + obj.getDni());
            } else {
                Logger.warning("No cliente found with DNI: " + obj.getDni());
            }
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in update(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in update(): " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Cliente obj) {
        if (obj == null || obj.getDni() == null || obj.getDni().trim().isEmpty()) {
            Logger.error("Cannot delete cliente: DNI is null or empty");
            return false;
        }
        return deleteById(obj.getDni());
    }

    @Override
    public boolean deleteById(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            Logger.error("Cannot delete cliente: DNI is null or empty");
            return false;
        }
        
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("DELETE FROM clientes WHERE dni = ?");
            pss.setString(1, dni);
            
            int affectedRows = pss.executeUpdate();
            if (affectedRows > 0) {
                Logger.success("Cliente deleted successfully: " + dni);
                return true;
            } else {
                Logger.warning("No cliente found with DNI: " + dni);
            }
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in deleteById(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in deleteById(): " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("DELETE FROM clientes");
            int affectedRows = pss.executeUpdate();
            Logger.warning("Deleted all clientes (" + affectedRows + " rows affected)");
            return true;
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in deleteAll(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in deleteAll(): " + e.getMessage());
        }
        return false;
    }

    // Additional custom methods for Cliente

    /**
     * Check if a cliente exists by DNI
     */
    public boolean exists(String dni) {
        return get(dni) != null;
    }

    /**
     * Search clientes by nombre (partial match)
     */
    public List<Cliente> searchByNombre(String searchTerm) {
        List<Cliente> clientes = new ArrayList<>();
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM clientes WHERE nombre LIKE ?");
            pss.setString(1, "%" + searchTerm + "%");
            ResultSet rs = pss.executeQuery();
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                clientes.add(cliente);
            }
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in searchByNombre(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in searchByNombre(): " + e.getMessage());
        }
        return clientes;
    }

    /**
     * Get the count of all clientes
     */
    public int count() {
        int count = 0;
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT COUNT(*) FROM clientes");
            ResultSet rs = pss.executeQuery();
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in count(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in count(): " + e.getMessage());
        }
        return count;
    }
}
