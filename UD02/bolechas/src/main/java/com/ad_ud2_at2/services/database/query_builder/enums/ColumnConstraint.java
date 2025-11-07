package com.ad_ud2_at2.services.database.query_builder.enums;

/**
 * Enumeración que define las restricciones y valores de columna SQL estándar.
 * 
 * Esta enum proporciona una abstracción tipo-segura para las restricciones
 * y valores más comunes utilizados en la definición de columnas de tablas SQL.
 * Facilita la generación de DDL (Data Definition Language) consistente y
 * reduce errores de sintaxis en la creación de esquemas de base de datos.
 * 
 * Las restricciones de columna definen reglas y comportamientos específicos
 * para los campos de una tabla, incluyendo validaciones, valores por defecto
 * y características especiales como auto-incremento.
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see com.ad_ud2_at2.services.database.query_builder.dialects.SQLDialect
 */
public enum ColumnConstraint {
    
    /**
     * Restricción NOT NULL para campos obligatorios.
     * Especifica que la columna no puede contener valores nulos,
     * forzando que siempre tenga un valor válido.
     * 
     * @example
     * <pre>
     * CREATE TABLE usuarios (
     *     id INT NOT NULL,
     *     email VARCHAR(100) NOT NULL
     * );
     * </pre>
     */
    NOT_NULL("NOT NULL"),
    
    /**
     * Restricción AUTO_INCREMENT para claves primarias auto-generadas.
     * Especifica que la columna incrementará automáticamente su valor
     * con cada nueva inserción. Típicamente usado para IDs únicos.
     * 
     * @example
     * <pre>
     * CREATE TABLE productos (
     *     id INT AUTO_INCREMENT PRIMARY KEY,
     *     nombre VARCHAR(100)
     * );
     * </pre>
     */
    AUTO_INCREMENT("AUTO_INCREMENT"),
    
    /**
     * Palabra clave DEFAULT para especificar valores por defecto.
     * Se utiliza junto con un valor específico para establecer
     * el valor que tomará la columna si no se proporciona uno explícitamente.
     * 
     * @example
     * <pre>
     * CREATE TABLE usuarios (
     *     activo BOOLEAN DEFAULT TRUE,
     *     fecha_registro DATE DEFAULT CURRENT_DATE
     * );
     * </pre>
     */
    DEFAULT("DEFAULT"),
    
    /**
     * Valor NULL para representar ausencia de datos.
     * Indica que la columna puede contener valores nulos o
     * se utiliza como valor por defecto explícito.
     * 
     * @example
     * <pre>
     * CREATE TABLE perfiles (
     *     biografia TEXT DEFAULT NULL,
     *     telefono VARCHAR(15) NULL
     * );
     * </pre>
     */
    NULL("NULL"),
    
    /**
     * Valor booleano TRUE.
     * Representa el valor verdadero para columnas de tipo booleano
     * o se utiliza como valor por defecto para campos lógicos.
     * 
     * @example
     * <pre>
     * CREATE TABLE configuracion (
     *     notificaciones_email BOOLEAN DEFAULT TRUE,
     *     cuenta_activa BOOLEAN DEFAULT TRUE
     * );
     * </pre>
     */
    TRUE("TRUE"),
    
    /**
     * Valor booleano FALSE.
     * Representa el valor falso para columnas de tipo booleano
     * o se utiliza como valor por defecto para campos lógicos.
     * 
     * @example
     * <pre>
     * CREATE TABLE usuarios (
     *     es_admin BOOLEAN DEFAULT FALSE,
     *     cuenta_verificada BOOLEAN DEFAULT FALSE
     * );
     * </pre>
     */
    FALSE("FALSE");

    /**
     * Representación en cadena de la restricción o valor SQL.
     * Contiene la sintaxis exacta como aparece en las declaraciones SQL estándar.
     */
    private final String constraint;

    /**
     * Constructor privado para inicializar cada constante de la enumeración.
     * 
     * @param constraint La representación en cadena de la restricción SQL
     */
    ColumnConstraint(String constraint) {
        this.constraint = constraint;
    }

    /**
     * Obtiene la representación en cadena de la restricción de columna.
     * 
     * @return String con la sintaxis SQL de la restricción
     * 
     * @example
     * <pre>
     * ColumnConstraint.NOT_NULL.getConstraint() → "NOT NULL"
     * ColumnConstraint.AUTO_INCREMENT.getConstraint() → "AUTO_INCREMENT"
     * ColumnConstraint.DEFAULT.getConstraint() → "DEFAULT"
     * </pre>
     */
    public String getConstraint() {
        return constraint;
    }

    /**
     * Representación en cadena de la restricción de columna.
     * Equivalente a getConstraint() para facilitar el uso en concatenaciones.
     * 
     * @return String con la sintaxis SQL de la restricción
     */
    @Override
    public String toString() {
        return constraint;
    }
}