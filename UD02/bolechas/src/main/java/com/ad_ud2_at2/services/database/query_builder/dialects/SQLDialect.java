package com.ad_ud2_at2.services.database.query_builder.dialects;

/**
 * Interfaz que define el contrato para dialectos SQL específicos de diferentes motores de base de datos.
 * 
 * Esta interfaz implementa el patrón Strategy, permitiendo que el sistema soporte
 * múltiples motores de base de datos (MySQL, PostgreSQL, Oracle, SQL Server, etc.)
 * sin modificar el código principal del generador de consultas.
 * 
 * Cada implementación debe proporcionar la sintaxis específica de su motor de base de datos
 * correspondiente, incluyendo tipos de datos, funciones, y cláusulas particulares.
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see MySQLDialect
 */
public interface SQLDialect {
    
    /**
     * Obtiene la palabra clave para auto-incremento específica del motor de base de datos.
     * 
     * @return String con la sintaxis de auto-incremento del dialecto específico
     *         (ej: "AUTO_INCREMENT" para MySQL, "SERIAL" para PostgreSQL)
     */
    String getAutoIncrement();
    
    /**
     * Genera la cláusula LIMIT para limitar el número de resultados.
     * Diferentes motores pueden tener sintaxis distintas para esta funcionalidad.
     * 
     * @param count El número máximo de registros a retornar
     * @return String con la sintaxis de limitación específica del dialecto
     *         (ej: "LIMIT count" para MySQL, "TOP count" para SQL Server)
     */
    String getLimit(int count);
    
    /**
     * Obtiene la cláusula condicional para creación de objetos si no existen.
     * Evita errores al intentar crear tablas, índices u otros objetos ya existentes.
     * 
     * @return String con la sintaxis condicional del dialecto específico
     *         (ej: "IF NOT EXISTS" para MySQL, "IF NOT EXISTS" para PostgreSQL)
     */
    String getIfNotExists();
    
    /**
     * Genera la definición de tipo de dato con parámetros específicos del dialecto.
     * Maneja las diferencias en tipos de datos entre diferentes motores de base de datos.
     * 
     * @param baseType El tipo de dato base (VARCHAR, INT, DECIMAL, etc.)
     * @param length La longitud para tipos que la requieren (VARCHAR, CHAR, etc.)
     * @param precision La precisión para tipos decimales/numéricos
     * @param scale La escala (decimales) para tipos numéricos
     * @return String con la definición completa del tipo de dato en sintaxis específica
     * 
     * @example
     * <pre>
     * MySQL: getDataType("VARCHAR", 100, null, null) → "VARCHAR(100)"
     * PostgreSQL: getDataType("VARCHAR", 100, null, null) → "CHARACTER VARYING(100)"
     * </pre>
     */
    String getDataType(String baseType, Integer length, Integer precision, Integer scale);
    
    /**
     * Genera una expresión de concatenación de cadenas usando la sintaxis específica del dialecto.
     * Diferentes motores tienen distintas formas de concatenar cadenas.
     * 
     * @param parts Las partes de cadena a concatenar
     * @return String con la sintaxis de concatenación específica del dialecto
     * 
     * @example
     * <pre>
     * MySQL: getStringConcatenation("a", "b") → "CONCAT(a, b)"
     * PostgreSQL: getStringConcatenation("a", "b") → "a || b"
     * SQL Server: getStringConcatenation("a", "b") → "a + b"
     * </pre>
     */
    String getStringConcatenation(String... parts);
    
    /**
     * Obtiene el tipo de dato para fechas específico del dialecto.
     * 
     * @return String con el tipo de dato para fechas del dialecto específico
     *         (ej: "DATE" para MySQL, "DATE" para PostgreSQL)
     */
    String getDateFormat();
    
    /**
     * Obtiene la representación del valor booleano verdadero específica del dialecto.
     * 
     * @return String con el valor booleano verdadero del dialecto específico
     *         (ej: "TRUE" para MySQL, "1" para SQL Server)
     */
    String getBooleanTrue();
    
    /**
     * Obtiene la representación del valor booleano falso específica del dialecto.
     * 
     * @return String con el valor booleano falso del dialecto específico
     *         (ej: "FALSE" para MySQL, "0" para SQL Server)
     */
    String getBooleanFalse();
    
    /**
     * Aplica las comillas específicas del dialecto a un identificador.
     * Diferentes motores usan distintos caracteres para delimitar identificadores.
     * 
     * @param identifier El nombre del identificador (tabla, columna, índice, etc.)
     * @return String con el identificador delimitado según el dialecto específico
     * 
     * @example
     * <pre>
     * MySQL: getQuoteIdentifier("table") → "`table`"
     * PostgreSQL: getQuoteIdentifier("table") → "\"table\""
     * SQL Server: getQuoteIdentifier("table") → "[table]"
     * </pre>
     */
    String getQuoteIdentifier(String identifier);
    
    // Funciones agregadas
    
    /**
     * Obtiene el nombre de la función COUNT para conteo de registros.
     * 
     * @return String con el nombre de la función de conteo del dialecto específico
     */
    String getCountFunction();
    
    /**
     * Obtiene el nombre de la función SUM para suma de valores.
     * 
     * @return String con el nombre de la función de suma del dialecto específico
     */
    String getSumFunction();
    
    /**
     * Obtiene el nombre de la función AVG para promedio de valores.
     * 
     * @return String con el nombre de la función de promedio del dialecto específico
     */
    String getAvgFunction();
    
    /**
     * Obtiene el nombre de la función MIN para valor mínimo.
     * 
     * @return String con el nombre de la función de mínimo del dialecto específico
     */
    String getMinFunction();
    
    /**
     * Obtiene el nombre de la función MAX para valor máximo.
     * 
     * @return String con el nombre de la función de máximo del dialecto específico
     */
    String getMaxFunction();
    
    // Funciones de cadena
    
    /**
     * Obtiene el nombre de la función UPPER para conversión a mayúsculas.
     * 
     * @return String con el nombre de la función de conversión a mayúsculas del dialecto específico
     */
    String getUpperFunction();
    
    /**
     * Obtiene el nombre de la función LOWER para conversión a minúsculas.
     * 
     * @return String con el nombre de la función de conversión a minúsculas del dialecto específico
     */
    String getLowerFunction();
    
    /**
     * Obtiene el nombre de la función LENGTH para calcular longitud de cadenas.
     * Algunos motores usan LEN, otros LENGTH.
     * 
     * @return String con el nombre de la función de longitud del dialecto específico
     *         (ej: "LENGTH" para MySQL, "LEN" para SQL Server)
     */
    String getLengthFunction();
    
    /**
     * Obtiene el nombre de la función SUBSTRING para extraer subcadenas.
     * 
     * @return String con el nombre de la función de subcadena del dialecto específico
     */
    String getSubstringFunction();
    
    // Funciones de fecha y utilidad
    
    /**
     * Obtiene la función para obtener la fecha y hora actual específica del dialecto.
     * 
     * @return String con la función de timestamp actual del dialecto específico
     *         (ej: "NOW()" para MySQL, "CURRENT_TIMESTAMP" para PostgreSQL)
     */
    String getCurrentTimestamp();
    
    /**
     * Obtiene la representación de valor nulo específica del dialecto.
     * 
     * @return String con la representación de NULL del dialecto específico
     */
    String getNullFunction();
    
    /**
     * Obtiene el nombre de la función COALESCE para manejar valores nulos.
     * Retorna el primer valor no nulo de una lista de expresiones.
     * 
     * @return String con el nombre de la función de manejo de nulos del dialecto específico
     */
    String getCoalesceFunction();
}