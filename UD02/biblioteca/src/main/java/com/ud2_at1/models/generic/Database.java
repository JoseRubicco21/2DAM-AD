package com.ud2_at1.models.generic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ud2_at1.services.database.query_builder.QueryBuilder;
import com.ud2_at1.services.database.query_builder.QueryBuilderFactory;
import com.ud2_at1.services.logger.Logger;

public class Database {
    private String name;
    private String charset;
    private String collationType;
    private DatabaseUser owner;

    public Database(String name, String charset, String collationType, DatabaseUser owner){
        this.name = name;
        this.charset = charset;
        this.collationType = collationType;
        this.owner = owner;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCharset() { return charset; }
    public void setCharset(String charset) { this.charset = charset; }
    public String getCollationType() { return collationType; }
    public void setCollationType(String collationType) { this.collationType = collationType; }
    public DatabaseUser getOwner() { return owner; }
    public void setOwner(DatabaseUser owner) { this.owner = owner; }

    // Check if database exists
    public boolean databaseExists(Connection connection) {
        QueryBuilder qb = QueryBuilderFactory.createMySQL();
        String query = qb.SELECT().INPUT("SCHEMA_NAME").FROM().INPUT("INFORMATION_SCHEMA.SCHEMATA")
                        .WHERE().INPUT("SCHEMA_NAME").EQUALS().PARAM(this.name).build();
        
        Logger.query("Checking database existence: " + query);
        
        try (PreparedStatement pss = connection.prepareStatement(query);
             ResultSet rs = pss.executeQuery()) {
            
            boolean exists = rs.next();
            Logger.info("Database '" + this.name + "' exists: " + exists);
            return exists;
            
        } catch (SQLException e) {
            Logger.error("Error checking database existence: " + e.getMessage());
            return false;
        }
    }

    // Check if user exists
    public boolean userExists(Connection connection) {
        if (this.owner == null) {
            Logger.warning("No owner specified");
            return false;
        }
        
        QueryBuilder qb = QueryBuilderFactory.createMySQL();
        String query = qb.SELECT().INPUT("User").FROM().INPUT("mysql.user")
                        .WHERE().INPUT("User").EQUALS().PARAM(this.owner.getUsername())
                        .build();
        
        Logger.query("Checking user existence: " + query);
        
        try (PreparedStatement pss = connection.prepareStatement(query);
             ResultSet rs = pss.executeQuery()) {
            
            boolean exists = rs.next();
            Logger.info("User '" + this.owner.getUsername() + "' exists: " + exists);
            return exists;
            
        } catch (SQLException e) {
            Logger.error("Error checking user existence: " + e.getMessage());
            return false;
        }
    }

    // Create database only if it doesn't exist
    public PreparedStatement createDatabaseSQLStatement(Connection connection) {
        QueryBuilder qb = QueryBuilderFactory.createMySQL();
        String query = qb.CREATE().DATABASE().IF_NOT_EXISTS().INPUT(this.name)
                        .INPUT("CHARACTER SET").INPUT(this.charset)
                        .INPUT("COLLATE").INPUT(this.collationType)
                        .build();
        
        Logger.query("Create database query: " + query);
        
        try {
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            Logger.error("Error preparing create database statement: " + e.getMessage());
        }
        return null;
    }

    public PreparedStatement dropDatabaseSQLStatement(Connection connection) {
        QueryBuilder qb = QueryBuilderFactory.createMySQL();
        String query = qb.DROP().DATABASE().IF().EXISTS().INPUT(this.name).build();
        
        Logger.query("Drop database query: " + query);
        
        try {
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            Logger.error("Error preparing drop database statement: " + e.getMessage());
        }
        return null;
    }

    public PreparedStatement useDatabaseSQLStatement(Connection connection) {
        QueryBuilder qb = QueryBuilderFactory.createMySQL();
        String query = qb.USE().INPUT(this.name).build();
        
        Logger.query("Use database query: " + query);
        
        try {
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            Logger.error("Error preparing use database statement: " + e.getMessage());
        }
        return null;
    }

    public PreparedStatement grantPrivilegesSQLStatement(Connection connection) {
        if (this.owner == null) {
            Logger.error("No owner specified for granting privileges");
            return null;
        }
        
        QueryBuilder qb = QueryBuilderFactory.createMySQL();
        String userHost = "'" + this.owner.getUsername() + "'@'%'"; // Fixed format
        String query = qb.GRANT().ALL().PRIVILEGES().ON()
                        .INPUT(this.name + ".*")
                        .TO().INPUT(userHost)
                        .build();
        
        Logger.query("Grant privileges query: " + query);
        
        try {
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            Logger.error("Error preparing grant privileges statement: " + e.getMessage());
        }
        return null;
    }

    public PreparedStatement flushPrivilegesSQLStatement(Connection connection) {
        QueryBuilder qb = QueryBuilderFactory.createMySQL();
        String query = qb.FLUSH().PRIVILEGES().build();
        
        Logger.query("Flush privileges query: " + query);
        
        try {
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            Logger.error("Error preparing flush privileges statement: " + e.getMessage());
        }
        return null;
    }

    // Smart creation methods that check before creating
    public boolean createDatabaseIfNotExists(Connection connection) {
        if (databaseExists(connection)) {
            Logger.info("Database '" + this.name + "' already exists, skipping creation");
            return true;
        }
        
        try (PreparedStatement pss = createDatabaseSQLStatement(connection)) {
            boolean result = pss.execute();
            Logger.success("Database '" + this.name + "' created successfully");
            return result;
        } catch (SQLException e) {
            Logger.error("Failed to create database: " + e.getMessage());
            return false;
        }
    }

    public boolean createUserIfNotExists(Connection connection) {
        if (this.owner == null) {
            Logger.error("No owner specified");
            return false;
        }
        
        if (userExists(connection)) {
            Logger.info("User '" + this.owner.getUsername() + "' already exists, skipping creation");
            return true;
        }
        
        try (PreparedStatement pss = this.owner.createUserStatement(connection)) {
            boolean result = pss.execute();
            Logger.success("User '" + this.owner.getUsername() + "' created successfully");
            return result;
        } catch (SQLException e) {
            Logger.error("Failed to create user: " + e.getMessage());
            return false;
        }
    }
}

