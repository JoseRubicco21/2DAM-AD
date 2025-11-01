package com.ud2_at1.services.database.query_builder;

import com.ud2_at1.services.database.query_builder.dialects.*;

public class QueryBuilderFactory {
    
    public enum DatabaseType {
        MYSQL, POSTGRESQL, SQLSERVER
    }
    
    public static QueryBuilder create(DatabaseType type) {
        switch (type) {
            case MYSQL:
                return new QueryBuilder(new MySQLDialect());
            case POSTGRESQL:
                return new QueryBuilder(new PostgreSQLDialect());
            case SQLSERVER:
                return new QueryBuilder(new SQLServerDialect());
            default:
                return new QueryBuilder(new MySQLDialect());
        }
    }
    
    public static QueryBuilder createMySQL() {
        return new QueryBuilder(new MySQLDialect());
    }
    
    public static QueryBuilder createPostgreSQL() {
        return new QueryBuilder(new PostgreSQLDialect());
    }
    
    public static QueryBuilder createSQLServer() {
        return new QueryBuilder(new SQLServerDialect());
    }
}