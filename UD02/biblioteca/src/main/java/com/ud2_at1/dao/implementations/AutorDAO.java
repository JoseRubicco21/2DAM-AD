package com.ud2_at1.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.ud2_at1.dao.Dao;
import com.ud2_at1.models.Autor;
import com.ud2_at1.services.database.query_builder.QueryBuilder;

public class AutorDAO implements Dao<Autor, Integer> {

    @Override
    public Autor get(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public List<Autor> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public boolean save(Autor objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public boolean delete(Autor obj) {
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
    public void update(Autor obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

       public PreparedStatement createTableStatement(Connection connection) throws SQLException {
        QueryBuilder qb = new QueryBuilder();
        QueryBuilder qbModel = new QueryBuilder();
        
        String modelQuery = qbModel
            .INPUT("id").PRIMARY().KEY().AUTO_INCREMENT()
            .INPUT("first_name").VARCHAR(100).NOTNULL()
            .INPUT("last_name").VARCHAR(100).NOTNULL()
            .build();
        
        String query = qb.CREATE().TABLE().INPUT("autores").MODEL(modelQuery).build();
        
        return connection.prepareStatement(query);
    }
 
    public PreparedStatement deleteTableStatement(Connection connection) throws SQLException {
        QueryBuilder qb = new QueryBuilder();
        
        String query = qb.DROP().TABLE().IF().EXISTS().INPUT("libros").build();
        
        return connection.prepareStatement(query);
    }
}
