package com.postgres.services.database.query_builder;

import com.postgres.services.database.query_builder.dialects.MySQLDialect;
import com.postgres.services.database.query_builder.dialects.SQLDialect;
import com.postgres.services.database.query_builder.enums.ColumnConstraint;
import com.postgres.services.database.query_builder.enums.CommonKeyword;
import com.postgres.services.database.query_builder.enums.DDLKeyword;
import com.postgres.services.database.query_builder.enums.DMLKeyword;
import com.postgres.services.database.query_builder.enums.DataType;

/**
 * Constructor de consultas SQL que utiliza el patrón Fluent Interface.
 * 
 * Esta clase permite construir consultas SQL de manera programática utilizando
 * una interfaz fluida (Fluent Interface) que mejora la legibilidad y reduce
 * errores de sintaxis. Soporta múltiples dialectos SQL a través del patrón
 * Strategy y proporciona métodos para DDL, DML y operaciones comunes.
 * 
 * Características principales:
 * - Interfaz fluida para construcción de consultas
 * - Soporte para múltiples dialectos SQL (MySQL por defecto)
 * - Métodos tipo-seguros basados en enumeraciones
 * - Construcción tanto de DDL como DML
 * - Manejo automático de espacios y sintaxis
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see SQLDialect
 * @see MySQLDialect
 * 
 * @example
 * <pre>
 * // Ejemplo de uso básico
 * QueryBuilder qb = new QueryBuilder();
 * String sql = qb.SELECT().ASTERISK().FROM().INPUT("usuarios").WHERE()
 *               .INPUT("activo").EQUALS().TRUE().build();
 * // Resultado: "SELECT * FROM usuarios WHERE activo = TRUE;"
 * 
 * // Ejemplo con DDL
 * String createTable = qb.CREATE().TABLE().INPUT("productos")
 *                       .openParenthesis()
 *                       .INPUT("id").INT().AUTO_INCREMENT().PRIMARY().KEY()
 *                       .closeParenthesis().build();
 * </pre>
 */
public class QueryBuilder {
    private StringBuilder query;
    private SQLDialect dialect;
    private boolean nextInputShouldBeWrapped = false;
    
    /**
     * Constructor por defecto que inicializa con dialecto MySQL.
     * Utiliza MySQLDialect como implementación predeterminada.
     */
    public QueryBuilder() {
        this.dialect = new MySQLDialect(); // Default
        this.query = new StringBuilder();
    }
    
    /**
     * Constructor que permite especificar un dialecto SQL específico.
     * 
     * @param dialect La implementación del dialecto SQL a utilizar
     */
    public QueryBuilder(SQLDialect dialect) {
        this.dialect = dialect;
        this.query = new StringBuilder();
    }
    
    /**
     * Establece el dialecto SQL a utilizar manteniendo la interfaz fluida.
     * 
     * @param dialect La implementación del dialecto SQL a utilizar
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder withDialect(SQLDialect dialect) {
        this.dialect = dialect;
        return this;
    }
    
    /**
     * Método privado para añadir un espacio a la consulta.
     * Utilizado internamente para mantener el formato correcto del SQL.
     */
    private void addSpace(){
        this.query.append(" ");
    }

    // Generic keyword methods using enums
    
    /**
     * Añade una palabra clave DDL a la consulta.
     * 
     * @param keyword La palabra clave DDL de la enumeración DDLKeyword
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder keyword(DDLKeyword keyword) {
        this.query.append(keyword.getKeyword());
        this.addSpace();
        return this;
    }

    /**
     * Añade una palabra clave DML a la consulta.
     * 
     * @param keyword La palabra clave DML de la enumeración DMLKeyword
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder keyword(DMLKeyword keyword) {
        this.query.append(keyword.getKeyword());
        this.addSpace();
        return this;
    }

    /**
     * Añade una palabra clave común a la consulta.
     * 
     * @param keyword La palabra clave común de la enumeración CommonKeyword
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder keyword(CommonKeyword keyword) {
        this.query.append(keyword.getKeyword());
        this.addSpace();
        return this;
    }

    // Dialect-aware constraint methods
    
    /**
     * Añade una restricción de columna usando el dialecto específico.
     * Para AUTO_INCREMENT utiliza la implementación específica del dialecto.
     * 
     * @param constraint La restricción de columna de la enumeración ColumnConstraint
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder constraint(ColumnConstraint constraint) {
        if (constraint == ColumnConstraint.AUTO_INCREMENT) {
            this.query.append(dialect.getAutoIncrement());
        } else {
            this.query.append(constraint.getConstraint());
        }
        this.addSpace();
        return this;
    }

    // Dialect-aware function methods
    
    /**
     * Añade la función COUNT específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder COUNT() {
        this.query.append(dialect.getCountFunction());
        this.addSpace();
        return this;
    }

    /**
     * Añade la función SUM específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder SUM() {
        this.query.append(dialect.getSumFunction());
        this.addSpace();
        return this;
    }

    /**
     * Añade la función AVG específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder AVG() {
        this.query.append(dialect.getAvgFunction());
        this.addSpace();
        return this;
    }

    /**
     * Añade la función MIN específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder MIN() {
        this.query.append(dialect.getMinFunction());
        this.addSpace();
        return this;
    }

    /**
     * Añade la función MAX específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder MAX() {
        this.query.append(dialect.getMaxFunction());
        this.addSpace();
        return this;
    }

    /**
     * Añade la función UPPER específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder UPPER() {
        this.query.append(dialect.getUpperFunction());
        this.addSpace();
        return this;
    }

    /**
     * Añade la función LOWER específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder LOWER() {
        this.query.append(dialect.getLowerFunction());
        this.addSpace();
        return this;
    }

    /**
     * Añade la función LENGTH específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder LENGTH() {
        this.query.append(dialect.getLengthFunction());
        this.addSpace();
        return this;
    }

    /**
     * Añade la función de timestamp actual específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder NOW() {
        this.query.append(dialect.getCurrentTimestamp());
        this.addSpace();
        return this;
    }

    // Dialect-aware convenience methods
    
    /**
     * Añade la palabra clave AUTO_INCREMENT específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder AUTO_INCREMENT() {
        this.query.append(dialect.getAutoIncrement());
        this.addSpace();
        return this;
    }
    
    /**
     * Añade IF NOT EXISTS si el dialecto lo soporta.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder IF_NOT_EXISTS() {
        String ifNotExists = dialect.getIfNotExists();
        if (!ifNotExists.isEmpty()) {
            this.query.append(ifNotExists);
            this.addSpace();
        }
        return this;
    }
    
    /**
     * Añade cláusula LIMIT con el número especificado usando el dialecto.
     * 
     * @param count El número máximo de registros a limitar
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder LIMIT(int count) {
        this.query.append(dialect.getLimit(count));
        this.addSpace();
        return this;
    }
    
    /**
     * Añade el valor booleano TRUE específico del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder TRUE() {
        this.query.append(dialect.getBooleanTrue());
        this.addSpace();
        return this;
    }
    
    /**
     * Añade el valor booleano FALSE específico del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder FALSE() {
        this.query.append(dialect.getBooleanFalse());
        this.addSpace();
        return this;
    }

    // DDL Methods
    
    /**
     * Añade la palabra clave CREATE.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder CREATE() { return keyword(DDLKeyword.CREATE); }
    
    /**
     * Añade la palabra clave DROP.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder DROP() { return keyword(DDLKeyword.DROP); }
    
    /**
     * Añade la palabra clave ALTER.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder ALTER() { return keyword(DDLKeyword.ALTER); }
    
    /**
     * Añade la palabra clave TABLE.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder TABLE() { return keyword(DDLKeyword.TABLE); }
    
    /**
     * Añade la palabra clave INDEX.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder INDEX() { return keyword(DDLKeyword.INDEX); }
    
    /**
     * Añade la palabra clave CONSTRAINT.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder CONSTRAINT() { return keyword(DDLKeyword.CONSTRAINT); }
    
    /**
     * Añade la palabra clave ADD.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder ADD() { return keyword(DDLKeyword.ADD); }
    
    /**
     * Añade la palabra clave PRIMARY.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder PRIMARY() { return keyword(DDLKeyword.PRIMARY); }
    
    /**
     * Añade la palabra clave FOREIGN.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder FOREIGN() { return keyword(DDLKeyword.FOREIGN); }
    
    /**
     * Añade la palabra clave KEY.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder KEY() { return keyword(DDLKeyword.KEY); }
    
    /**
     * Añade la palabra clave UNIQUE.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder UNIQUE() { return keyword(DDLKeyword.UNIQUE); }
    
    /**
     * Añade la palabra clave REFERENCES.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder REFERENCES() { return keyword(DDLKeyword.REFERENCES); }
    
    /**
     * Añade la palabra clave DATABASE.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder DATABASE() { return keyword(DDLKeyword.DATABASE); }
    
    /**
     * Añade la palabra clave USE.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder USE() { return keyword(DDLKeyword.USE); }
    
    /**
     * Añade la palabra clave DATABASES.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder DATABASES() { return keyword(DDLKeyword.DATABASES); }
    
    /**
     * Añade la palabra clave IDENTIFIED.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder IDENTIFIED() { return keyword(DDLKeyword.IDENTIFIED); }
    
    /**
     * Añade la palabra clave IF.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder IF() { return keyword(DDLKeyword.IF); }
    
    /**
     * Añade la palabra clave EXISTS.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder EXISTS() { return keyword(DDLKeyword.EXISTS); }
    
    /**
     * Añade la palabra clave FLUSH.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder FLUSH() { return keyword(DDLKeyword.FLUSH); }
    
    /**
     * Añade la palabra clave GRANT.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder GRANT() { return keyword(DDLKeyword.GRANT); }

    // DML Methods
    
    /**
     * Añade la palabra clave SELECT.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder SELECT() { return keyword(DMLKeyword.SELECT); }
    
    /**
     * Añade la palabra clave INSERT.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder INSERT() { return keyword(DMLKeyword.INSERT); }
    
    /**
     * Añade la palabra clave UPDATE.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder UPDATE() { return keyword(DMLKeyword.UPDATE); }
    
    /**
     * Añade la palabra clave DELETE.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder DELETE() { return keyword(DMLKeyword.DELETE); }
    
    /**
     * Añade la palabra clave FROM.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder FROM() { return keyword(DMLKeyword.FROM); }
    
    /**
     * Añade la palabra clave WHERE.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder WHERE() { return keyword(DMLKeyword.WHERE); }
    
    /**
     * Añade la palabra clave VALUES.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder VALUES() { return keyword(DMLKeyword.VALUES); }
    
    /**
     * Añade la palabra clave INTO.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder INTO() { return keyword(DMLKeyword.INTO); }
    
    /**
     * Añade la palabra clave SET.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder SET() { return keyword(DMLKeyword.SET); }

    // Common Keywords
    
    /**
     * Añade la palabra clave NOT.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder NOT() { return keyword(CommonKeyword.NOT); }
    
    /**
     * Añade la palabra clave ON.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder ON() { return keyword(CommonKeyword.ON); }
    
    /**
     * Añade la palabra clave ALL.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder ALL() { return keyword(CommonKeyword.ALL); }
    
    /**
     * Añade la palabra clave TO.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder TO() { return keyword(CommonKeyword.TO); }
    
    /**
     * Añade la palabra clave BY.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder BY() { return keyword(CommonKeyword.BY); }
    
    /**
     * Añade la palabra clave ASC.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder ASC() { return keyword(CommonKeyword.ASC); }
    
    /**
     * Añade la palabra clave DESC.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder DESC() { return keyword(CommonKeyword.DESC); }
    
    /**
     * Añade la palabra clave CASCADE.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder CASCADE() { return keyword(CommonKeyword.CASCADE); }
    
    /**
     * Añade el símbolo de igualdad (=).
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder EQUALS() { return keyword(CommonKeyword.EQUALS); }
    
    /**
     * Añade una coma (,).
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder COMMA() { return keyword(CommonKeyword.COMMA); }
    
    /**
     * Añade un punto y coma (;).
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder SEMICOLON() { return keyword(CommonKeyword.SEMICOLON); }
    
    /**
     * Añade un asterisco (*).
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder ASTERISK() { return keyword(CommonKeyword.ASTERISK); }
    
    /**
     * Añade el símbolo de porcentaje (%).
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder PERCENT() { return keyword(CommonKeyword.PERCENT); }
    
    /**
     * Añade el símbolo arroba (@).
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder AT_SIGN() { return keyword(CommonKeyword.AT_SIGN); }
    
    /**
     * Añade la palabra clave USER.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder USER() { return keyword(CommonKeyword.USER); }
    
    /**
     * Añade la palabra clave PRIVILEGES.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder PRIVILEGES() { return keyword(CommonKeyword.PRIVILEGES); }
    
    /**
     * Añade la palabra clave SHOW.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder SHOW() { return keyword(CommonKeyword.SHOW); }

    /**
     * Private helper method to handle data types using the dialect
     * @param dataType The data type enum
     * @return QueryBuilder instance for method chaining
     */
    private QueryBuilder dataType(DataType dataType) {
        String typeString = dialect.getDataType(dataType.toString(), null, null, null);
        query.append(typeString);
        addSpace();
        return this;
    }
    
    /**
     * Private helper method to handle data types with length using the dialect
     * @param dataType The data type enum
     * @param length The length parameter for the data type
     * @return QueryBuilder instance for method chaining
     */
    private QueryBuilder dataType(DataType dataType, Integer length) {
        String typeString = dialect.getDataType(dataType.toString(), length, null, null);
        query.append(typeString);
        addSpace();
        return this;
    }
    
    /**
     * Private helper method to handle data types with precision and scale using the dialect
     * @param dataType The data type enum
     * @param precision The precision parameter
     * @param scale The scale parameter
     * @return QueryBuilder instance for method chaining
     */
    private QueryBuilder dataType(DataType dataType, Integer precision, Integer scale) {
        String typeString = dialect.getDataType(dataType.toString(), null, precision, scale);
        query.append(typeString);
        addSpace();
        return this;
    }

    // Data types using dialect
    
    /**
     * Añade tipo de dato VARCHAR con longitud específica.
     * 
     * @param length La longitud máxima del VARCHAR
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder VARCHAR(int length) { return dataType(DataType.VARCHAR, length); }
    
    /**
     * Añade tipo de dato INT.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder INT() { return dataType(DataType.INT); }
    
    /**
     * Añade tipo de dato DATE.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder DATE() { return dataType(DataType.DATE); }
    
    /**
     * Añade tipo de dato TEXT.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder TEXT() { return dataType(DataType.TEXT); } // Fixed: changed data() to dataType()
    
    /**
     * Añade tipo de dato BOOLEAN.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder BOOLEAN() { return dataType(DataType.BOOLEAN); }
    
    /**
     * Añade tipo de dato DECIMAL con precisión y escala.
     * 
     * @param precision La precisión total del número
     * @param scale El número de dígitos después del punto decimal
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder DECIMAL(int precision, int scale) { 
        return dataType(DataType.DECIMAL, precision, scale); 
    }
    
    /**
     * Añade tipo de dato DECIMAL con precisión por defecto.
     * 
     * @param precision La precisión total del número
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder DECIMAL(int precision) { 
        return dataType(DataType.DECIMAL, precision, 2); // Default scale of 2
    }
    
    /**
     * Añade tipo de dato DECIMAL con valores por defecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder DECIMAL() { 
        return dataType(DataType.DECIMAL, 10, 2); // Default 10,2
    }
    
    // Constraints using dialect
    
    /**
     * Añade restricción NOT NULL.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder NOTNULL() { return constraint(ColumnConstraint.NOT_NULL); }

    /**
     * Adds column specification for INSERT statements
     * The next INPUT() call will be wrapped in parentheses
     * @return QueryBuilder instance for method chaining
     */
    public QueryBuilder COLUMNS() {
        this.nextInputShouldBeWrapped = true;
        return this;
    }
    
    /**
     * Adds input to the query
     * @param input The input string to add
     * @return QueryBuilder instance for method chaining
     */
    public QueryBuilder INPUT(String input) {
        if (nextInputShouldBeWrapped) {
            query.append(" (").append(input).append(")");
            nextInputShouldBeWrapped = false;
        } else {
            query.append(" ").append(input);
        }
        return this;
    }
    
    /**
     * Resets the query builder state
     * @return QueryBuilder instance for method chaining
     */
    public QueryBuilder reset() {
        query = new StringBuilder();
        nextInputShouldBeWrapped = false;
        return this;
    }
    
    /**
     * Construye la consulta SQL final añadiendo punto y coma.
     * Resetea el builder para permitir reutilización.
     * 
     * @return La consulta SQL completa terminada en punto y coma
     */
    public String build() {
        String query = this.query.toString().trim() + ";";
        this.setQuery("");
        return query;
    }

    /**
     * Consume la consulta actual sin añadir punto y coma.
     * Resetea el builder para permitir reutilización.
     * 
     * @return La consulta SQL actual sin terminar
     */
    public String CONSUME(){
        String query = this.getQuery();
        this.setQuery("");
        return query;
    }

    // Utility methods
    
    /**
     * Añade un paréntesis de apertura "(".
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder openParenthesis() {
        this.query.append("(");
        return this;
    }

    /**
     * Añade un paréntesis de cierre ")" seguido de espacio.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder closeParenthesis() {
        this.query.append(")");
        this.addSpace();
        return this;
    }

    /**
     * Añade una coma "," seguida de espacio.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder comma() {
        this.query.append(",");
        this.addSpace();
        return this;
    }

    /**
     * Añade un punto y coma ";" sin espacio adicional.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder semicolon() {
        this.query.append(";");
        return this;
    }

    /**
     * Añade símbolo de igualdad "=" con espacios.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder equals() {
        this.query.append("=");
        this.addSpace();
        return this;
    }

    // Getters and setters
    
    /**
     * Obtiene la consulta SQL actual en construcción.
     * 
     * @return La cadena de consulta SQL actual
     */
    public String getQuery() {
        return query.toString();
    }

    /**
     * Establece la consulta SQL directamente.
     * 
     * @param query La nueva consulta SQL
     */
    public void setQuery(String query) {
        this.query = new StringBuilder(query);
    }

    /**
     * Obtiene el dialecto SQL actualmente configurado.
     * 
     * @return La implementación del dialecto SQL actual
     */
    public SQLDialect getDialect() {
        return dialect;
    }

    /**
     * Establece el dialecto SQL a utilizar.
     * 
     * @param dialect La nueva implementación del dialecto SQL
     */
    public void setDialect(SQLDialect dialect) {
        this.dialect = dialect;
    }
}