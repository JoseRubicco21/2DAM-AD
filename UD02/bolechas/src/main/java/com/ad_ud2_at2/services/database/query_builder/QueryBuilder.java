package com.ad_ud2_at2.services.database.query_builder;

import com.ad_ud2_at2.services.database.query_builder.dialects.SQLDialect;
import com.ad_ud2_at2.services.database.query_builder.dialects.MySQLDialect;
import com.ad_ud2_at2.services.database.query_builder.enums.*;

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
    
    /**
     * Cadena que almacena la consulta SQL en construcción.
     * Se va concatenando con cada método llamado hasta el build final.
     */
    private String query = "";
    
    /**
     * Dialecto SQL utilizado para generar sintaxis específica del motor de BD.
     * Permite cambiar entre diferentes implementaciones (MySQL, PostgreSQL, etc.).
     */
    private SQLDialect dialect;
    
    /**
     * Constructor por defecto que inicializa con dialecto MySQL.
     * Utiliza MySQLDialect como implementación predeterminada.
     */
    public QueryBuilder() {
        this.dialect = new MySQLDialect(); // Default
    }
    
    /**
     * Constructor que permite especificar un dialecto SQL específico.
     * 
     * @param dialect La implementación del dialecto SQL a utilizar
     */
    public QueryBuilder(SQLDialect dialect) {
        this.dialect = dialect;
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
        this.query += " ";
    }

    // Generic keyword methods using enums
    
    /**
     * Añade una palabra clave DDL a la consulta.
     * 
     * @param keyword La palabra clave DDL de la enumeración DDLKeyword
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder keyword(DDLKeyword keyword) {
        this.query += keyword.getKeyword();
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
        this.query += keyword.getKeyword();
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
        this.query += keyword.getKeyword();
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
            this.query += dialect.getAutoIncrement();
        } else {
            this.query += constraint.getConstraint();
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
        this.query += dialect.getCountFunction();
        this.addSpace();
        return this;
    }

    /**
     * Añade la función SUM específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder SUM() {
        this.query += dialect.getSumFunction();
        this.addSpace();
        return this;
    }

    /**
     * Añade la función AVG específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder AVG() {
        this.query += dialect.getAvgFunction();
        this.addSpace();
        return this;
    }

    /**
     * Añade la función MIN específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder MIN() {
        this.query += dialect.getMinFunction();
        this.addSpace();
        return this;
    }

    /**
     * Añade la función MAX específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder MAX() {
        this.query += dialect.getMaxFunction();
        this.addSpace();
        return this;
    }

    /**
     * Añade la función UPPER específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder UPPER() {
        this.query += dialect.getUpperFunction();
        this.addSpace();
        return this;
    }

    /**
     * Añade la función LOWER específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder LOWER() {
        this.query += dialect.getLowerFunction();
        this.addSpace();
        return this;
    }

    /**
     * Añade la función LENGTH específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder LENGTH() {
        this.query += dialect.getLengthFunction();
        this.addSpace();
        return this;
    }

    /**
     * Añade la función de timestamp actual específica del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder NOW() {
        this.query += dialect.getCurrentTimestamp();
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
        this.query += dialect.getAutoIncrement();
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
            this.query += ifNotExists;
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
        this.query += dialect.getLimit(count);
        this.addSpace();
        return this;
    }
    
    /**
     * Añade el valor booleano TRUE específico del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder TRUE() {
        this.query += dialect.getBooleanTrue();
        this.addSpace();
        return this;
    }
    
    /**
     * Añade el valor booleano FALSE específico del dialecto.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder FALSE() {
        this.query += dialect.getBooleanFalse();
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
    public QueryBuilder TEXT() { return dataType(DataType.TEXT); }
    
    /**
     * Añade tipo de dato BOOLEAN.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder BOOLEAN() { return dataType(DataType.BOOLEAN); }
    
    // Constraints using dialect
    
    /**
     * Añade restricción NOT NULL.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder NOTNULL() { return constraint(ColumnConstraint.NOT_NULL); }

    // Input method for custom text
    
    /**
     * Añade texto personalizado a la consulta.
     * Utilizado para nombres de tabla, columnas y otros identificadores.
     * 
     * @param input El texto a añadir a la consulta
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder INPUT(String input) {
        this.query += input;
        this.addSpace();
        return this;
    }

    /**
     * Añade un parámetro de cadena entre comillas simples.
     * Útil para valores de texto en WHERE y VALUES.
     * 
     * @param input El valor de cadena a añadir entre comillas
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder PARAM(String input){
        this.query += "'" + input + "'";
        this.addSpace();
        return this;
    }

    /**
     * Añade una subconsulta o expresión entre paréntesis.
     * 
     * @param modelQuery La consulta o expresión a encerrar entre paréntesis
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder MODEL(String modelQuery) {
        this.query += "(" + modelQuery + ")";
        return this;
    }

    /**
     * Añade texto eliminando espacios múltiples.
     * Útil para limpiar formato de entrada.
     * 
     * @param input El texto a limpiar y añadir
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder TRIM(String input) {
        this.query += input.replaceAll("\\s+", "");
        this.addSpace();
        return this;
    }

    // Data type methods
    
    /**
     * Añade un tipo de dato básico usando el dialecto específico.
     * 
     * @param type El tipo de dato de la enumeración DataType
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder dataType(DataType type) {
        this.query += dialect.getDataType(type.getType(), null, null, null);
        this.addSpace();
        return this;
    }

    /**
     * Añade un tipo de dato con longitud usando el dialecto específico.
     * 
     * @param type El tipo de dato de la enumeración DataType
     * @param length La longitud para el tipo de dato
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder dataType(DataType type, int length) {
        this.query += dialect.getDataType(type.getType(), length, null, null);
        this.addSpace();
        return this;
    }

    // Build method
    
    /**
     * Construye la consulta SQL final añadiendo punto y coma.
     * Resetea el builder para permitir reutilización.
     * 
     * @return La consulta SQL completa terminada en punto y coma
     */
    public String build() {
        String query = this.query.trim() + ";";
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

    // Reset method
    
    /**
     * Resetea el builder limpiando la consulta actual.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder reset() {
        this.query = "";
        return this;
    }

    // Utility methods
    
    /**
     * Añade un paréntesis de apertura "(".
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder openParenthesis() {
        this.query += "(";
        return this;
    }

    /**
     * Añade un paréntesis de cierre ")" seguido de espacio.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder closeParenthesis() {
        this.query += ")";
        this.addSpace();
        return this;
    }

    /**
     * Añade una coma "," seguida de espacio.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder comma() {
        this.query += ",";
        this.addSpace();
        return this;
    }

    /**
     * Añade un punto y coma ";" sin espacio adicional.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder semicolon() {
        this.query += ";";
        return this;
    }

    /**
     * Añade símbolo de igualdad "=" con espacios.
     * 
     * @return Esta instancia de QueryBuilder para encadenamiento de métodos
     */
    public QueryBuilder equals() {
        this.query += "=";
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
        return query;
    }

    /**
     * Establece la consulta SQL directamente.
     * 
     * @param query La nueva consulta SQL
     */
    public void setQuery(String query) {
        this.query = query;
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