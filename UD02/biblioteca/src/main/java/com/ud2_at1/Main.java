package com.ud2_at1;

import com.ud2_at1.services.connectors.MySQLConnector;
import com.ud2_at1.services.connectors.exceptions.MySQLConnectorException;
import com.ud2_at1.services.loaders.ConfigLoader;
import com.ud2_at1.services.logger.Logger;

public class Main {

    MySQLConnector connection;
    private static void init(){
        try {
            MySQLConnector connector = new MySQLConnector(ConfigLoader.getInstance());
            if(connector != null){
                Logger.success("Connected correctly.");
            }
        } catch (MySQLConnectorException e) {
            e.displayExceptionMessage();
        }

    }
    public static void main(String[] args) {
        // Initialize the database with a test connection.
        init();
    }
}