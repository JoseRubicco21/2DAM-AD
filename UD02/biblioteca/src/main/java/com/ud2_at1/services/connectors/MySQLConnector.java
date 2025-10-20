package com.ud2_at1.services.connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ud2_at1.services.connectors.exceptions.MySQLConnectorException;
import com.ud2_at1.services.loaders.ConfigLoader;

public class MySQLConnector {

    private Connection connection;

    public MySQLConnector(String username, String pasword, String url) throws MySQLConnectorException {
        try {
            this.connection = DriverManager.getConnection(url, username, pasword);
        } catch (SQLException e) {
            // Throws custom Exception
            throw new MySQLConnectorException(e.getMessage());
        }
    }

    public MySQLConnector() throws MySQLConnectorException {
        try {
            ConfigLoader config = ConfigLoader.getInstance();
            this.connection = DriverManager.getConnection(ConfigLoader.get("mysql.url"), ConfigLoader.get("mysql.user"),
                    ConfigLoader.get("mysql.password"));
        } catch (SQLException e) {
            // Throws custom Exception
            throw new MySQLConnectorException(e.getMessage());
        }
    }

    // Load the config Loader in the Stack call passing the invocation of getInstance.
    public MySQLConnector(ConfigLoader config) throws MySQLConnectorException {
        try {
            this.connection = DriverManager.getConnection(ConfigLoader.get("mysql.url"), ConfigLoader.get("mysql.user"),
                    ConfigLoader.get("mysql.password"));
        } catch (SQLException e) {
            // Throws custom Exception
            throw new MySQLConnectorException(e.getMessage());
        }

    }

    public Connection getConnection() {
        return connection;
    }

}
