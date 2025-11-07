package com.ad_ud2_at2.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ad_ud2_at2.dao.Dao;
import com.ad_ud2_at2.models.Producto;
import com.ad_ud2_at2.services.connectors.MySQLConnector;
import com.ad_ud2_at2.services.connectors.exceptions.ConnectorException;
import com.ad_ud2_at2.services.logger.Logger;

public class ProductoDAO implements Dao<Producto, Integer> {

    @Override
    public Producto get(Integer id) {
        Producto producto = null;
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM productos WHERE id = ?");
            pss.setInt(1, id);
            ResultSet rs = pss.executeQuery();
            
            if (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getFloat("precio"));
            }
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in get(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in get(): " + e.getMessage());
        }
        return producto;
    }

    @Override
    public List<Producto> getAll() {
        List<Producto> productos = new ArrayList<>();
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM productos");
            ResultSet rs = pss.executeQuery();
            
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getFloat("precio"));
                productos.add(producto);
            }
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in getAll(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getAll(): " + e.getMessage());
        }
        return productos;
    }

    @Override
    public boolean save(Producto objeto) {
        if (objeto == null) {
            Logger.error("Cannot save producto: object is null");
            return false;
        }
        
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("INSERT INTO productos (nombre, descripcion, precio) VALUES (?, ?, ?)");
            pss.setString(1, objeto.getNombre());
            pss.setString(2, objeto.getDescripcion());
            pss.setFloat(3, objeto.getPrecio());
            
            int affectedRows = pss.executeUpdate();
            if (affectedRows > 0) {
                Logger.success("Producto saved successfully: " + objeto.getNombre());
                return true;
            }
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in save(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in save(): " + e.getMessage());
        }
        return false;
    }

    @Override
    public void update(Producto obj) {
        if (obj == null || obj.getId() <= 0) {
            Logger.error("Cannot update producto: invalid object or ID");
            return;
        }
        
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("UPDATE productos SET nombre = ?, descripcion = ?, precio = ? WHERE id = ?");
            pss.setString(1, obj.getNombre());
            pss.setString(2, obj.getDescripcion());
            pss.setFloat(3, obj.getPrecio());
            pss.setInt(4, obj.getId());
            
            int affectedRows = pss.executeUpdate();
            if (affectedRows > 0) {
                Logger.success("Producto updated successfully: " + obj.getId());
            } else {
                Logger.warning("No producto found with ID: " + obj.getId());
            }
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in update(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in update(): " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Producto obj) {
        if (obj == null || obj.getId() <= 0) {
            Logger.error("Cannot delete producto: invalid object or ID");
            return false;
        }
        return deleteById(obj.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        if (id == null || id <= 0) {
            Logger.error("Cannot delete producto: invalid ID");
            return false;
        }
        
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("DELETE FROM productos WHERE id = ?");
            pss.setInt(1, id);
            
            int affectedRows = pss.executeUpdate();
            if (affectedRows > 0) {
                Logger.success("Producto deleted successfully: " + id);
                return true;
            } else {
                Logger.warning("No producto found with ID: " + id);
            }
        } catch (ConnectorException e) {
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
            PreparedStatement pss = connection.prepareStatement("DELETE FROM productos");
            int affectedRows = pss.executeUpdate();
            Logger.warning("Deleted all productos (" + affectedRows + " rows affected)");
            return true;
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in deleteAll(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in deleteAll(): " + e.getMessage());
        }
        return false;
    }

    // Additional custom methods for Producto

    /**
     * Check if a producto exists by ID
     */
    public boolean exists(Integer id) {
        return get(id) != null;
    }

    /**
     * Search productos by nombre (partial match)
     */
    public List<Producto> searchByNombre(String searchTerm) {
        List<Producto> productos = new ArrayList<>();
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM productos WHERE nombre LIKE ?");
            pss.setString(1, "%" + searchTerm + "%");
            ResultSet rs = pss.executeQuery();
            
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getFloat("precio"));
                productos.add(producto);
            }
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in searchByNombre(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in searchByNombre(): " + e.getMessage());
        }
        return productos;
    }

    /**
     * Get productos by price range
     */
    public List<Producto> getByPriceRange(float minPrice, float maxPrice) {
        List<Producto> productos = new ArrayList<>();
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT * FROM productos WHERE precio BETWEEN ? AND ?");
            pss.setFloat(1, minPrice);
            pss.setFloat(2, maxPrice);
            ResultSet rs = pss.executeQuery();
            
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getFloat("precio"));
                productos.add(producto);
            }
        } catch (ConnectorException e) {
            Logger.error("MySQLConnectorException in getByPriceRange(): " + e.getMessage());
        } catch (SQLException e) {
            Logger.error("SQLException in getByPriceRange(): " + e.getMessage());
        }
        return productos;
    }

    /**
     * Get the count of all productos
     */
    public int count() {
        int count = 0;
        try {
            MySQLConnector connector = new MySQLConnector();
            Connection connection = connector.getConnection();
            PreparedStatement pss = connection.prepareStatement("SELECT COUNT(*) FROM productos");
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
