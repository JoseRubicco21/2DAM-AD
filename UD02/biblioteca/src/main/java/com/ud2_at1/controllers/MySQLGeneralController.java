package com.ud2_at1.controllers;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.ud2_at1.controllers.crud.CRUD;
import com.ud2_at1.controllers.crud.Operation;
import com.ud2_at1.controllers.exceptions.GeneralControllerException;
import com.ud2_at1.controllers.intefaces.StatementOperation;
import com.ud2_at1.models.generic.Database;
import com.ud2_at1.services.connectors.MySQLConnector;
import com.ud2_at1.services.connectors.exceptions.MySQLConnectorException;
import com.ud2_at1.services.loaders.ConfigLoader;
import com.ud2_at1.services.logger.Logger;


/**
 * A general controller used for Modifying and Accesing a Database Model. This is 
 * a meta programming class used to manage databases. It should be shared across
 * multiple programs that manage a MYSQL db and Table controllers should extend
 * from this.
 */
public class MySQLGeneralController implements CRUD {

    private Database db;

    @Override
    public boolean create(Operation operation) {
        Logger.info("Launching CREATE operation");
        return operation.operate();
    }

    @Override
    public boolean update(Operation operation) {
        Logger.info("Launching UPDATE operation");
        return operation.operate();
    }

    @Override
    public boolean delete(Operation operation) {
        Logger.info("Launching DELETE operation");
        return operation.operate();
    }

    @Override
    public boolean read(Operation operation) {
        Logger.info("Launching READ operation");
        return operation.operate();
    }

  
    public static boolean createDatabase() {
        boolean result;
        try {
            result = prepareStatementTemplateMethod(MySQLGeneralController::createDatabaseStatement);
        } catch (MySQLConnectorException e) {
            result = false;
            e.displayExceptionMessage();
        }
        return result;
    }

    public static boolean deleteDatabase() {
        boolean result;
        try {
            result = prepareStatementTemplateMethod(MySQLGeneralController::deleteDatabaseStatement);
        } catch (MySQLConnectorException e) {
            result = false;
            e.displayExceptionMessage();
        }
        return result;
    }


    private static PreparedStatement deleteDatabaseStatement(Connection connection){
        PreparedStatement result;
        try {
            PreparedStatement pss = connection.prepareStatement("DROP DATABASE IF EXISTS ?;");
            pss.setString(1, ConfigLoader.get("mysql.dbname"));
            result =  pss;
        } catch (SQLException e){
            Logger.error(e.getMessage());
            result = null;
        }
        return result;
    }
 
    private static PreparedStatement createDatabaseStatement(Connection connection){
        PreparedStatement result;
        try {
            PreparedStatement pss = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS ? CHARACTER SET ? COLLATE ?;");
            pss.setString(1, ConfigLoader.get("mysql.dbname"));
            pss.setString(2, ConfigLoader.get("mysql.charset"));
            pss.setString(3, ConfigLoader.get("mysql.charset.collation"));
            Logger.success("Se ha creado la base de datos correctamente");
            result = pss;
        } catch (SQLException e){
            Logger.error(e.getMessage());
            result = null;
        }
        return result;
    }

    public static boolean showDatabase(Connection connection){
        boolean result;
        try {
            PreparedStatement pss = connection.prepareStatement("SHOW DATABASES;");
            ResultSet resultSet = pss.executeQuery();
            while (resultSet.next()) {
                Logger.info(resultSet.getString(1));
            }
            result = true;
            pss.close();
        }
        catch (SQLException e ){
            Logger.error(e.getMessage());
            result = false;
        }
        return result;
    }

    private static PreparedStatement createUserStatement(Connection connection){
        PreparedStatement result;
        try{
            PreparedStatement pss = connection.prepareStatement("CREATE USER IF NOT EXISTS ?@? IDENTIFIED BY ?;");
            pss.setString(1, ConfigLoader.get("mysql.user"));
            pss.setString(2, "%"); // SQL ref
            pss.setString(3, ConfigLoader.get("mysql.password"));
            result = pss;
        }   catch (SQLException e) {
            Logger.error(e.getMessage());
            result = null;
        }
        return result;
    }

    private static PreparedStatement grantPrivilegesStatement(Connection connection){
        PreparedStatement result;
        try {
            PreparedStatement pss = connection.prepareStatement("GRANT ALL PRIVILEGES ON ?.* TO ?@?;");
            pss.setString(1, ConfigLoader.get("mysql.dbname"));
            pss.setString(2, ConfigLoader.get("mysql.user"));
            pss.setString(3, "%");
            result = pss;
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            result = null;
        }
        return result;
    }

    private static PreparedStatement flushPrivilegesStatement(Connection connection){
        PreparedStatement result;
        try{
            PreparedStatement pss = connection.prepareStatement(("FLUSH PRIVILEGES;"));
            result = pss;
            return result;
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            result = null;
        }
        return result;
    }

    private static boolean prepareStatementTemplateMethod(StatementOperation op) throws MySQLConnectorException{
        boolean result;
        try{
            Connection connection = MySQLConnector.getConnectionAsRoot();
            PreparedStatement pss = op.statementOperation(connection);
            result = pss.execute();
            pss.close();
            connection.close();
        } catch (MySQLConnectorException e) {
            e.displayExceptionMessage();
            result = false;
        }
        catch (SQLException e) {
            result = false;
            throw new MySQLConnectorException(e.getMessage());
        }
        return result;
    }

    public static boolean init() throws GeneralControllerException{
        boolean result;
        try {
            result = prepareStatementTemplateMethod(MySQLGeneralController::createDatabaseStatement);
            if (result == false) throw new GeneralControllerException("Error creating the database");
            result = prepareStatementTemplateMethod(MySQLGeneralController::createUserStatement);
            if (result == false) throw new GeneralControllerException("Error creating the user");
            result = prepareStatementTemplateMethod(MySQLGeneralController::grantPrivilegesStatement);
            if (result == false) throw new GeneralControllerException("Error granting privileges");
            result = prepareStatementTemplateMethod(MySQLGeneralController::flushPrivilegesStatement);
            if (result == false) throw new GeneralControllerException("Error flushing the privileges");
        } catch (MySQLConnectorException e) {
            result = false;
            e.displayExceptionMessage();
        }
        return result;
    }



}
