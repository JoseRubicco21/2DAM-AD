package com.ad_ud2_at2.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación personalizada para mapear clases con tablas de base de datos.
 * Esta anotación permite especificar el nombre de la tabla en la base de datos
 * que corresponde a una clase del modelo de datos.
 */
@Target(ElementType.TYPE) // Solo se puede aplicar a clases, interfaces o enums
@Retention(RetentionPolicy.RUNTIME) // Disponible en tiempo de ejecución para reflexión
public @interface Table {
    
    /**
     * Define el nombre de la tabla en la base de datos
     * @return String con el nombre de la tabla
     */
    String name();
}
