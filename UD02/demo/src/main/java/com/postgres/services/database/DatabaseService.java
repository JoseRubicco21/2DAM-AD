package com.postgres.services.database;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.postgres.annotations.Column;
import com.postgres.annotations.Table;
import com.postgres.models.Alumno;
import com.postgres.services.connectors.SQLConnector;
import com.postgres.services.connectors.exceptions.ConnectorException;
import com.postgres.services.database.query_builder.QueryBuilder;
import com.postgres.services.database.query_builder.QueryBuilderFactory;
import com.postgres.services.database.schema.annotations.AnnotationSchemaBuilder;
import com.postgres.services.loaders.ConfigLoader;
import com.postgres.services.logger.Logger;



public class DatabaseService {
    
    private static final List<Class<?>> MODEL_CLASSES = Arrays.asList(
        Alumno.class
    );
    
    public static boolean executeStatement(String query) throws ConnectorException, SQLException {
        try (Connection connection = SQLConnector.getConnectionAsRoot()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException e) {
            Logger.error("Failed to execute statement: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Execute statement operations on the specific database
     * This method uses a direct connection to the database for operations that need to work on tables
     */
    public static boolean executeStatementOnDatabase(String query) throws ConnectorException, SQLException {
        try (Connection connection = SQLConnector.getConnectionToDatabaseAsRoot()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException e) {
            Logger.error("Failed to execute statement on database: " + e.getMessage());
            throw e;
        }
    }

    public static void initializeDatabase() {
        Logger.info("Starting database initialization...");
        
        try {
            // Create database if it doesn't exist
            //createDatabaseIfNotExists();
            
            // Create user and grant privileges
            //createUserAndGrantPrivileges();
            
            // Initialize schema (tables)
            initializeSchema();
            
            Logger.success("Database initialization completed successfully");
            
        } catch (Exception e) {
            Logger.error("Database initialization failed: " + e.getMessage());
        }
    }


    

    /**
     * Create database if it doesn't exist
     */
    private static void createDatabaseIfNotExists() throws ConnectorException, SQLException {
        Logger.info("Creating database if not exists...");
        
        ConfigLoader.getInstance();
        String databaseName = ConfigLoader.get("postgres.dbname");
        
        QueryBuilder qb = QueryBuilderFactory.createPostgreSQL();
        String query = qb.CREATE().DATABASE()
                        .IF().NOT().EXISTS().INPUT(databaseName).CONSUME();
        
        executeStatement(query);
        Logger.success("Database created or already exists: " + databaseName);
    }

    /**
     * Create database user and grant privileges
     */
    private static void createUserAndGrantPrivileges() throws ConnectorException, SQLException {
        Logger.info("Creating database user and granting privileges...");
        
        ConfigLoader.getInstance();
        String username = ConfigLoader.get("postgres.user");
        String password = ConfigLoader.get("postgres.password");
        String databaseName = ConfigLoader.get("postgres.dbname");
        
        QueryBuilder qb = QueryBuilderFactory.createPostgreSQL();
        
        try {
            // Create user if not exists (MySQL 5.7+ syntax)
            String createUserQuery = qb.CREATE().USER().IF().NOT().EXISTS()
                                    .INPUT("'" + username + "'@'%'")
                                    .IDENTIFIED().BY().INPUT("'" + password + "'").CONSUME();
            
            executeStatement(createUserQuery);
            Logger.success("User created or already exists: " + username);
            
            // Reset query builder for next query
            qb.reset();
            
            // Grant all privileges on the specific database
            String grantQuery = qb.GRANT().ALL().PRIVILEGES()
                              .ON().INPUT(databaseName + ".*")
                              .TO().INPUT("'" + username + "'@'%'").CONSUME();
            
            executeStatement(grantQuery);
            Logger.success("Privileges granted to user: " + username + " on database: " + databaseName);
            
            // Reset query builder for flush
            qb.reset();
            
            // Flush privileges to ensure changes take effect
            String flushQuery = qb.FLUSH().PRIVILEGES().CONSUME();
            executeStatement(flushQuery);
            Logger.success("Privileges flushed successfully");
            
        } catch (SQLException e) {
            // If user already exists with different password, this might fail
            // Try to grant privileges anyway
            Logger.warning("User creation might have failed (user may already exist): " + e.getMessage());
            
            try {
                qb.reset();
                String grantQuery = qb.GRANT().ALL().PRIVILEGES()
                                  .ON().INPUT(databaseName + ".*")
                                  .TO().INPUT("'" + username + "'@'%'").CONSUME();
                
                executeStatement(grantQuery);
                Logger.success("Privileges granted to existing user: " + username);
                
                qb.reset();
                String flushQuery = qb.FLUSH().PRIVILEGES().CONSUME();
                executeStatement(flushQuery);
                Logger.success("Privileges flushed successfully");
                
            } catch (SQLException e2) {
                Logger.error("Failed to grant privileges: " + e2.getMessage());
                throw e2;
            }
        }
    }

    /**
     * Initialize database schema by creating tables from annotated model classes
     * Uses a connection to the specific database for schema operations
     */
    private static void initializeSchema() {
        Logger.info("Starting schema initialization...");
        
        AnnotationSchemaBuilder schemaBuilder = new AnnotationSchemaBuilder("postgresql");
        
        try (Connection connection = SQLConnector.getConnectionToDatabaseAsRoot()) {
            Logger.success("Connected directly to database");
            
            createTables(schemaBuilder, connection);
            addConstraints(connection); 
            
        } catch (ConnectorException | SQLException e) {
            Logger.error("Failed to initialize schema: " + e.getMessage());
        }
    }

    /**
     * Create tables from model classes using the provided connection
     */
    private static void createTables(AnnotationSchemaBuilder schemaBuilder, Connection connection) {
        Logger.info("Creating tables from model classes...");
        
        for (Class<?> modelClass : MODEL_CLASSES) {
            try {
                Logger.info("Creating table for model: " + modelClass.getSimpleName());
                
                PreparedStatement stmt = schemaBuilder.createTableStatement(modelClass, connection);
                if (stmt != null) {
                    stmt.execute();
                    stmt.close();
                    Logger.success("Table created for model: " + modelClass.getSimpleName());
                } else {
                    Logger.error("Failed to create statement for model: " + modelClass.getSimpleName());
                }
                
            } catch (SQLException | IllegalArgumentException e) {
                Logger.error("Failed to create table for " + modelClass.getSimpleName() + ": " + e.getMessage());
            }
        }
    }

    /**
     * Add constraints from model classes using the provided connection
     * This includes unique constraints, foreign keys, etc.
     */
    private static void addConstraints(Connection connection) {
        Logger.info("Adding constraints...");
        
        try {
            for (Class<?> modelClass : MODEL_CLASSES) {
                addForeignKeyConstraintsForModel(modelClass, connection);
            }
            Logger.success("Constraints added successfully");
        } catch (SQLException e) {
            Logger.error("Failed to add constraints: " + e.getMessage());
        }
    }

    /**
     * Add foreign key constraints for a specific model class based on annotations
     */
    private static void addForeignKeyConstraintsForModel(Class<?> modelClass, Connection connection) throws SQLException {
        if (!modelClass.isAnnotationPresent(Table.class)) {
            return;
        }

        Table tableAnnotation = modelClass.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();
        
        Field[] fields = modelClass.getDeclaredFields();
        
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                
                if (column.foreignKey() && !column.references().isEmpty()) {
                    addForeignKeyFromAnnotation(tableName, column, connection);
                }
            }
        }
    }

    /**
     * Add foreign key constraint based on Column annotation
     */
    private static void addForeignKeyFromAnnotation(String tableName, Column column, Connection connection) throws SQLException {
        String columnName = column.name();
        String references = column.references(); // e.g., "productos(id)"
        
        // Parse the references string to get table and column
        String[] parts = references.split("\\(");
        if (parts.length != 2) {
            Logger.error("Invalid references format: " + references + ". Expected format: 'table(column)'");
            return;
        }
        
        String referencedTable = parts[0].trim();
        String referencedColumn = parts[1].replace(")", "").trim();
        
        addForeignKeyStatement(tableName, columnName, referencedTable, referencedColumn, connection);
    }

    /**
     * Add foreign key constraint using ALTER TABLE statement
     */
    private static void addForeignKeyStatement(String tableName, String columnName, String referencedTable, String referencedColumn, Connection connection) throws SQLException {
        try {
            QueryBuilder qb = QueryBuilderFactory.createPostgreSQL();
            String constraintName = "fk_" + tableName + "_" + columnName;
            
            String query = qb.ALTER()
                            .TABLE().INPUT(tableName)
                            .ADD()
                            .CONSTRAINT()
                            .INPUT(constraintName)
                            .FOREIGN()
                            .KEY()
                            .openParenthesis().INPUT(columnName).closeParenthesis()
                            .REFERENCES()
                            .INPUT(referencedTable).openParenthesis().INPUT(referencedColumn).closeParenthesis()
                            .ON().DELETE().CASCADE()
                            .CONSUME();
            
            Logger.query("Adding foreign key with CASCADE: " + query);
            
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.execute();
            stmt.close();
            
            Logger.success("Foreign key constraint added with CASCADE: " + constraintName);
            
        } catch (SQLException e) {
            Logger.warning("Failed to add foreign key constraint: " + e.getMessage());
        }
    }

    /**
     * Test database connectivity and verify we can connect to the specific database
     */
    public static boolean testDatabaseConnectivity() {
        try (Connection connection = SQLConnector.getConnectionToDatabaseAsRoot()) {
            Logger.success("Database connectivity test passed");
            return true;
        } catch (ConnectorException | SQLException e) {
            Logger.error("Database connectivity test failed: " + e.getMessage());
            return false;
        }
    }
}