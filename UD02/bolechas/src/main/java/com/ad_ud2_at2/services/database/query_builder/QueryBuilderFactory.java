package com.ad_ud2_at2.services.database.query_builder;

import com.ad_ud2_at2.services.database.query_builder.dialects.*;

public class QueryBuilderFactory {
    
    public enum DatabaseType {
        MYSQL, POSTGRESQL, SQLSERVER
    }
    
    public static QueryBuilder create(DatabaseType type) {
        switch (type) {
            case MYSQL:
                return new QueryBuilder(new MySQLDialect());
            case POSTGRESQL:
                return new QueryBuilder(new MySQLDialect()); // Simplified for now
            case SQLSERVER:
                return new QueryBuilder(new MySQLDialect()); // Simplified for now
            default:
                return new QueryBuilder(new MySQLDialect());
        }
    }
    
    public static QueryBuilder createMySQL() {
        return new QueryBuilder(new MySQLDialect());
    }
    
    public static QueryBuilder createPostgreSQL() {
        return new QueryBuilder(new MySQLDialect()); // Simplified for now
    }
    
    public static QueryBuilder createSQLServer() {
        return new QueryBuilder(new MySQLDialect()); // Simplified for now
    }
}