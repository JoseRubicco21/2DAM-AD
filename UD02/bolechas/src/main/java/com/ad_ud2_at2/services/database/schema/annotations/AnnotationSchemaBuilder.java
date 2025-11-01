package com.ad_ud2_at2.services.database.schema.annotations;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ad_ud2_at2.annotations.Column;
import com.ad_ud2_at2.annotations.Table;
import com.ad_ud2_at2.services.database.query_builder.QueryBuilder;
import com.ad_ud2_at2.services.database.query_builder.QueryBuilderFactory;
import com.ad_ud2_at2.services.logger.Logger;

public class AnnotationSchemaBuilder {
    
    private final String sqlFlavor;
    
    public AnnotationSchemaBuilder(String sqlFlavor) {
        this.sqlFlavor = sqlFlavor;
    }
    
    private QueryBuilder createQueryBuilder() {
        return QueryBuilderFactory.createMySQL(); // For now, always MySQL
    }
    
    public PreparedStatement createTableStatement(Class<?> modelClass, Connection connection) throws SQLException {
        // Read the @Table annotation
        Table tableAnnotation = modelClass.getAnnotation(Table.class);
        if (tableAnnotation == null) {
            throw new IllegalArgumentException("Class " + modelClass.getSimpleName() + " is not annotated with @Table");
        }
        String tableName = tableAnnotation.name(); // "clientes", "productos", "pedidos"
        
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
                if (column.length() > 0) {
                    qbModel.INPUT(column.type() + "(" + column.length() + ")");
                } else {
                    qbModel.INPUT(column.type());
                }
                
                // Add constraints
                if (column.primaryKey()) {
                    qbModel.INPUT("PRIMARY KEY");
                }
                
                if (column.autoIncrement()) {
                    qbModel.INPUT("AUTO_INCREMENT");
                }
                
                if (column.unique()) {
                    qbModel.INPUT("UNIQUE");
                }
                
                if (!column.nullable()) {
                    qbModel.INPUT("NOT NULL");
                }
                
                first = false;
            }
        }
        
        // Build final query: CREATE TABLE tableName (columns)
        String columnsQuery = qbModel.CONSUME(); // Use CONSUME instead of build
        String finalQuery = qb.CREATE().TABLE().INPUT(tableName).openParenthesis()
                             .INPUT(columnsQuery).closeParenthesis().CONSUME(); // Use CONSUME to not add semicolon
        
        Logger.query("Creating table: " + finalQuery);
        return connection.prepareStatement(finalQuery);
    }
    
    public PreparedStatement dropTableStatement(Class<?> modelClass, Connection connection) throws SQLException {
        Table tableAnnotation = modelClass.getAnnotation(Table.class);
        if (tableAnnotation == null) {
            throw new IllegalArgumentException("Class " + modelClass.getSimpleName() + " is not annotated with @Table");
        }
        
        QueryBuilder qb = createQueryBuilder();
        String query = qb.DROP().TABLE().IF().EXISTS().INPUT(tableAnnotation.name()).CONSUME();
        return connection.prepareStatement(query);
    }
}