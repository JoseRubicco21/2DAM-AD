package com.ud2_at1.models.generic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.management.Query;

import com.ud2_at1.services.database.query_builder.QueryBuilder;
import com.ud2_at1.services.database.query_builder.QueryBuilderFactory;
import com.ud2_at1.services.logger.Logger;

public class DatabaseUser {
    private String username;
    private String password;

    public DatabaseUser(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PreparedStatement createUserStatement(Connection connection) {
        QueryBuilder qb = QueryBuilderFactory.createMySQL();
        String user = qb.PARAM(this.username).AT_SIGN().PARAM("%").CONSUME();
        String query = qb.CREATE().USER().TRIM(user).IDENTIFIED().BY().PARAM(this.password).build();
        Logger.query("Query: " + query);
        try {
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
        return null;
    }
    
}
