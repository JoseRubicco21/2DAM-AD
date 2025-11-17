package com.postgres.services.database.query_builder.enums;

/**
 * Enumeración que define las funciones agregadas SQL estándar.
 * 
 * Esta enum proporciona una abstracción tipo-segura para las funciones
 * de agregación más comunes utilizadas en consultas SQL. Cada función
 * tiene su representación en cadena correspondiente para generar
 * consultas SQL válidas.
 * 
 * Las funciones agregadas procesan un conjunto de valores y retornan
 * un único valor resultado, siendo fundamentales para estadísticas
 * y resúmenes de datos.
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see com.ad_ud2_at2.services.database.query_builder.dialects.SQLDialect
 */
public enum AggregateFunction {
    
    /**
     * Función COUNT para contar el número de registros.
     * Cuenta el número de filas que cumplen una condición específica.
     * 
     * @example
     * <pre>
     * COUNT(*) - cuenta todas las filas
     * COUNT(columna) - cuenta filas con valores no nulos en la columna
     * </pre>
     */
    COUNT("COUNT"),
    
    /**
     * Función SUM para sumar valores numéricos.
     * Calcula la suma total de todos los valores de una columna numérica.
     * 
     * @example
     * <pre>
     * SUM(precio) - suma todos los precios
     * SUM(cantidad * precio) - suma el resultado de una expresión
     * </pre>
     */
    SUM("SUM"),
    
    /**
     * Función AVG para calcular el promedio de valores numéricos.
     * Calcula la media aritmética de todos los valores de una columna numérica.
     * 
     * @example
     * <pre>
     * AVG(edad) - promedio de edades
     * AVG(salario) - salario promedio
     * </pre>
     */
    AVG("AVG"),
    
    /**
     * Función MIN para encontrar el valor mínimo.
     * Retorna el valor más pequeño de una columna, aplicable a
     * números, fechas y cadenas (orden alfabético).
     * 
     * @example
     * <pre>
     * MIN(precio) - precio más bajo
     * MIN(fecha_nacimiento) - fecha más antigua
     * </pre>
     */
    MIN("MIN"),
    
    /**
     * Función MAX para encontrar el valor máximo.
     * Retorna el valor más grande de una columna, aplicable a
     * números, fechas y cadenas (orden alfabético).
     * 
     * @example
     * <pre>
     * MAX(precio) - precio más alto
     * MAX(fecha_registro) - fecha más reciente
     * </pre>
     */
    MAX("MAX");

    /**
     * Representación en cadena de la función SQL.
     * Contiene el nombre exacto de la función como aparece en SQL estándar.
     */
    private final String function;

    /**
     * Constructor privado para inicializar cada constante de la enumeración.
     * 
     * @param function La representación en cadena de la función SQL
     */
    AggregateFunction(String function) {
        this.function = function;
    }

    /**
     * Obtiene la representación en cadena de la función agregada.
     * 
     * @return String con el nombre de la función SQL (COUNT, SUM, AVG, MIN, MAX)
     * 
     * @example
     * <pre>
     * AggregateFunction.COUNT.getFunction() → "COUNT"
     * AggregateFunction.AVG.getFunction() → "AVG"
     * </pre>
     */
    public String getFunction() {
        return function;
    }

    /**
     * Representación en cadena de la función agregada.
     * Equivalente a getFunction() para facilitar el uso en concatenaciones.
     * 
     * @return String con el nombre de la función SQL
     */
    @Override
    public String toString() {
        return function;
    }
}