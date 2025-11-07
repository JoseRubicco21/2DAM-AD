package com.ad_ud2_at2.services.connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ad_ud2_at2.services.connectors.exceptions.ConnectorException;
import com.ad_ud2_at2.services.loaders.ConfigLoader;
import com.ad_ud2_at2.services.logger.Logger;

public class MariaDBConnerctor {
    private Connection connection;

    public MariaDBConnerctor(String username, String pasword, String url) throws ConnectorException {
        try {
            this.connection = DriverManager.getConnection(url, username, pasword);
        } catch (SQLException e) {
            // Throws custom Exception
            throw new ConnectorException(e.getMessage());
        }
    }

    public MariaDBConnerctor() throws ConnectorException {
        try {
            // Initialize ConfigLoader
            ConfigLoader.getInstance();
            String baseUrl = ConfigLoader.get("mysql.url");
            String databaseName = ConfigLoader.get("mysql.dbname");
            String fullUrl = baseUrl + "/" + databaseName;
            
            this.connection = DriverManager.getConnection(
                fullUrl, 
                ConfigLoader.get("mysql.user"),
                ConfigLoader.get("mysql.password")
            );
            
            Logger.info("MySQLConnector initialized with database: " + databaseName);
        } catch (SQLException e) {
            // Throws custom Exception
            throw new ConnectorException(e.getMessage());
        }
    }

    // Load the config Loader in the Stack call passing the invocation of getInstance.
    public MariaDBConnerctor(ConfigLoader config) throws ConnectorException {
        try {
            String baseUrl = ConfigLoader.get("mysql.url");
            String databaseName = ConfigLoader.get("mysql.dbname");
            String fullUrl = baseUrl + "/" + databaseName;
            
            this.connection = DriverManager.getConnection(
                fullUrl, 
                ConfigLoader.get("mysql.user"),
                ConfigLoader.get("mysql.password")
            );
            
            Logger.info("MySQLConnector initialized with config and database: " + databaseName);
        } catch (SQLException e) {
            // Throws custom Exception
            throw new ConnectorException(e.getMessage());
        }

    }

    public static Connection getConnectionAsRoot() throws ConnectorException{
        try{
            ConfigLoader.getInstance();
            Connection connection = DriverManager.getConnection(ConfigLoader.get("mysql.url"), "root", "root");
            return connection;
        } catch (SQLException e){
            throw new ConnectorException(e.getMessage());
        }
    }

    /**
     * Get a connection as a regular user to the specific database
     * This method ensures the connection is made directly to the database specified in the config
     */
    public static Connection getConnectionToDatabase() throws ConnectorException {
        try {
            ConfigLoader.getInstance();
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
     * Get a connection as root to the specific database
     * This method ensures the connection is made directly to the database specified in the config
     */
    public static Connection getConnectionToDatabaseAsRoot() throws ConnectorException {
        try {
            ConfigLoader.getInstance();
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

    public Connection getConnection() {
        return connection;
    }

}
