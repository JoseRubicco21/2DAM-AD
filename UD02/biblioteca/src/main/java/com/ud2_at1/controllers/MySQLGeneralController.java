package com.ud2_at1.controllers;


import javax.xml.crypto.Data;

import com.ud2_at1.controllers.crud.CRUD;
import com.ud2_at1.controllers.crud.Operation;
import com.ud2_at1.models.generic.Database;
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

    
}
