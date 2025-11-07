package com.ad_ud2_at2.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ad_ud2_at2.dao.Dao;
import com.ad_ud2_at2.models.Cliente;
import com.ad_ud2_at2.services.connectors.GenericConnector;
import com.ad_ud2_at2.services.connectors.SQLConnector;
import com.ad_ud2_at2.services.connectors.exceptions.ConnectorException;
import com.ad_ud2_at2.services.logger.Logger;

/**
 * Implementación del patrón DAO para la entidad Cliente.
 * Proporciona operaciones CRUD (Create, Read, Update, Delete) para gestionar
 * clientes en la base de datos MySQL.
 * 
 * Esta clase implementa la interfaz Dao<Cliente, String> donde:
 * - Cliente es el tipo de entidad gestionada
 * - String es el tipo de la clave primaria (DNI)
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 */
public class ClienteDAO implements Dao<Cliente, String> {

    /**
     * Obtiene un cliente específico por su DNI.
     * 
     * @param dni El DNI del cliente a buscar (clave primaria)
     * @return El objeto Cliente encontrado, o null si no existe
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    @Override
    public Cliente get(String dni) {
        Cliente cliente = null;
        try {
            GenericConnector connector = new SQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM clientes WHERE dni = ?");
            pss.setString(1, dni);
            ResultSet rs = pss.executeQuery();
            
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
            }
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in get(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in get(): " + e.getMessage());
        }
        return cliente;
    }

    /**
     * Obtiene todos los clientes de la base de datos.
     * 
     * @return Lista con todos los clientes existentes. Lista vacía si no hay clientes
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    @Override
    public List<Cliente> getAll() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            GenericConnector connector = new SQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM clientes");
            ResultSet rs = pss.executeQuery();
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                clientes.add(cliente);
            }
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in getAll(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getAll(): " + e.getMessage());
        }
        return clientes;
    }

    /**
     * Guarda un nuevo cliente en la base de datos.
     * 
     * @param objeto El cliente a guardar. No debe ser null y debe tener DNI válido
     * @return true si el cliente se guardó correctamente, false en caso contrario
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL o el DNI ya existe
     */
    @Override
    public boolean save(Cliente objeto) {
        if (objeto == null || objeto.getDni() == null || objeto.getDni().trim().isEmpty()) {
            Logger.error("Cannot save cliente: DNI is null or empty");
            return false;
        }
        
        try {
            GenericConnector connector = new SQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("INSERT INTO clientes (dni, nombre) VALUES (?, ?)");
            pss.setString(1, objeto.getDni());
            pss.setString(2, objeto.getNombre());
            
            int affectedRows = pss.executeUpdate();
            if (affectedRows > 0) {
                Logger.success("Cliente saved successfully: " + objeto.getDni());
                return true;
            }
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in save(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in save(): " + e.getMessage());
        }
        return false;
    }

    /**
     * Actualiza los datos de un cliente existente en la base de datos.
     * Solo actualiza el campo 'nombre', ya que el DNI es la clave primaria.
     * 
     * @param obj El cliente con los datos actualizados. Debe tener DNI válido
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    @Override
    public void update(Cliente obj) {
        if (obj == null || obj.getDni() == null || obj.getDni().trim().isEmpty()) {
            Logger.error("Cannot update cliente: DNI is null or empty");
            return;
        }
        
        try {
            GenericConnector connector = new SQLConnector();
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
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in update(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in update(): " + e.getMessage());
        }
    }

    /**
     * Elimina un cliente de la base de datos.
     * Utiliza internamente el método deleteById().
     * 
     * @param obj El cliente a eliminar. Debe tener DNI válido
     * @return true si el cliente se eliminó correctamente, false en caso contrario
     */
    @Override
    public boolean delete(Cliente obj) {
        if (obj == null || obj.getDni() == null || obj.getDni().trim().isEmpty()) {
            Logger.error("Cannot delete cliente: DNI is null or empty");
            return false;
        }
        return deleteById(obj.getDni());
    }

    /**
     * Elimina un cliente de la base de datos por su DNI.
     * 
     * @param dni El DNI del cliente a eliminar
     * @return true si el cliente se eliminó correctamente, false si no se encontró o hubo error
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    @Override
    public boolean deleteById(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            Logger.error("Cannot delete cliente: DNI is null or empty");
            return false;
        }
        
        try {
            GenericConnector connector = new SQLConnector();
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
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in deleteById(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in deleteById(): " + e.getMessage());
        }
        return false;
    }

    /**
     * Elimina todos los clientes de la base de datos.
     * ¡PRECAUCIÓN! Esta operación es irreversible.
     * 
     * @return true si la operación se completó correctamente, false en caso de error
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    @Override
    public boolean deleteAll() {
        try {
            GenericConnector connector = new SQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("DELETE FROM clientes");
            int affectedRows = pss.executeUpdate();
            Logger.warning("Deleted all clientes (" + affectedRows + " rows affected)");
            return true;
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in deleteAll(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in deleteAll(): " + e.getMessage());
        }
        return false;
    }

    // Additional custom methods for Cliente

    /**
     * Verifica si existe un cliente con el DNI especificado.
     * 
     * @param dni El DNI a verificar
     * @return true si existe un cliente con ese DNI, false en caso contrario
     */
    public boolean exists(String dni) {
        return get(dni) != null;
    }

    /**
     * Busca clientes por nombre utilizando coincidencia parcial.
     * La búsqueda es case-sensitive y utiliza el operador LIKE.
     * 
     * @param searchTerm El término de búsqueda para el nombre
     * @return Lista de clientes que contienen el término en su nombre
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    public List<Cliente> searchByNombre(String searchTerm) {
        List<Cliente> clientes = new ArrayList<>();
        try {
            GenericConnector connector = new SQLConnector();
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
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in searchByNombre(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in searchByNombre(): " + e.getMessage());
        }
        return clientes;
    }

    /**
     * Obtiene el número total de clientes en la base de datos.
     * 
     * @return El número total de clientes, 0 si no hay clientes o en caso de error
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    public int count() {
        int count = 0;
        try {
            GenericConnector connector = new SQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT COUNT(*) FROM clientes");
            ResultSet rs = pss.executeQuery();
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in count(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in count(): " + e.getMessage());
        }
        return count;
    }
}
