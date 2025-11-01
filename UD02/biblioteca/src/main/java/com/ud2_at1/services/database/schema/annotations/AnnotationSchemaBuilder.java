package com.ud2_at1.services.database.schema.annotations;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ud2_at1.annotations.Column;
import com.ud2_at1.annotations.Table;
import com.ud2_at1.services.database.query_builder.QueryBuilder;
import com.ud2_at1.services.database.query_builder.QueryBuilderFactory;
import com.ud2_at1.services.logger.Logger;

public class AnnotationSchemaBuilder {
    
    private final String sqlFlavor;
    
    public AnnotationSchemaBuilder(String sqlFlavor) {
        this.sqlFlavor = sqlFlavor;
    }
    
    private QueryBuilder createQueryBuilder() {
        switch (sqlFlavor.toLowerCase()) {
            case "mysql":
                return QueryBuilderFactory.createMySQL();
            case "postgresql":
                return QueryBuilderFactory.createPostgreSQL();
            default:
                throw new IllegalArgumentException("Unsupported SQL flavor: " + sqlFlavor);
        }
    }
    
    public PreparedStatement createTableStatement(Class<?> modelClass, Connection connection) throws SQLException {
        // Read the @Table annotation
        Table tableAnnotation = modelClass.getAnnotation(Table.class);
        if (tableAnnotation == null) {
            throw new IllegalArgumentException("Class " + modelClass.getSimpleName() + " is not annotated with @Table");
        }
        String tableName = tableAnnotation.name(); // "libros"
        
        QueryBuilder qb = createQueryBuilder();
        QueryBuilder qbModel = createQueryBuilder();
        
        // Get all fields with @Column annotations
        Field[] fields = modelClass.getDeclaredFields();
        boolean first = true;
        
        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                if (!first) qbModel.INPUT(",");
                
                // Build: "id INT PRIMARY KEY AUTO_INCREMENT"
                qbModel.INPUT(column.name()); // "id"
                
                // Handle data type with length properly
                if (column.length() > 0 && (column.type().equals("VARCHAR") || column.type().equals("CHAR"))) {
                    qbModel.INPUT(column.type() + "(" + column.length() + ")");
                } else {
                    qbModel.INPUT(column.type()); // "INT"
                }
                
                if (column.primaryKey()) {
                    qbModel.PRIMARY().KEY(); // "PRIMARY KEY"
                }
                
                if (column.autoIncrement()) {
                    qbModel.AUTO_INCREMENT(); // "AUTO_INCREMENT"
                }
                
                if (!column.nullable()) {
                    qbModel.NOTNULL(); // "NOT NULL"
                }
                
                if (column.unique()) {
                    qbModel.UNIQUE(); // "UNIQUE"
                }
                
                first = false;
            }
        }
        
        String modelQuery = qbModel.CONSUME();
        String query = qb.CREATE().TABLE().IF_NOT_EXISTS().INPUT(tableName).MODEL(modelQuery).build();
        Logger.query(query);
        return connection.prepareStatement(query);
    }
    
    public PreparedStatement dropTableStatement(Class<?> modelClass, Connection connection) throws SQLException {
        Table tableAnnotation = modelClass.getAnnotation(Table.class);
        if (tableAnnotation == null) {
            throw new IllegalArgumentException("Class " + modelClass.getSimpleName() + " is not annotated with @Table");
        }
        
        QueryBuilder qb = createQueryBuilder();
        String query = qb.DROP().TABLE().IF().EXISTS().INPUT(tableAnnotation.name()).build();
        return connection.prepareStatement(query);
    }
    
    public PreparedStatement createIndexesStatement(Class<?> modelClass, Connection connection) throws SQLException {
        Table tableAnnotation = modelClass.getAnnotation(Table.class);
        if (tableAnnotation == null) {
            throw new IllegalArgumentException("Class " + modelClass.getSimpleName() + " is not annotated with @Table");
        }
        
        QueryBuilder qb = createQueryBuilder();
        String query = qb.CREATE().INDEX().IF_NOT_EXISTS()
            .INPUT("idx_" + tableAnnotation.name() + "_id")
            .ON().INPUT(tableAnnotation.name()).INPUT("(id)").build();
        return connection.prepareStatement(query);
    }
    
    public PreparedStatement addConstraintsStatement(Class<?> modelClass, Connection connection) throws SQLException {
        QueryBuilder qb = createQueryBuilder();
        String query = qb.SELECT().INPUT("1").build(); // No-op query
        return connection.prepareStatement(query);
    }

    
}
