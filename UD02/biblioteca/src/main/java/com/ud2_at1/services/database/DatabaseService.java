package com.ud2_at1.services.database;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.ud2_at1.annotations.Column;  // ADD THIS MISSING IMPORT
import com.ud2_at1.annotations.Table;
import com.ud2_at1.controllers.intefaces.StatementOperation;
import com.ud2_at1.models.generic.Database;
import com.ud2_at1.models.generic.DatabaseUser;
import com.ud2_at1.services.connectors.MySQLConnector;
import com.ud2_at1.services.connectors.exceptions.MySQLConnectorException;
import com.ud2_at1.services.database.query_builder.QueryBuilder;
import com.ud2_at1.services.database.query_builder.QueryBuilderFactory;
import com.ud2_at1.services.database.schema.annotations.AnnotationSchemaBuilder;
import com.ud2_at1.services.loaders.ConfigLoader;
import com.ud2_at1.services.logger.Logger;

// Import your model classes here
import com.ud2_at1.models.Libro;
import com.ud2_at1.models.Autor;

public class DatabaseService {
    
    private static final List<Class<?>> MODEL_CLASSES = Arrays.asList(
        Libro.class,
        Autor.class
    );
    
    public static boolean executeStatement(StatementOperation operation) throws MySQLConnectorException, SQLException {
        boolean result;
        try (Connection connection = MySQLConnector.getConnectionAsRoot();
            PreparedStatement pss = operation.statementOperation(connection)) {
            result = pss.execute();
        } catch (MySQLConnectorException e) {
            e.displayExceptionMessage();
            throw e;
        } catch (SQLException e) {
            throw new MySQLConnectorException(e.getMessage());
        } catch (UnsupportedOperationException e) {
            Logger.error("Unsupported operation: " + e.getMessage());
            throw e;
        }
        return result;
    }

    /**
     * Execute statement operations on the specific database
     * This method uses a direct connection to the database for operations that need to work on tables
     */
    public static boolean executeStatementOnDatabase(StatementOperation operation) throws MySQLConnectorException, SQLException {
        boolean result;
        try (Connection connection = MySQLConnector.getConnectionToDatabaseAsRoot();
            PreparedStatement pss = operation.statementOperation(connection)) {
            result = pss.execute();
        } catch (MySQLConnectorException e) {
            e.displayExceptionMessage();
            throw e;
        } catch (SQLException e) {
            throw new MySQLConnectorException(e.getMessage());
        } catch (UnsupportedOperationException e) {
            Logger.error("Unsupported operation: " + e.getMessage());
            throw e;
        }
        return result;
    }

    public static Database initializeDatabase() {
        ConfigLoader.getInstance();
        Logger.info("Starting database initialization...");
        
        DatabaseUser user = new DatabaseUser(
            ConfigLoader.get("mysql.user"), 
            ConfigLoader.get("mysql.password")
        );
        
        Database db = new Database(
            ConfigLoader.get("mysql.dbname"),
            ConfigLoader.get("mysql.charset"),
            ConfigLoader.get("mysql.charset.collation"),
            user
        );

        // Database initialization steps
        executeInitStep("Checking databases", DatabaseService::showDatabaseStatement);
        executeInitStep("Creating database", db::createDatabaseSQLStatement, 
                       "Database created successfully");
        executeInitStep("Creating user", user::createUserStatement, 
                       "User created successfully");
        executeInitStep("Granting privileges", db::grantPrivilegesSQLStatement, 
                       "Privileges granted successfully");
        executeInitStep("Flushing privileges", db::flushPrivilegesSQLStatement, 
                       "Privileges flushed successfully");

        // Initialize schema using the same connection
        initializeSchema(db);

        // Test database connectivity for DAO operations
        if (testDatabaseConnectivity()) {
            Logger.success("Database is ready for operations!");
        } else {
            Logger.error("Database connectivity test failed!");
        }

        Logger.success("Database initialization completed!");
        return db;
    }

    /**
     * Initialize database schema by creating tables from annotated model classes
     * Uses a connection to the specific database for schema operations
     */
    private static void initializeSchema(Database database) {
        Logger.info("Starting schema initialization...");
        
        AnnotationSchemaBuilder schemaBuilder = new AnnotationSchemaBuilder("mysql");
        
        try (Connection connection = MySQLConnector.getConnectionToDatabaseAsRoot()) {
            // We're already connected to the specific database, no need for USE statement
            Logger.success("Connected directly to database: " + database.getName());
            
            // Verify database selection
            verifyDatabaseSelection(connection, database.getName());
            createTables(schemaBuilder, connection);
            addConstraints(connection); 
            
        } catch (MySQLConnectorException | SQLException e) {
            Logger.error("Failed to initialize schema: " + e.getMessage());
        }
    }

    /**
     * Verify that we're using the correct database
     */
    private static void verifyDatabaseSelection(Connection connection, String expectedDatabase) {
        try {
            QueryBuilder qb = QueryBuilderFactory.createMySQL();
            String query = qb.SELECT().INPUT("DATABASE()").build();
            
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                
                if (rs.next()) {
                    String currentDatabase = rs.getString(1);
                    Logger.info("Current database: " + currentDatabase);
                    
                    if (!expectedDatabase.equals(currentDatabase)) {
                        Logger.warning("Expected database: " + expectedDatabase + ", but using: " + currentDatabase);
                    } else {
                        Logger.success("Successfully connected to database: " + currentDatabase);
                    }
                }
            }
        } catch (SQLException e) {
            Logger.error("Failed to verify database selection: " + e.getMessage());
        }
    }

    /**
     * Test database connectivity and verify we can connect to the specific database
     */
    public static boolean testDatabaseConnectivity() {
        Logger.info("Testing database connectivity...");
        
        try (Connection connection = MySQLConnector.getConnectionToDatabase()) {
            QueryBuilder qb = QueryBuilderFactory.createMySQL();
            String query = qb.SELECT().INPUT("DATABASE() as current_db, VERSION() as version").build();
            
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                
                if (rs.next()) {
                    String currentDb = rs.getString("current_db");
                    String version = rs.getString("version");
                    Logger.success("Database connectivity test successful!");
                    Logger.info("Connected to database: " + currentDb);
                    Logger.info("MySQL version: " + version);
                    return true;
                }
            }
        } catch (MySQLConnectorException | SQLException e) {
            Logger.error("Database connectivity test failed: " + e.getMessage());
            return false;
        }
        
        return false;
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
                    stmt.execute(); // REMOVED unused result variable
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
    private static void addConstraints(Connection connection) { // REMOVED unused schemaBuilder parameter
        Logger.info("Adding constraints for tables...");
        
        for (Class<?> modelClass : MODEL_CLASSES) {
            try {
                Logger.info("Adding constraints for model: " + modelClass.getSimpleName());
                
                // Add unique constraints
                addUniqueConstraintsForModel(modelClass, connection);
                
                // Add foreign key constraints
                addForeignKeyConstraintsForModel(modelClass, connection);
                
                Logger.success("Constraints added for model: " + modelClass.getSimpleName());
                
            } catch (SQLException | IllegalArgumentException e) {
                Logger.error("Failed to add constraints for " + modelClass.getSimpleName() + ": " + e.getMessage());
            }
        }
    }

    /**
     * Add unique constraints for a specific model class based on annotations
     */
    private static void addUniqueConstraintsForModel(Class<?> modelClass, Connection connection) throws SQLException {
        if (!modelClass.isAnnotationPresent(Table.class)) {
            return;
        }

        Table tableAnnotation = modelClass.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();
        
        Field[] fields = modelClass.getDeclaredFields();
        
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                
                if (column.unique() && !column.primaryKey()) {
                    addUniqueConstraint(tableName, column.name(), connection);
                }
            }
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
     * Add a unique constraint for a specific column
     */
    private static void addUniqueConstraint(String tableName, String columnName, Connection connection) throws SQLException {
        QueryBuilder qb = QueryBuilderFactory.createMySQL();
        String constraintName = "uk_" + tableName + "_" + columnName;
        
        String query = qb.ALTER().TABLE().INPUT(tableName)
                        .ADD().CONSTRAINT().INPUT(constraintName)
                        .UNIQUE().INPUT("(" + columnName + ")")
                        .build();
        
        Logger.query("Add unique constraint: " + query);
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.execute();
            Logger.info("Unique constraint added: " + constraintName);
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate key name")) {
                Logger.warning("Unique constraint " + constraintName + " already exists, skipping...");
            } else {
                throw e;
            }
        }
    }

    /**
     * Add foreign key constraint based on Column annotation
     */
    private static void addForeignKeyFromAnnotation(String tableName, Column column, Connection connection) throws SQLException {
        String columnName = column.name();
        String references = column.references(); // e.g., "autores(id)"
        
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
     * Improved addForeignKeyStatement method that properly handles the foreign key creation
     */
    public static void addForeignKeyStatement(String tableName, String columnName, String referencedTable, String referencedColumn, Connection connection) throws SQLException {
        QueryBuilder qb = QueryBuilderFactory.createMySQL();
        String constraintName = "fk_" + tableName + "_" + columnName;
        
        String query = qb.ALTER().TABLE().INPUT(tableName)
                        .ADD().CONSTRAINT().INPUT(constraintName)
                        .FOREIGN().KEY().INPUT("(" + columnName + ")")
                        .REFERENCES().INPUT(referencedTable + "(" + referencedColumn + ")")
                        .build();
        
        Logger.query("Add foreign key constraint: " + query);
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.execute();
            Logger.info("Foreign key constraint added: " + constraintName);
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate key name")) {
                Logger.warning("Foreign key constraint " + constraintName + " already exists, skipping...");
            } else if (e.getMessage().contains("Cannot add foreign key constraint")) {
                Logger.error("Cannot add foreign key constraint " + constraintName + ": " + e.getMessage());
                Logger.warning("Make sure the referenced table '" + referencedTable + "' exists and has the column '" + referencedColumn + "'");
            } else {
                throw e;
            }
        }
    }

    private static void executeInitStep(String stepName, StatementOperation operation) {
        executeInitStep(stepName, operation, null);
    }

    private static void executeInitStep(String stepName, StatementOperation operation, String successMessage) {
        try {
            Logger.info(stepName + "...");
            executeStatement(operation); // REMOVED unused result variable
            
            if (successMessage != null) {
                Logger.success(successMessage);
            }
            
        } catch (MySQLConnectorException | SQLException e) {
            if (stepName.contains("user") && e.getMessage().contains("CREATE USER failed")) {
                Logger.warning("User already exists, continuing...");
            } else if (stepName.contains("database") && e.getMessage().contains("database exists")) {
                Logger.warning("Database already exists, continuing...");
            } else {
                Logger.error("Error during " + stepName.toLowerCase() + ": " + e.getMessage());
            }
        }
    }

    public static PreparedStatement showDatabaseStatement(Connection connection) {
        QueryBuilder qb = QueryBuilderFactory.createMySQL();
        String query = qb.SHOW().DATABASES().build();
        try {
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            Logger.error("Error creating show databases statement: " + e.getMessage());
        }
        return null;
    }

}
