package com.ad_ud2_at2.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ad_ud2_at2.dao.Dao;
import com.ad_ud2_at2.models.Pedido;
import com.ad_ud2_at2.models.Cliente;
import com.ad_ud2_at2.models.Producto;
import com.ad_ud2_at2.services.connectors.GenericConnector;
import com.ad_ud2_at2.services.connectors.SQLConnector;
import com.ad_ud2_at2.services.connectors.exceptions.ConnectorException;
import com.ad_ud2_at2.services.logger.Logger;

/**
 * Implementación del patrón DAO para la entidad Pedido.
 * Proporciona operaciones CRUD (Create, Read, Update, Delete) para gestionar
 * pedidos en la base de datos MySQL, incluyendo relaciones con Cliente y Producto.
 * 
 * Esta clase implementa la interfaz Dao<Pedido, Integer> donde:
 * - Pedido es el tipo de entidad gestionada
 * - Integer es el tipo de la clave primaria (ID)
 * 
 * Los pedidos mantienen relaciones con:
 * - Cliente (mediante DNI como clave foránea)
 * - Producto (mediante ID como clave foránea)
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 */
public class PedidoDAO implements Dao<Pedido, Integer> {

    /**
     * DAO para gestionar operaciones con clientes relacionados
     */
    private ClienteDAO clienteDAO = new ClienteDAO();
    
    /**
     * DAO para gestionar operaciones con productos relacionados
     */
    private ProductoDAO productoDAO = new ProductoDAO();

    /**
     * Obtiene un pedido específico por su ID, incluyendo las entidades relacionadas.
     * Carga automáticamente el cliente y producto asociados al pedido.
     * 
     * @param id El ID del pedido a buscar (clave primaria)
     * @return El objeto Pedido encontrado con sus relaciones cargadas, o null si no existe
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    @Override
    public Pedido get(Integer id) {
        Pedido pedido = null;
        try {
            GenericConnector connector = new SQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM pedidos WHERE id = ?");
            pss.setInt(1, id);
            ResultSet rs = pss.executeQuery();
            
            if (rs.next()) {
                pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setCantidad(rs.getInt("cantidad"));
                
                // Load related entities
                int productoId = rs.getInt("producto_id");
                String clienteDni = rs.getString("cliente_dni");
                
                Producto producto = productoDAO.get(productoId);
                Cliente cliente = clienteDAO.get(clienteDni);
                
                pedido.setProducto(producto);
                pedido.setCliente(cliente);
            }
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in get(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in get(): " + e.getMessage());
        }
        return pedido;
    }

    /**
     * Obtiene todos los pedidos de la base de datos, incluyendo las entidades relacionadas.
     * Carga automáticamente los clientes y productos asociados a cada pedido.
     * 
     * @return Lista con todos los pedidos existentes con sus relaciones cargadas. Lista vacía si no hay pedidos
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    @Override
    public List<Pedido> getAll() {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            GenericConnector connector = new SQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM pedidos");
            ResultSet rs = pss.executeQuery();
            
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setCantidad(rs.getInt("cantidad"));
                
                // Load related entities
                int productoId = rs.getInt("producto_id");
                String clienteDni = rs.getString("cliente_dni");
                
                Producto producto = productoDAO.get(productoId);
                Cliente cliente = clienteDAO.get(clienteDni);
                
                pedido.setProducto(producto);
                pedido.setCliente(cliente);
                pedidos.add(pedido);
            }
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in getAll(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getAll(): " + e.getMessage());
        }
        return pedidos;
    }

    /**
     * Guarda un nuevo pedido en la base de datos.
     * Requiere que el pedido tenga un cliente y producto válidos asociados.
     * 
     * @param objeto El pedido a guardar. No debe ser null y debe tener cliente y producto válidos
     * @return true si el pedido se guardó correctamente, false en caso contrario
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL o violación de claves foráneas
     */
    @Override
    public boolean save(Pedido objeto) {
        if (objeto == null || objeto.getProducto() == null || objeto.getCliente() == null) {
            Logger.error("Cannot save pedido: object, producto, or cliente is null");
            return false;
        }
        
        try {
            GenericConnector connector = new SQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("INSERT INTO pedidos (fecha, cantidad, producto_id, cliente_dni) VALUES (?, ?, ?, ?)");
            pss.setDate(1, objeto.getFecha());
            pss.setInt(2, objeto.getCantidad());
            pss.setInt(3, objeto.getProducto().getId());
            pss.setString(4, objeto.getCliente().getDni());
            
            int affectedRows = pss.executeUpdate();
            if (affectedRows > 0) {
                Logger.success("Pedido saved successfully");
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
     * Actualiza los datos de un pedido existente en la base de datos.
     * Permite modificar fecha, cantidad, producto y cliente asociados.
     * 
     * @param obj El pedido con los datos actualizados. Debe tener ID, cliente y producto válidos
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL o violación de claves foráneas
     */
    @Override
    public void update(Pedido obj) {
        if (obj == null || obj.getId() <= 0 || obj.getProducto() == null || obj.getCliente() == null) {
            Logger.error("Cannot update pedido: invalid object, ID, producto, or cliente");
            return;
        }
        
        try {
            GenericConnector connector = new SQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("UPDATE pedidos SET fecha = ?, cantidad = ?, producto_id = ?, cliente_dni = ? WHERE id = ?");
            pss.setDate(1, obj.getFecha());
            pss.setInt(2, obj.getCantidad());
            pss.setInt(3, obj.getProducto().getId());
            pss.setString(4, obj.getCliente().getDni());
            pss.setInt(5, obj.getId());
            
            int affectedRows = pss.executeUpdate();
            if (affectedRows > 0) {
                Logger.success("Pedido updated successfully: " + obj.getId());
            } else {
                Logger.warning("No pedido found with ID: " + obj.getId());
            }
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in update(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in update(): " + e.getMessage());
        }
    }

    /**
     * Elimina un pedido de la base de datos.
     * Utiliza internamente el método deleteById().
     * 
     * @param obj El pedido a eliminar. Debe tener ID válido
     * @return true si el pedido se eliminó correctamente, false en caso contrario
     */
    @Override
    public boolean delete(Pedido obj) {
        if (obj == null || obj.getId() <= 0) {
            Logger.error("Cannot delete pedido: invalid object or ID");
            return false;
        }
        return deleteById(obj.getId());
    }

    /**
     * Elimina un pedido de la base de datos por su ID.
     * 
     * @param id El ID del pedido a eliminar
     * @return true si el pedido se eliminó correctamente, false si no se encontró o hubo error
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    @Override
    public boolean deleteById(Integer id) {
        if (id == null || id <= 0) {
            Logger.error("Cannot delete pedido: invalid ID");
            return false;
        }
        
        try {
            GenericConnector connector = new SQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("DELETE FROM pedidos WHERE id = ?");
            pss.setInt(1, id);
            
            int affectedRows = pss.executeUpdate();
            if (affectedRows > 0) {
                Logger.success("Pedido deleted successfully: " + id);
                return true;
            } else {
                Logger.warning("No pedido found with ID: " + id);
            }
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in deleteById(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in deleteById(): " + e.getMessage());
        }
        return false;
    }

    /**
     * Elimina todos los pedidos de la base de datos.
     * ¡PRECAUCIÓN! Esta operación es irreversible y afectará todas las relaciones.
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
            PreparedStatement pss = connection.prepareStatement("DELETE FROM pedidos");
            int affectedRows = pss.executeUpdate();
            Logger.warning("Deleted all pedidos (" + affectedRows + " rows affected)");
            return true;
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in deleteAll(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in deleteAll(): " + e.getMessage());
        }
        return false;
    }

    // Additional custom methods for Pedido

    /**
     * Verifica si existe un pedido con el ID especificado.
     * 
     * @param id El ID a verificar
     * @return true si existe un pedido con ese ID, false en caso contrario
     */
    public boolean exists(Integer id) {
        return get(id) != null;
    }

    /**
     * Obtiene todos los pedidos asociados a un cliente específico.
     * Útil para consultar el historial de pedidos de un cliente.
     * 
     * @param clienteDni El DNI del cliente cuyos pedidos se quieren obtener
     * @return Lista de pedidos del cliente con sus relaciones cargadas
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    public List<Pedido> getByClienteDni(String clienteDni) {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            GenericConnector connector = new SQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM pedidos WHERE cliente_dni = ?");
            pss.setString(1, clienteDni);
            ResultSet rs = pss.executeQuery();
            
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setCantidad(rs.getInt("cantidad"));
                
                // Load related entities
                int productoId = rs.getInt("producto_id");
                Producto producto = productoDAO.get(productoId);
                Cliente cliente = clienteDAO.get(clienteDni);
                
                pedido.setProducto(producto);
                pedido.setCliente(cliente);
                pedidos.add(pedido);
            }
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in getByClienteDni(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getByClienteDni(): " + e.getMessage());
        }
        return pedidos;
    }

    /**
     * Obtiene todos los pedidos que contienen un producto específico.
     * Útil para analizar la demanda de un producto particular.
     * 
     * @param productoId El ID del producto cuyos pedidos se quieren obtener
     * @return Lista de pedidos que contienen el producto con sus relaciones cargadas
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    public List<Pedido> getByProductoId(Integer productoId) {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            GenericConnector connector = new SQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM pedidos WHERE producto_id = ?");
            pss.setInt(1, productoId);
            ResultSet rs = pss.executeQuery();
            
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setCantidad(rs.getInt("cantidad"));
                
                // Load related entities
                String clienteDni = rs.getString("cliente_dni");
                Producto producto = productoDAO.get(productoId);
                Cliente cliente = clienteDAO.get(clienteDni);
                
                pedido.setProducto(producto);
                pedido.setCliente(cliente);
                pedidos.add(pedido);
            }
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in getByProductoId(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getByProductoId(): " + e.getMessage());
        }
        return pedidos;
    }

    /**
     * Obtiene el número total de pedidos en la base de datos.
     * 
     * @return El número total de pedidos, 0 si no hay pedidos o en caso de error
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    public int count() {
        int count = 0;
        try {
            SQLConnector connector = new SQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT COUNT(*) FROM pedidos");
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

    /**
     * Obtiene el número de pedidos realizados por un cliente específico.
     * Útil para estadísticas de actividad del cliente.
     * 
     * @param cliente El cliente del cual contar los pedidos
     * @return El número de pedidos del cliente, 0 si no tiene pedidos, el cliente es null o en caso de error
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    public int countByCliente(Cliente cliente) {
        if (cliente == null || cliente.getDni() == null) {
            return 0;
        }
        
        int count = 0;
        try {
            GenericConnector connector = new SQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT COUNT(*) FROM pedidos WHERE cliente_dni = ?");
            pss.setString(1, cliente.getDni());
            ResultSet rs = pss.executeQuery();
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in countByCliente(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in countByCliente(): " + e.getMessage());
        }
        return count;
    }
}
