package com.ad_ud2_at2.services.database.query_builder;

import com.ad_ud2_at2.services.database.query_builder.dialects.*;

/**
 * Factory para la creación de instancias QueryBuilder con diferentes dialectos SQL.
 * 
 * Esta clase implementa el patrón Factory Method para simplificar la creación
 * de QueryBuilder configurados con dialectos específicos de diferentes motores
 * de base de datos. Proporciona métodos estáticos para crear instancias
 * preconfiguradas y centraliza la lógica de selección de dialectos.
 * 
 * El patrón Factory facilita el cambio entre diferentes implementaciones
 * de dialectos sin necesidad de modificar el código cliente y asegura
 * que cada QueryBuilder tenga el dialecto correcto configurado.
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see QueryBuilder
 * @see SQLDialect
 * @see MySQLDialect
 * 
 * @example
 * <pre>
 * // Usando el factory con enum
 * QueryBuilder mysqlBuilder = QueryBuilderFactory.create(DatabaseType.MYSQL);
 * 
 * // Usando métodos específicos
 * QueryBuilder postgresBuilder = QueryBuilderFactory.createPostgreSQL();
 * 
 * // Construyendo consulta
 * String sql = mysqlBuilder.SELECT().ASTERISK().FROM().INPUT("usuarios").build();
 * </pre>
 */
public class QueryBuilderFactory {
    
    /**
     * Enumeración que define los tipos de base de datos soportados.
     * 
     * Esta enum proporciona una lista tipo-segura de los motores de base de datos
     * para los cuales el factory puede crear QueryBuilders específicos.
     * Facilita la expansión futura para soportar nuevos dialectos.
     */
    public enum DatabaseType {
        /**
         * MySQL/MariaDB - Motor de base de datos relacional open source.
         * Dialecto completamente implementado con sintaxis específica de MySQL.
         */
        MYSQL,
        
        /**
         * PostgreSQL - Sistema de gestión de base de datos objeto-relacional.
         * Actualmente utiliza MySQLDialect como implementación simplificada.
         * 
         * @deprecated Implementación temporal - se requiere PostgreSQLDialect específico
         */
        POSTGRESQL,
        
        /**
         * Microsoft SQL Server - Sistema de gestión de base de datos empresarial.
         * Actualmente utiliza MySQLDialect como implementación simplificada.
         * 
         * @deprecated Implementación temporal - se requiere SQLServerDialect específico
         */
        SQLSERVER
    }
    
    /**
     * Constructor privado para prevenir la instanciación de la clase factory.
     * Esta clase solo debe utilizarse a través de sus métodos estáticos.
     */
    private QueryBuilderFactory() {
        // Utility class - no instances should be created
    }
    
    /**
     * Crea un QueryBuilder configurado con el dialecto correspondiente al tipo de base de datos especificado.
     * 
     * Este es el método principal del factory que utiliza el patrón Strategy
     * para seleccionar la implementación adecuada del dialecto SQL.
     * 
     * @param type El tipo de base de datos para el cual crear el QueryBuilder
     * @return QueryBuilder configurado con el dialecto apropiado
     * 
     * @example
     * <pre>
     * QueryBuilder builder = QueryBuilderFactory.create(DatabaseType.MYSQL);
     * String createTable = builder.CREATE().TABLE().INPUT("usuarios")
     *                            .openParenthesis()
     *                            .INPUT("id").INT().AUTO_INCREMENT().PRIMARY().KEY()
     *                            .closeParenthesis().build();
     * </pre>
     * 
     * @note Actualmente PostgreSQL y SQL Server utilizan MySQLDialect como
     *       implementación temporal hasta que se desarrollen sus dialectos específicos.
     */
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
    
    /**
     * Crea un QueryBuilder específicamente configurado para MySQL.
     * 
     * Método de conveniencia que crea directamente una instancia de QueryBuilder
     * con MySQLDialect, evitando la necesidad de usar la enumeración.
     * 
     * @return QueryBuilder configurado con MySQLDialect
     * 
     * @example
     * <pre>
     * QueryBuilder mysql = QueryBuilderFactory.createMySQL();
     * String select = mysql.SELECT().INPUT("nombre").COMMA().INPUT("email")
     *                      .FROM().INPUT("usuarios")
     *                      .WHERE().INPUT("activo").EQUALS().TRUE()
     *                      .build();
     * </pre>
     */
    public static QueryBuilder createMySQL() {
        return new QueryBuilder(new MySQLDialect());
    }
    
    /**
     * Crea un QueryBuilder configurado para PostgreSQL.
     * 
     * @return QueryBuilder configurado para PostgreSQL
     * 
     * @deprecated Implementación temporal que utiliza MySQLDialect.
     *             Se planea implementar PostgreSQLDialect específico en el futuro.
     * 
     * @todo Implementar PostgreSQLDialect con sintaxis específica:
     *       - SERIAL en lugar de AUTO_INCREMENT
     *       - Comillas dobles para identificadores
     *       - Sintaxis específica para LIMIT/OFFSET
     *       - Tipos de datos específicos de PostgreSQL
     * 
     * @example
     * <pre>
     * QueryBuilder postgres = QueryBuilderFactory.createPostgreSQL();
     * // Actualmente genera sintaxis MySQL, necesita PostgreSQLDialect
     * </pre>
     */
    public static QueryBuilder createPostgreSQL() {
        return new QueryBuilder(new MySQLDialect()); // Simplified for now
    }
    
    /**
     * Crea un QueryBuilder configurado para Microsoft SQL Server.
     * 
     * @return QueryBuilder configurado para SQL Server
     * 
     * @deprecated Implementación temporal que utiliza MySQLDialect.
     *             Se planea implementar SQLServerDialect específico en el futuro.
     * 
     * @todo Implementar SQLServerDialect con sintaxis específica:
     *       - IDENTITY en lugar de AUTO_INCREMENT
     *       - TOP en lugar de LIMIT
     *       - Corchetes [] para identificadores
     *       - Sintaxis específica para tipos de datos
     * 
     * @example
     * <pre>
     * QueryBuilder sqlserver = QueryBuilderFactory.createSQLServer();
     * // Actualmente genera sintaxis MySQL, necesita SQLServerDialect
     * </pre>
     */
    public static QueryBuilder createSQLServer() {
        return new QueryBuilder(new MySQLDialect()); // Simplified for now
    }
    
    /**
     * Obtiene la lista de tipos de base de datos soportados actualmente.
     * 
     * @return Array con todos los tipos de base de datos disponibles
     * 
     * @example
     * <pre>
     * DatabaseType[] supported = QueryBuilderFactory.getSupportedTypes();
     * for (DatabaseType type : supported) {
     *     System.out.println("Supported: " + type);
     * }
     * </pre>
     */
    public static DatabaseType[] getSupportedTypes() {
        return DatabaseType.values();
    }
    
    /**
     * Verifica si un tipo de base de datos específico está completamente soportado.
     * 
     * @param type El tipo de base de datos a verificar
     * @return true si tiene implementación completa, false si usa implementación temporal
     * 
     * @example
     * <pre>
     * boolean fullySupported = QueryBuilderFactory.isFullySupported(DatabaseType.MYSQL);
     * // Retorna: true para MySQL, false para PostgreSQL y SQL Server
     * </pre>
     */
    public static boolean isFullySupported(DatabaseType type) {
        switch (type) {
            case MYSQL:
                return true; // Fully implemented
            case POSTGRESQL:
            case SQLSERVER:
                return false; // Using simplified implementation
            default:
                return false;
        }
    }
    
    /**
     * Obtiene información sobre el estado de implementación de un tipo de base de datos.
     * 
     * @param type El tipo de base de datos para obtener información
     * @return Descripción del estado de implementación
     * 
     * @example
     * <pre>
     * String info = QueryBuilderFactory.getImplementationInfo(DatabaseType.POSTGRESQL);
     * // Retorna: "Using MySQL dialect as temporary implementation"
     * </pre>
     */
    public static String getImplementationInfo(DatabaseType type) {
        switch (type) {
            case MYSQL:
                return "Fully implemented with native MySQL dialect";
            case POSTGRESQL:
                return "Using MySQL dialect as temporary implementation";
            case SQLSERVER:
                return "Using MySQL dialect as temporary implementation";
            default:
                return "Unknown database type";
        }
    }
}