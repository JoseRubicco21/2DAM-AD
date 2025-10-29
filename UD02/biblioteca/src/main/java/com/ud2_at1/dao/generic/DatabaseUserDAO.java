package com.ud2_at1.dao.generic;

import com.ud2_at1.controllers.exceptions.GeneralControllerException;
import com.ud2_at1.dao.Dao;
import com.ud2_at1.models.generic.DatabaseUser;
import com.ud2_at1.services.connectors.exceptions.MySQLConnectorException;
import com.ud2_at1.services.database.DatabaseService;
import com.ud2_at1.services.database.query_builder.QueryBuilder;
import com.ud2_at1.services.loaders.ConfigLoader;
import com.ud2_at1.services.logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseUserDAO implements Dao<DatabaseUser, Integer> {

    @Override
    public DatabaseUser get(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public List<DatabaseUser> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public boolean save(DatabaseUser objeto) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public boolean delete(DatabaseUser obj) {
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
    public void update(DatabaseUser obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    private PreparedStatement createUserStatement(Connection connection) {
        PreparedStatement result;
        try {
            QueryBuilder qb = new QueryBuilder();
            
        
            String query = qb.CREATE().USER().IF().NOT().EXISTS()
                    .INPUT(ConfigLoader.get("mysql.user")).ATSIGN().PERCENT().IDENTIFIED().BY()
                    .INPUT(ConfigLoader.get("mysql.password")).build();
            PreparedStatement pss = connection.prepareStatement(query);
            result = pss;
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            result = null;
        }
        return result;
    }

    private PreparedStatement grantPrivilegesStatement(Connection connection) {
        PreparedStatement result;
        try {
            QueryBuilder qb = new QueryBuilder();
            String query = qb.GRANT().ALL().PRIVILEGES().ON()
                    .INPUT(ConfigLoader.get("mysql.dbname") + ".*").TO()
                    .INPUT(ConfigLoader.get("mysql.user")).ATSIGN().PERCENT().build();
            PreparedStatement pss = connection.prepareStatement(query);
            result = pss;
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            result = null;
        }
        return result;
    }

    private PreparedStatement flushPrivilegesStatement(Connection connection) {
        PreparedStatement result;
        try {
            QueryBuilder qb = new QueryBuilder();
            String query = qb.FLUSH().PRIVILEGES().build();
            PreparedStatement pss = connection.prepareStatement(query);
            result = pss;
            return result;
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            result = null;
        }
        return result;
    }

    public boolean init() throws GeneralControllerException {
        boolean result;
        try {
            result = DatabaseService.executeStatement(this::createUserStatement);
            if (result == false)
                throw new GeneralControllerException("Error creating the user");
            result = DatabaseService.executeStatement(this::grantPrivilegesStatement);
            if (result == false)
                throw new GeneralControllerException("Error granting privileges");
            result = DatabaseService.executeStatement(this::flushPrivilegesStatement);
            if (result == false)
                throw new GeneralControllerException("Error flushing the privileges");
        } catch (MySQLConnectorException e) {
            result = false;
            e.displayExceptionMessage();
        }
        return result;
    }
}
