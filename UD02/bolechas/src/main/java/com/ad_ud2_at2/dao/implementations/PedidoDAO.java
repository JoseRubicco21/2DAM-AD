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
import com.ad_ud2_at2.services.connectors.MySQLConnector;
import com.ad_ud2_at2.services.connectors.exceptions.MySQLConnectorException;
import com.ad_ud2_at2.services.logger.Logger;

public class PedidoDAO implements Dao<Pedido, Integer> {

    private ClienteDAO clienteDAO = new ClienteDAO();
    private ProductoDAO productoDAO = new ProductoDAO();

    @Override
    public Pedido get(Integer id) {
        Pedido pedido = null;
        try {
            MySQLConnector connector = new MySQLConnector();
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
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in get(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in get(): " + e.getMessage());
        }
        return pedido;
    }

    @Override
    public List<Pedido> getAll() {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            MySQLConnector connector = new MySQLConnector();
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
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in getAll(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getAll(): " + e.getMessage());
        }
        return pedidos;
    }

    @Override
    public boolean save(Pedido objeto) {
        if (objeto == null || objeto.getProducto() == null || objeto.getCliente() == null) {
            Logger.error("Cannot save pedido: object, producto, or cliente is null");
            return false;
        }
        
        try {
            MySQLConnector connector = new MySQLConnector();
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
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in save(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in save(): " + e.getMessage());
        }
        return false;
    }

    @Override
    public void update(Pedido obj) {
        if (obj == null || obj.getId() <= 0 || obj.getProducto() == null || obj.getCliente() == null) {
            Logger.error("Cannot update pedido: invalid object, ID, producto, or cliente");
            return;
        }
        
        try {
            MySQLConnector connector = new MySQLConnector();
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
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in update(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in update(): " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Pedido obj) {
        if (obj == null || obj.getId() <= 0) {
            Logger.error("Cannot delete pedido: invalid object or ID");
            return false;
        }
        return deleteById(obj.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        if (id == null || id <= 0) {
            Logger.error("Cannot delete pedido: invalid ID");
            return false;
        }
        
        try {
            MySQLConnector connector = new MySQLConnector();
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
            PreparedStatement pss = connection.prepareStatement("DELETE FROM pedidos");
            int affectedRows = pss.executeUpdate();
            Logger.warning("Deleted all pedidos (" + affectedRows + " rows affected)");
            return true;
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in deleteAll(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in deleteAll(): " + e.getMessage());
        }
        return false;
    }

    // Additional custom methods for Pedido

    /**
     * Check if a pedido exists by ID
     */
    public boolean exists(Integer id) {
        return get(id) != null;
    }

    /**
     * Get pedidos by cliente DNI
     */
    public List<Pedido> getByClienteDni(String clienteDni) {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            MySQLConnector connector = new MySQLConnector();
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
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in getByClienteDni(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getByClienteDni(): " + e.getMessage());
        }
        return pedidos;
    }

    /**
     * Get pedidos by producto ID
     */
    public List<Pedido> getByProductoId(Integer productoId) {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            MySQLConnector connector = new MySQLConnector();
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
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in getByProductoId(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getByProductoId(): " + e.getMessage());
        }
        return pedidos;
    }

    /**
     * Get the count of all pedidos
     */
    public int count() {
        int count = 0;
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT COUNT(*) FROM pedidos");
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

    /**
     * Get count of pedidos by cliente
     */
    public int countByCliente(Cliente cliente) {
        if (cliente == null || cliente.getDni() == null) {
            return 0;
        }
        
        int count = 0;
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT COUNT(*) FROM pedidos WHERE cliente_dni = ?");
            pss.setString(1, cliente.getDni());
            ResultSet rs = pss.executeQuery();
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (MySQLConnectorException e) {
            Logger.error("MySQLConnectorException in countByCliente(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in countByCliente(): " + e.getMessage());
        }
        return count;
    }
}
