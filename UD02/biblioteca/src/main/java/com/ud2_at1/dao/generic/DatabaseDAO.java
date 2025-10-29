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
        this.db = objeto;
        try {
            return DatabaseService.executeStatement(this::createDatabaseStatement);
        } catch (MySQLConnectorException e) {
            e.displayExceptionMessage();
            return false;
        }
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

    public boolean createDatabase() {
        try {
            return DatabaseService.executeStatement(this::createDatabaseStatement);
        } catch (MySQLConnectorException e) {
            e.displayExceptionMessage();
            return false;
        }
    }

    public boolean deleteDatabase() {
        try {
            return DatabaseService.executeStatement(this::deleteDatabaseStatement);
        } catch (MySQLConnectorException e) {
            e.displayExceptionMessage();
            return false;
        }
    }


    private  PreparedStatement deleteDatabaseStatement(Connection connection){
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
 
    private  PreparedStatement createDatabaseStatement(Connection connection){
        PreparedStatement result;
        try {
            QueryBuilder qb = new QueryBuilder();
            String query = qb.CREATE().DATABASE().IF().NOT().EXISTS().INPUT(this.db.getName())
                            .CHARSET().INPUT(this.db.getCharset()).
                            COLLATION().INPUT(this.db.getCollationType())
                            .build();
            PreparedStatement pss = connection.prepareStatement(query);
            Logger.success("Se ha creado la base de datos correctamente");
            result = pss;
        } catch (SQLException e){
            Logger.error(e.getMessage());
            result = null;
        }
        return result;
    }

    public boolean showDatabase(Connection connection){
        boolean result;
        try {
            QueryBuilder qb = new QueryBuilder();
            String query = qb.SHOW().DATABASES().build();
            PreparedStatement pss = connection.prepareStatement(query);
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

  

    public boolean init() throws GeneralControllerException{
        boolean result;
        try {
            result = DatabaseService.executeStatement(this::createDatabaseStatement);
            if (result == false) throw new GeneralControllerException("Error creating the database");
        } catch (MySQLConnectorException e) {
            result = false;
            e.displayExceptionMessage();
        }
        return result;
    }


}
