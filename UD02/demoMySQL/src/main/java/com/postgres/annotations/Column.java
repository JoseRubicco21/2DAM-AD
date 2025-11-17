package com.postgres.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación personalizada para mapear campos de clase con columnas de base de datos.
 * Esta anotación permite definir metadatos sobre cómo un campo debe ser representado
 * en la base de datos, incluyendo nombre, tipo, restricciones y características especiales.
 */
@Target(ElementType.FIELD) // Solo se puede aplicar a campos de clase

/**
 * Retención en tiempo de ejecución para permitir acceso mediante reflexión
 */
@Retention(RetentionPolicy.RUNTIME)

/**
 * Definición de la interfaz de anotación Column
 */
public @interface Column {
    
    /**
     * Define el nombre de la columna en la base de datos
     * @return String con el nombre de la columna
     */
    String name();

    /**
     * Especifica el tipo de dato de la columna en la base de datos
     * @return String con el tipo SQL (VARCHAR, INT, DATE, etc.)
     */
    String type();

    /**
     * Define la longitud máxima para tipos de dato que la requieren (VARCHAR, CHAR, etc.)
     * @return int con la longitud máxima, -1 indica que no se especifica longitud
     */
    int length() default -1;

    /**
     * Indica si la columna puede contener valores NULL
     * @return boolean true si permite NULL, false si es NOT NULL
     */
    boolean nullable() default true;

    /**
     * Marca si esta columna es una clave primaria
     * @return boolean true si es clave primaria, false en caso contrario
     */
    boolean primaryKey() default false;

    /**
     * Especifica si la columna tiene incremento automático (AUTO_INCREMENT)
     * @return boolean true si es auto-incrementable, false en caso contrario
     */
    boolean autoIncrement() default false;

    /**
     * Define si la columna debe tener valores únicos (UNIQUE constraint)
     * @return boolean true si requiere unicidad, false en caso contrario
     */
    boolean unique() default false;

    /**
     * Indica si esta columna es una clave foránea
     * @return boolean true si es clave foránea, false en caso contrario
     */
    boolean foreignKey() default false;

    /**
     * Especifica la tabla y columna de referencia para claves foráneas
     * @return String con el formato "tabla.columna" o cadena vacía si no aplica
     */
    String references() default "";
}
