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
import com.ud2_at1.services.database.query_builder.QueryBuilder;
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

    public MySQLGeneralController(){

    }

    public MySQLGeneralController(Database db){
        this.db = db;
    }

    
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

  

}
