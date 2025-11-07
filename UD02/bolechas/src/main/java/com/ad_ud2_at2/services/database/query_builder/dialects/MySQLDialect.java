package com.ad_ud2_at2.services.database.query_builder.dialects;

/**
 * Implementación del dialecto SQL específico para MySQL.
 * 
 * Esta clase proporciona las funciones y sintaxis específicas de MySQL,
 * implementando la interfaz SQLDialect. Permite generar consultas SQL
 * compatibles con MySQL/MariaDB utilizando su sintaxis particular.
 * 
 * Incluye soporte para:
 * - Tipos de datos específicos de MySQL
 * - Funciones agregadas y de cadena
 * - Sintaxis para auto-incremento y limitaciones
 * - Identificadores con comillas específicas de MySQL (backticks)
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see SQLDialect
 */
public class MySQLDialect implements SQLDialect {
    
    /**
     * Obtiene la palabra clave para auto-incremento en MySQL.
     * 
     * @return String "AUTO_INCREMENT" - sintaxis específica de MySQL para campos auto-incrementales
     */
    @Override
    public String getAutoIncrement() {
        return "AUTO_INCREMENT";
    }
    
    /**
     * Genera la cláusula LIMIT para MySQL.
     * 
     * @param count El número máximo de registros a retornar
     * @return String con la sintaxis "LIMIT count" específica de MySQL
     */
    @Override
    public String getLimit(int count) {
        return "LIMIT " + count;
    }
    
    /**
     * Obtiene la cláusula condicional para creación de objetos si no existen.
     * 
     * @return String "IF NOT EXISTS" - sintaxis de MySQL para evitar errores en creación
     */
    @Override
    public String getIfNotExists() {
        return "IF NOT EXISTS";
    }
    
    /**
     * Genera la definición de tipo de dato con parámetros específicos para MySQL.
     * Maneja tipos comunes como VARCHAR, DECIMAL, TEXT, etc.
     * 
     * @param baseType El tipo de dato base (VARCHAR, INT, DECIMAL, etc.)
     * @param length La longitud para tipos que la requieren (VARCHAR, CHAR)
     * @param precision La precisión para tipos decimales
     * @param scale La escala para tipos decimales
     * @return String con la definición completa del tipo de dato en sintaxis MySQL
     * 
     * @example
     * <pre>
     * getDataType("VARCHAR", 100, null, null) → "VARCHAR(100)"
     * getDataType("DECIMAL", null, 10, 2) → "DECIMAL(10,2)"
     * getDataType("TEXT", null, null, null) → "TEXT"
     * </pre>
     */
    @Override
    public String getDataType(String baseType, Integer length, Integer precision, Integer scale) {
        switch (baseType.toUpperCase()) {
            case "VARCHAR":
                return length != null ? "VARCHAR(" + length + ")" : "VARCHAR(255)";
            case "DECIMAL":
                return precision != null && scale != null ? 
                    "DECIMAL(" + precision + "," + scale + ")" : "DECIMAL(10,2)";
            case "TEXT":
                return "TEXT";
            case "BOOLEAN":
                return "BOOLEAN";
            default:
                return baseType;
        }
    }
    
    /**
     * Genera una expresión de concatenación de cadenas usando la función CONCAT de MySQL.
     * 
     * @param parts Las partes de cadena a concatenar
     * @return String con la sintaxis "CONCAT(part1, part2, ...)" de MySQL
     * 
     * @example
     * <pre>
     * getStringConcatenation("'Hello'", "' '", "'World'") → "CONCAT('Hello', ' ', 'World')"
     * </pre>
     */
    @Override
    public String getStringConcatenation(String... parts) {
        return "CONCAT(" + String.join(", ", parts) + ")";
    }
    
    /**
     * Obtiene el tipo de dato para fechas en MySQL.
     * 
     * @return String "DATE" - tipo de dato específico para fechas en MySQL
     */
    @Override
    public String getDateFormat() {
        return "DATE";
    }
    
    /**
     * Obtiene la representación del valor booleano verdadero en MySQL.
     * 
     * @return String "TRUE" - valor booleano verdadero en MySQL
     */
    @Override
    public String getBooleanTrue() {
        return "TRUE";
    }
    
    /**
     * Obtiene la representación del valor booleano falso en MySQL.
     * 
     * @return String "FALSE" - valor booleano falso en MySQL
     */
    @Override
    public String getBooleanFalse() {
        return "FALSE";
    }
    
    /**
     * Aplica las comillas específicas de MySQL a un identificador.
     * MySQL utiliza backticks (`) para delimitar identificadores.
     * 
     * @param identifier El nombre del identificador (tabla, columna, etc.)
     * @return String con el identificador delimitado por backticks
     * 
     * @example
     * <pre>
     * getQuoteIdentifier("table_name") → "`table_name`"
     * getQuoteIdentifier("user") → "`user`" (útil para palabras reservadas)
     * </pre>
     */
    @Override
    public String getQuoteIdentifier(String identifier) {
        return "`" + identifier + "`";
    }

    /**
     * Obtiene el nombre de la función COUNT para agregaciones.
     * 
     * @return String "COUNT" - función de conteo estándar de MySQL
     */
    @Override
    public String getCountFunction() {
        return "COUNT";
    }

    /**
     * Obtiene el nombre de la función SUM para agregaciones.
     * 
     * @return String "SUM" - función de suma estándar de MySQL
     */
    @Override
    public String getSumFunction() {
        return "SUM";
    }

    /**
     * Obtiene el nombre de la función AVG para promedios.
     * 
     * @return String "AVG" - función de promedio estándar de MySQL
     */
    @Override
    public String getAvgFunction() {
        return "AVG";
    }

    /**
     * Obtiene el nombre de la función MIN para valores mínimos.
     * 
     * @return String "MIN" - función de mínimo estándar de MySQL
     */
    @Override
    public String getMinFunction() {
        return "MIN";
    }

    /**
     * Obtiene el nombre de la función MAX para valores máximos.
     * 
     * @return String "MAX" - función de máximo estándar de MySQL
     */
    @Override
    public String getMaxFunction() {
        return "MAX";
    }

    /**
     * Obtiene el nombre de la función UPPER para convertir a mayúsculas.
     * 
     * @return String "UPPER" - función de conversión a mayúsculas de MySQL
     */
    @Override
    public String getUpperFunction() {
        return "UPPER";
    }

    /**
     * Obtiene el nombre de la función LOWER para convertir a minúsculas.
     * 
     * @return String "LOWER" - función de conversión a minúsculas de MySQL
     */
    @Override
    public String getLowerFunction() {
        return "LOWER";
    }

    /**
     * Obtiene el nombre de la función LENGTH para calcular longitud de cadenas.
     * 
     * @return String "LENGTH" - función de longitud específica de MySQL
     */
    @Override
    public String getLengthFunction() {
        return "LENGTH";
    }

    /**
     * Obtiene el nombre de la función SUBSTRING para extraer subcadenas.
     * 
     * @return String "SUBSTRING" - función de subcadena estándar de MySQL
     */
    @Override
    public String getSubstringFunction() {
        return "SUBSTRING";
    }

    /**
     * Obtiene la función para obtener la fecha y hora actual en MySQL.
     * 
     * @return String "NOW()" - función específica de MySQL para timestamp actual
     */
    @Override
    public String getCurrentTimestamp() {
        return "NOW()";
    }

    /**
     * Obtiene la representación de valor nulo en MySQL.
     * 
     * @return String "NULL" - valor nulo estándar de MySQL
     */
    @Override
    public String getNullFunction() {
        return "NULL";
    }

    /**
     * Obtiene el nombre de la función COALESCE para manejar valores nulos.
     * Retorna el primer valor no nulo de una lista de expresiones.
     * 
     * @return String "COALESCE" - función de MySQL para manejo de nulos
     * 
     * @example
     * <pre>
     * COALESCE(campo1, campo2, 'default') retorna el primer valor no nulo
     * </pre>
     */
    @Override
    public String getCoalesceFunction() {
        return "COALESCE";
    }
}