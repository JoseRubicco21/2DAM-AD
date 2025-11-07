package com.ad_ud2_at2.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ad_ud2_at2.dao.Dao;
import com.ad_ud2_at2.models.Producto;
import com.ad_ud2_at2.services.connectors.GenericConnector;
import com.ad_ud2_at2.services.connectors.SQLConnector;
import com.ad_ud2_at2.services.connectors.exceptions.ConnectorException;
import com.ad_ud2_at2.services.logger.Logger;

/**
 * Implementación del patrón DAO para la entidad Producto.
 * Proporciona operaciones CRUD (Create, Read, Update, Delete) para gestionar
 * productos en la base de datos MySQL.
 * 
 * Esta clase implementa la interfaz Dao<Producto, Integer> donde:
 * - Producto es el tipo de entidad gestionada
 * - Integer es el tipo de la clave primaria (ID)
 * 
 * Incluye métodos adicionales para búsquedas específicas como:
 * - Búsqueda por nombre (coincidencia parcial)
 * - Filtrado por rango de precios
 * - Conteo de productos
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 */
public class ProductoDAO implements Dao<Producto, Integer> {

    /**
     * Obtiene un producto específico por su ID.
     * 
     * @param id El ID del producto a buscar (clave primaria)
     * @return El objeto Producto encontrado, o null si no existe
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    @Override
    public Producto get(Integer id) {
        Producto producto = null;
        try {
            GenericConnector connector = new SQLConnector();
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

    /**
     * Obtiene todos los productos de la base de datos.
     * 
     * @return Lista con todos los productos existentes. Lista vacía si no hay productos
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    @Override
    public List<Producto> getAll() {
        List<Producto> productos = new ArrayList<>();
        try {
            GenericConnector connector = new SQLConnector();
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

    /**
     * Guarda un nuevo producto en la base de datos.
     * El ID se genera automáticamente por la base de datos (AUTO_INCREMENT).
     * 
     * @param objeto El producto a guardar. No debe ser null
     * @return true si el producto se guardó correctamente, false en caso contrario
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    @Override
    public boolean save(Producto objeto) {
        if (objeto == null) {
            Logger.error("Cannot save producto: object is null");
            return false;
        }
        
        try {
            GenericConnector connector = new SQLConnector();
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

    /**
     * Actualiza los datos de un producto existente en la base de datos.
     * Permite modificar nombre, descripción y precio del producto.
     * 
     * @param obj El producto con los datos actualizados. Debe tener ID válido
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    @Override
    public void update(Producto obj) {
        if (obj == null || obj.getId() <= 0) {
            Logger.error("Cannot update producto: invalid object or ID");
            return;
        }
        
        try {
            GenericConnector connector = new SQLConnector();
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

    /**
     * Elimina un producto de la base de datos.
     * Utiliza internamente el método deleteById().
     * 
     * @param obj El producto a eliminar. Debe tener ID válido
     * @return true si el producto se eliminó correctamente, false en caso contrario
     */
    @Override
    public boolean delete(Producto obj) {
        if (obj == null || obj.getId() <= 0) {
            Logger.error("Cannot delete producto: invalid object or ID");
            return false;
        }
        return deleteById(obj.getId());
    }

    /**
     * Elimina un producto de la base de datos por su ID.
     * ¡PRECAUCIÓN! Esta operación puede afectar pedidos existentes que referencien este producto.
     * 
     * @param id El ID del producto a eliminar
     * @return true si el producto se eliminó correctamente, false si no se encontró o hubo error
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL o violación de claves foráneas
     */
    @Override
    public boolean deleteById(Integer id) {
        if (id == null || id <= 0) {
            Logger.error("Cannot delete producto: invalid ID");
            return false;
        }
        
        try {
            GenericConnector connector = new SQLConnector();
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

    /**
     * Elimina todos los productos de la base de datos.
     * ¡PRECAUCIÓN! Esta operación es irreversible y puede afectar pedidos existentes.
     * 
     * @return true si la operación se completó correctamente, false en caso de error
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL o violación de claves foráneas
     */
    @Override
    public boolean deleteAll() {
        try {
            GenericConnector connector = new SQLConnector();
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
     * Verifica si existe un producto con el ID especificado.
     * 
     * @param id El ID a verificar
     * @return true si existe un producto con ese ID, false en caso contrario
     */
    public boolean exists(Integer id) {
        return get(id) != null;
    }

    /**
     * Busca productos por nombre utilizando coincidencia parcial.
     * La búsqueda es case-sensitive y utiliza el operador LIKE con wildcards.
     * 
     * @param searchTerm El término de búsqueda para el nombre del producto
     * @return Lista de productos que contienen el término en su nombre
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     * 
     * @example
     * <pre>
     * List&lt;Producto&gt; productos = productoDAO.searchByNombre("laptop");
     * // Retorna productos como "Laptop Gaming", "Laptop Ultrabook", etc.
     * </pre>
     */
    public List<Producto> searchByNombre(String searchTerm) {
        List<Producto> productos = new ArrayList<>();
        try {
            GenericConnector connector = new SQLConnector();
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
     * Obtiene productos dentro de un rango de precios específico.
     * Los valores límite están incluidos en la búsqueda (BETWEEN es inclusivo).
     * 
     * @param minPrice El precio mínimo (incluido)
     * @param maxPrice El precio máximo (incluido)
     * @return Lista de productos cuyo precio está en el rango especificado
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     * 
     * @example
     * <pre>
     * List&lt;Producto&gt; productos = productoDAO.getByPriceRange(100.0f, 500.0f);
     * // Retorna productos con precio entre 100€ y 500€ (ambos incluidos)
     * </pre>
     */
    public List<Producto> getByPriceRange(float minPrice, float maxPrice) {
        List<Producto> productos = new ArrayList<>();
        try {
            SQLConnector connector = new SQLConnector();
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
     * Obtiene el número total de productos en la base de datos.
     * Útil para paginación y estadísticas del catálogo.
     * 
     * @return El número total de productos, 0 si no hay productos o en caso de error
     * @throws ConnectorException Si hay problemas de conexión a la base de datos
     * @throws SQLException Si hay errores en la consulta SQL
     */
    public int count() {
        int count = 0;
        try {
            GenericConnector connector = new SQLConnector();
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
