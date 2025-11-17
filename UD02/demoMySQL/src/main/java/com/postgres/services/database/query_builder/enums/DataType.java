package com.postgres.services.database.query_builder.enums;

/**
 * Enumeración que define los tipos de datos SQL estándar.
 * 
 * Esta enum proporciona una abstracción tipo-segura para los tipos de datos
 * más comunes utilizados en bases de datos relacionales. Incluye métodos
 * de conveniencia para generar definiciones de tipos con parámetros como
 * longitud, precisión y escala.
 * 
 * Facilita la creación de esquemas de base de datos de forma programática
 * y asegura compatibilidad con la mayoría de motores de base de datos SQL.
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see com.ad_ud2_at2.services.database.query_builder.dialects.SQLDialect
 */
public enum DataType {
    
    /**
     * Tipo de dato entero (INT).
     * Almacena números enteros con signo, típicamente de 32 bits.
     * Rango aproximado: -2,147,483,648 a 2,147,483,647.
     * 
     * @example
     * <pre>
     * id INT PRIMARY KEY
     * edad INT NOT NULL
     * </pre>
     */
    INT("INT"),
    
    /**
     * Tipo de dato cadena de caracteres de longitud variable (VARCHAR).
     * Almacena texto con longitud variable hasta un máximo especificado.
     * Más eficiente que CHAR para cadenas de longitud variable.
     * 
     * @example
     * <pre>
     * nombre VARCHAR(100)
     * email VARCHAR(255)
     * </pre>
     */
    VARCHAR("VARCHAR"),
    
    /**
     * Tipo de dato texto largo (TEXT).
     * Almacena cadenas de texto de gran tamaño (hasta 65,535 caracteres en MySQL).
     * Ideal para descripciones, comentarios y contenido extenso.
     * 
     * @example
     * <pre>
     * descripcion TEXT
     * contenido_articulo TEXT
     * </pre>
     */
    TEXT("TEXT"),
    
    /**
     * Tipo de dato fecha (DATE).
     * Almacena fechas sin información de hora.
     * Formato típico: YYYY-MM-DD.
     * 
     * @example
     * <pre>
     * fecha_nacimiento DATE
     * fecha_registro DATE DEFAULT CURRENT_DATE
     * </pre>
     */
    DATE("DATE"),
    
    /**
     * Tipo de dato fecha y hora (DATETIME).
     * Almacena fecha y hora completa con precisión de segundos.
     * Formato típico: YYYY-MM-DD HH:MM:SS.
     * 
     * @example
     * <pre>
     * fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP
     * ultima_actualizacion DATETIME
     * </pre>
     */
    DATETIME("DATETIME"),
    
    /**
     * Tipo de dato booleano (BOOLEAN).
     * Almacena valores lógicos verdadero/falso.
     * Implementación varía según el motor de BD (TINYINT(1) en MySQL).
     * 
     * @example
     * <pre>
     * activo BOOLEAN DEFAULT TRUE
     * es_administrador BOOLEAN DEFAULT FALSE
     * </pre>
     */
    BOOLEAN("BOOLEAN"),
    
    /**
     * Tipo de dato punto flotante de precisión simple (FLOAT).
     * Almacena números decimales con aproximadamente 7 dígitos de precisión.
     * Adecuado para valores que no requieren alta precisión.
     * 
     * @example
     * <pre>
     * precio FLOAT
     * coordenada_latitud FLOAT
     * </pre>
     */
    FLOAT("FLOAT"),
    
    /**
     * Tipo de dato punto flotante de precisión doble (DOUBLE).
     * Almacena números decimales con aproximadamente 15 dígitos de precisión.
     * Mayor precisión que FLOAT para cálculos científicos.
     * 
     * @example
     * <pre>
     * saldo_cuenta DOUBLE
     * calculo_cientifico DOUBLE
     * </pre>
     */
    DOUBLE("DOUBLE"),
    
    /**
     * Tipo de dato decimal de precisión fija (DECIMAL).
     * Almacena números decimales exactos con precisión y escala definidas.
     * Ideal para valores monetarios y cálculos que requieren exactitud.
     * 
     * @example
     * <pre>
     * precio DECIMAL(10,2)  -- 10 dígitos total, 2 decimales
     * salario DECIMAL(8,2)
     * </pre>
     */
    DECIMAL("DECIMAL"),
    
    /**
     * Tipo de dato cadena de caracteres de longitud fija (CHAR).
     * Almacena texto con longitud fija, rellenando con espacios si es necesario.
     * Más eficiente que VARCHAR para cadenas de longitud constante.
     * 
     * @example
     * <pre>
     * codigo_pais CHAR(2)  -- Siempre 2 caracteres
     * estado CHAR(1)       -- Un solo carácter
     * </pre>
     */
    CHAR("CHAR"),
    
    /**
     * Tipo de dato binario largo (BLOB).
     * Almacena datos binarios como imágenes, archivos, documentos.
     * BLOB = Binary Large Object.
     * 
     * @example
     * <pre>
     * imagen_perfil BLOB
     * archivo_adjunto BLOB
     * </pre>
     */
    BLOB("BLOB");

    /**
     * Representación en cadena del tipo de dato SQL.
     * Contiene el nombre exacto del tipo como aparece en DDL estándar.
     */
    private final String type;

    /**
     * Constructor privado para inicializar cada constante de la enumeración.
     * 
     * @param type La representación en cadena del tipo de dato SQL
     */
    DataType(String type) {
        this.type = type;
    }

    /**
     * Obtiene la representación en cadena del tipo de dato.
     * 
     * @return String con el nombre del tipo de dato SQL
     * 
     * @example
     * <pre>
     * DataType.VARCHAR.getType() → "VARCHAR"
     * DataType.INT.getType() → "INT"
     * </pre>
     */
    public String getType() {
        return type;
    }

    /**
     * Genera la definición del tipo de dato con una longitud específica.
     * Aplicable a tipos como VARCHAR, CHAR que requieren especificar tamaño.
     * 
     * @param length La longitud máxima del campo
     * @return String con la definición completa del tipo (ej: "VARCHAR(100)")
     * 
     * @example
     * <pre>
     * DataType.VARCHAR.withLength(255) → "VARCHAR(255)"
     * DataType.CHAR.withLength(10) → "CHAR(10)"
     * </pre>
     */
    public String withLength(int length) {
        return type + "(" + length + ")";
    }

    /**
     * Genera la definición del tipo de dato con precisión y escala.
     * Aplicable principalmente a tipos DECIMAL/NUMERIC que requieren
     * especificar el número total de dígitos y los decimales.
     * 
     * @param precision El número total de dígitos significativos
     * @param scale El número de dígitos después del punto decimal
     * @return String con la definición completa del tipo (ej: "DECIMAL(10,2)")
     * 
     * @example
     * <pre>
     * DataType.DECIMAL.withPrecision(10, 2) → "DECIMAL(10,2)"
     * DataType.FLOAT.withPrecision(7, 4) → "FLOAT(7,4)"
     * </pre>
     */
    public String withPrecision(int precision, int scale) {
        return type + "(" + precision + "," + scale + ")";
    }

    /**
     * Representación en cadena del tipo de dato.
     * Equivalente a getType() para facilitar el uso en concatenaciones.
     * 
     * @return String con el nombre del tipo de dato SQL
     */
    @Override
    public String toString() {
        return type;
    }
}