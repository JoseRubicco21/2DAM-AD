package com.postgres.services.connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.postgres.services.connectors.exceptions.ConnectorException;
import com.postgres.services.loaders.ConfigLoader;
import com.postgres.services.logger.Logger;

/**
 * Implementación de conector SQL genérico para bases de datos relacionales.
 * 
 * Esta clase proporciona diferentes formas de establecer conexiones a bases de datos SQL,
 * principalmente orientada a postgres. Implementa la interfaz GenericConnector y ofrece
 * múltiples constructores para diferentes escenarios de conexión.
 * 
 * Funcionalidades principales:
 * - Conexión con parámetros explícitos
 * - Conexión usando configuración automática
 * - Conexiones como usuario root
 * - Conexiones directas a bases de datos específicas
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see GenericConnector
 * @see ConfigLoader
 * @see ConnectorException
 */
public class SQLConnector implements GenericConnector {

    /**
     * Conexión JDBC activa a la base de datos.
     * Almacena la conexión establecida por cualquiera de los constructores.
     */
    private Connection connection;

    /**
     * Constructor con parámetros explícitos para conexión personalizada.
     * Permite establecer conexión con credenciales y URL específicas.
     * 
     * @param username El nombre de usuario para la conexión a la base de datos
     * @param pasword La contraseña del usuario (nota: parámetro mal escrito en original)
     * @param url La URL completa de conexión JDBC (ej: jdbc:postgres://localhost:3306/database)
     * @throws ConnectorException Si no se puede establecer la conexión debido a
     *         credenciales incorrectas, URL malformada, o base de datos no disponible
     */
    public SQLConnector(String username, String pasword, String url) throws ConnectorException {
        try {
            this.connection = DriverManager.getConnection(url, username, pasword);
        } catch (SQLException e) {
            // Throws custom Exception
            throw new ConnectorException(e.getMessage());
        }
    }

    /**
     * Constructor por defecto que utiliza configuración automática.
     * Lee los parámetros de conexión desde ConfigLoader y establece conexión
     * a la base de datos especificada en la configuración.
     * 
     * @throws ConnectorException Si no se puede establecer la conexión debido a
     *         problemas de configuración, credenciales incorrectas, o base de datos no disponible
     */
    public SQLConnector() throws ConnectorException {
        try {
            // Initialize ConfigLoader
            ConfigLoader.getInstance();
            // Change from postgres.* to mysql.*
            String baseUrl = ConfigLoader.get("mysql.url");
            String databaseName = ConfigLoader.get("mysql.dbname");
            String fullUrl = baseUrl + "/" + databaseName;
            
            this.connection = DriverManager.getConnection(
                fullUrl, 
                ConfigLoader.get("mysql.user"),
                ConfigLoader.get("mysql.password")
            );
            
            Logger.info("SQLConnector initialized with database: " + databaseName);
        } catch (SQLException e) {
            // Throws custom Exception
            throw new ConnectorException(e.getMessage());
        }
    }

    /**
     * Constructor que acepta un ConfigLoader como parámetro.
     * Aunque recibe ConfigLoader como parámetro, utiliza la instancia singleton
     * para obtener la configuración. Útil para inyección de dependencias.
     * 
     * @param config Instancia de ConfigLoader (se usa el singleton internamente)
     * @throws ConnectorException Si no se puede establecer la conexión debido a
     *         problemas de configuración, credenciales incorrectas, o base de datos no disponible
     */
    public SQLConnector(ConfigLoader config) throws ConnectorException {
        try {
            // Change from postgres.* to mysql.*
            String baseUrl = ConfigLoader.get("mysql.url");
            String databaseName = ConfigLoader.get("mysql.dbname");
            String fullUrl = baseUrl + "/" + databaseName;
            
            this.connection = DriverManager.getConnection(
                fullUrl, 
                ConfigLoader.get("mysql.user"),
                ConfigLoader.get("mysql.password")
            );
            
            Logger.info("SQLConnector initialized with config and database: " + databaseName);
        } catch (SQLException e) {
            // Throws custom Exception
            throw new ConnectorException(e.getMessage());
        }
    }

    /**
     * Método estático para obtener conexión como usuario root sin especificar base de datos.
     * Útil para operaciones administrativas como crear/eliminar bases de datos.
     * 
     * @return Connection Conexión JDBC como usuario root
     * @throws ConnectorException Si no se puede establecer la conexión como root
     *         debido a credenciales incorrectas o servidor no disponible
     */
    public static Connection getConnectionAsRoot() throws ConnectorException{
        try{
            ConfigLoader.getInstance();
            // Fix: Use mysql.* properties
            String baseUrl = ConfigLoader.get("mysql.url");
            Connection connection = DriverManager.getConnection(baseUrl, "root", "root");
            Logger.info("Connected as root to MySQL server");
            return connection;
        } catch (SQLException e){
            throw new ConnectorException(e.getMessage());
        }
    }

    /**
     * Método estático para obtener conexión como usuario regular a la base de datos específica.
     * Este método asegura que la conexión se establezca directamente a la base de datos
     * especificada en la configuración.
     * 
     * @return Connection Conexión JDBC a la base de datos específica
     * @throws ConnectorException Si no se puede conectar a la base de datos debido a
     * credenciales incorrectas, base de datos no existe, o problemas de configuración
     */
    public static Connection getConnectionToDatabase() throws ConnectorException {
        try {
            ConfigLoader.getInstance();
            // Change from postgres.* to mysql.*
            String baseUrl = ConfigLoader.get("mysql.url");
            String databaseName = ConfigLoader.get("mysql.dbname");
            String fullUrl = baseUrl + "/" + databaseName;
            
            Connection connection = DriverManager.getConnection(
                fullUrl, 
                ConfigLoader.get("mysql.user"), 
                ConfigLoader.get("mysql.password")
            );
            
            Logger.info("Connected to database: " + databaseName);
            return connection;
        } catch (SQLException e) {
            throw new ConnectorException("Failed to connect to database: " + e.getMessage());
        }
    }

    /**
     * Método estático para obtener conexión como root a la base de datos específica.
     * Este método asegura que la conexión se establezca directamente a la base de datos
     * especificada en la configuración con privilegios de administrador.
     * 
     * @return Connection Conexión JDBC como root a la base de datos específica
     * @throws ConnectorException Si no se puede conectar como root a la base de datos
     *         debido a credenciales incorrectas, base de datos no existe, o problemas de configuración
     */
    public static Connection getConnectionToDatabaseAsRoot() throws ConnectorException {
        try {
            ConfigLoader.getInstance();
            // Change from postgres.* to mysql.*
            String baseUrl = ConfigLoader.get("mysql.url");
            String databaseName = ConfigLoader.get("mysql.dbname");
            String fullUrl = baseUrl + "/" + databaseName;
            
            Connection connection = DriverManager.getConnection(fullUrl, "root", "root");
            Logger.info("Connected to database as root: " + databaseName);
            return connection;
        } catch (SQLException e) {
            throw new ConnectorException("Failed to connect to database as root: " + e.getMessage());
        }
    }

    /**
     * Obtiene la conexión JDBC establecida por esta instancia.
     * Implementa el método requerido por la interfaz GenericConnector.
     * 
     * @return Connection La conexión JDBC activa, puede ser null si no se ha establecido
     */
    public Connection getConnection() {
        return connection;
    }
}
