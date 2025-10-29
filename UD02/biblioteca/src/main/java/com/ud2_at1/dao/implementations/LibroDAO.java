package com.ud2_at1.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ud2_at1.dao.Dao;
import com.ud2_at1.models.Libro;
import com.ud2_at1.services.database.query_builder.QueryBuilder;

public class LibroDAO implements Dao<Libro, Integer> {

    @Override
    public Libro get(Integer id) {
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public List<Libro> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public boolean save(Libro objeto) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public boolean delete(Libro obj) {
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
    public void update(Libro obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public PreparedStatement createTableStatement(Connection connection) throws SQLException {
        QueryBuilder qb = new QueryBuilder();
        QueryBuilder qbModel = new QueryBuilder();
        
        String modelQuery = qbModel
            .INPUT("id").PRIMARY().KEY().AUTO_INCREMENT()
            .INPUT("title").VARCHAR(255).NOTNULL()
            .INPUT("author_id").INT().NOTNULL()
            .INPUT("isbn").VARCHAR(13).NOTNULL().UNIQUE()
            .build();
        
        String query = qb.CREATE().TABLE().INPUT("libros").MODEL(modelQuery).build();
        
        return connection.prepareStatement(query);
    }
 
    public PreparedStatement deleteTableStatement(Connection connection) throws SQLException {
        QueryBuilder qb = new QueryBuilder();
        
        String query = qb.DROP().TABLE().IF().EXISTS().INPUT("libros").build();
        
        return connection.prepareStatement(query);
    }

    public PreparedStatement getById(Connection connection) throws SQLException {
        QueryBuilder qb = new QueryBuilder();
        
        String query = qb.SELECT().EVERYTHING().FROM().INPUT("libros").WHERE()
            .INPUT("id").EQUALS().INPUT("?").build();
        
        return connection.prepareStatement(query);
    }   
}
