package com.ud2_at1.dao.generic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ud2_at1.controllers.MySQLGeneralController;
import com.ud2_at1.controllers.exceptions.GeneralControllerException;
import com.ud2_at1.controllers.intefaces.StatementOperation;
import com.ud2_at1.dao.Dao;
import com.ud2_at1.models.generic.Database;
import com.ud2_at1.services.connectors.MySQLConnector;
import com.ud2_at1.services.connectors.exceptions.MySQLConnectorException;
import com.ud2_at1.services.database.DatabaseService;
import com.ud2_at1.services.database.query_builder.QueryBuilder;
import com.ud2_at1.services.loaders.ConfigLoader;
import com.ud2_at1.services.logger.Logger;

public class DatabaseDAO implements Dao<Database, Integer> {

    private Database db;


    @Override
    public Database get(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public List<Database> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public boolean save(Database objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public boolean delete(Database obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public boolean deleteAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public boolean deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public void update(Database obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }



}
