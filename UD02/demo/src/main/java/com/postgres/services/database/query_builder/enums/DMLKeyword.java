package com.postgres.services.database.query_builder.enums;

/**
 * Enumeración que define las palabras clave específicas del DML (Data Manipulation Language).
 * 
 * Esta enum proporciona una abstracción tipo-segura para las palabras clave utilizadas
 * en la manipulación de datos dentro de las bases de datos. El DML incluye comandos
 * para consultar, insertar, actualizar y eliminar datos de las tablas.
 * 
 * Facilita la construcción programática de sentencias DML y reduce errores de sintaxis
 * al centralizar la definición de palabras clave específicas para operaciones de datos.
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see com.ad_ud2_at2.services.database.query_builder.dialects.SQLDialect
 */
public enum DMLKeyword {
    
    /**
     * Palabra clave SELECT para consultas de datos.
     * Utilizada para recuperar datos de una o más tablas.
     * 
     * @example
     * <pre>
     * SELECT * FROM usuarios;
     * SELECT nombre, email FROM clientes WHERE activo = TRUE;
     * </pre>
     */
    SELECT("SELECT"),
    
    /**
     * Palabra clave INSERT para inserción de datos.
     * Utilizada para añadir nuevos registros a una tabla.
     * 
     * @example
     * <pre>
     * INSERT INTO usuarios (nombre, email) VALUES ('Juan', 'juan@email.com');
     * INSERT INTO productos SELECT * FROM productos_temporales;
     * </pre>
     */
    INSERT("INSERT"),
    
    /**
     * Palabra clave UPDATE para actualización de datos.
     * Utilizada para modificar registros existentes en una tabla.
     * 
     * @example
     * <pre>
     * UPDATE usuarios SET activo = FALSE WHERE id = 1;
     * UPDATE productos SET precio = precio * 1.1 WHERE categoria = 'electronics';
     * </pre>
     */
    UPDATE("UPDATE"),
    
    /**
     * Palabra clave DELETE para eliminación de datos.
     * Utilizada para eliminar registros de una tabla.
     * 
     * @example
     * <pre>
     * DELETE FROM usuarios WHERE activo = FALSE;
     * DELETE FROM pedidos WHERE fecha < '2020-01-01';
     * </pre>
     */
    DELETE("DELETE"),
    
    /**
     * Palabra clave FROM para especificar tablas origen.
     * Utilizada en SELECT, DELETE y UPDATE para indicar las tablas involucradas.
     * 
     * @example
     * <pre>
     * SELECT * FROM usuarios;
     * DELETE FROM productos WHERE stock = 0;
     * UPDATE usuarios u FROM perfiles p SET u.activo = p.estado;
     * </pre>
     */
    FROM("FROM"),
    
    /**
     * Palabra clave INTO para especificar tabla destino.
     * Utilizada en INSERT para indicar la tabla donde insertar datos.
     * 
     * @example
     * <pre>
     * INSERT INTO usuarios (nombre, email) VALUES ('Ana', 'ana@email.com');
     * SELECT * INTO usuarios_backup FROM usuarios;
     * </pre>
     */
    INTO("INTO"),
    
    /**
     * Palabra clave VALUES para especificar valores de inserción.
     * Utilizada en INSERT para proporcionar los datos a insertar.
     * 
     * @example
     * <pre>
     * INSERT INTO productos (nombre, precio) VALUES ('Laptop', 999.99);
     * INSERT INTO usuarios VALUES (1, 'Juan', 'juan@email.com', TRUE);
     * </pre>
     */
    VALUES("VALUES"),
    
    /**
     * Palabra clave SET para especificar asignaciones.
     * Utilizada en UPDATE para indicar qué columnas actualizar y con qué valores.
     * 
     * @example
     * <pre>
     * UPDATE usuarios SET nombre = 'Juan Carlos', activo = TRUE WHERE id = 1;
     * UPDATE productos SET precio = precio * 0.9, stock = stock - 1;
     * </pre>
     */
    SET("SET"),
    
    /**
     * Palabra clave WHERE para especificar condiciones.
     * Utilizada para filtrar registros en SELECT, UPDATE, DELETE.
     * 
     * @example
     * <pre>
     * SELECT * FROM usuarios WHERE edad > 18;
     * DELETE FROM productos WHERE stock = 0;
     * UPDATE clientes SET activo = FALSE WHERE ultima_compra < '2023-01-01';
     * </pre>
     */
    WHERE("WHERE"),
    
    /**
     * Operador lógico AND para combinar condiciones.
     * Utilizado en WHERE para que se cumplan múltiples condiciones simultáneamente.
     * 
     * @example
     * <pre>
     * SELECT * FROM usuarios WHERE edad > 18 AND activo = TRUE;
     * DELETE FROM productos WHERE stock = 0 AND categoria = 'descontinuado';
     * </pre>
     */
    AND("AND"),
    
    /**
     * Operador lógico OR para alternativas de condiciones.
     * Utilizado en WHERE para que se cumpla al menos una de varias condiciones.
     * 
     * @example
     * <pre>
     * SELECT * FROM usuarios WHERE rol = 'admin' OR rol = 'moderador';
     * UPDATE productos SET descuento = 0.1 WHERE categoria = 'ropa' OR categoria = 'zapatos';
     * </pre>
     */
    OR("OR"),
    
    /**
     * Operador LIKE para búsquedas con patrones.
     * Utilizado para buscar registros que coincidan con un patrón de texto.
     * 
     * @example
     * <pre>
     * SELECT * FROM usuarios WHERE nombre LIKE 'Juan%';
     * SELECT * FROM productos WHERE descripcion LIKE '%laptop%';
     * </pre>
     */
    LIKE("LIKE"),
    
    /**
     * Operador BETWEEN para rangos de valores.
     * Utilizado para filtrar registros dentro de un rango específico.
     * 
     * @example
     * <pre>
     * SELECT * FROM productos WHERE precio BETWEEN 100 AND 500;
     * SELECT * FROM pedidos WHERE fecha BETWEEN '2024-01-01' AND '2024-12-31';
     * </pre>
     */
    BETWEEN("BETWEEN"),
    
    /**
     * Operador IS para comparaciones con NULL.
     * Utilizado para verificar valores nulos o no nulos.
     * 
     * @example
     * <pre>
     * SELECT * FROM usuarios WHERE telefono IS NULL;
     * SELECT * FROM productos WHERE descripcion IS NOT NULL;
     * </pre>
     */
    IS("IS"),
    
    /**
     * Cláusula ORDER BY para ordenar resultados.
     * Utilizada para especificar el orden de los registros en el resultado.
     * 
     * @example
     * <pre>
     * SELECT * FROM usuarios ORDER BY nombre ASC;
     * SELECT * FROM productos ORDER BY precio DESC, nombre ASC;
     * </pre>
     */
    ORDER_BY("ORDER BY"),
    
    /**
     * Cláusula GROUP BY para agrupar registros.
     * Utilizada para agrupar registros por una o más columnas para agregaciones.
     * 
     * @example
     * <pre>
     * SELECT categoria, COUNT(*) FROM productos GROUP BY categoria;
     * SELECT usuario_id, SUM(total) FROM pedidos GROUP BY usuario_id;
     * </pre>
     */
    GROUP_BY("GROUP BY"),
    
    /**
     * Cláusula HAVING para filtrar grupos.
     * Utilizada para filtrar resultados de GROUP BY basándose en funciones agregadas.
     * 
     * @example
     * <pre>
     * SELECT categoria, COUNT(*) FROM productos GROUP BY categoria HAVING COUNT(*) > 10;
     * SELECT usuario_id, SUM(total) FROM pedidos GROUP BY usuario_id HAVING SUM(total) > 1000;
     * </pre>
     */
    HAVING("HAVING"),
    
    /**
     * Cláusula LIMIT para limitar número de resultados.
     * Utilizada para restringir el número de registros devueltos por una consulta.
     * 
     * @example
     * <pre>
     * SELECT * FROM usuarios ORDER BY fecha_registro DESC LIMIT 10;
     * SELECT * FROM productos WHERE stock > 0 LIMIT 5;
     * </pre>
     */
    LIMIT("LIMIT"),
    
    /**
     * Palabra clave DISTINCT para eliminar duplicados.
     * Utilizada para obtener valores únicos en los resultados de una consulta.
     * 
     * @example
     * <pre>
     * SELECT DISTINCT categoria FROM productos;
     * SELECT DISTINCT usuario_id FROM pedidos WHERE fecha > '2024-01-01';
     * </pre>
     */
    DISTINCT("DISTINCT");

    /**
     * Representación en cadena de la palabra clave DML.
     * Contiene la sintaxis exacta como aparece en las sentencias DML estándar.
     */
    private final String keyword;

    /**
     * Constructor privado para inicializar cada constante de la enumeración.
     * 
     * @param keyword La representación en cadena de la palabra clave DML
     */
    DMLKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Obtiene la representación en cadena de la palabra clave DML.
     * 
     * @return String con la sintaxis SQL de la palabra clave
     * 
     * @example
     * <pre>
     * DMLKeyword.SELECT.getKeyword() → "SELECT"
     * DMLKeyword.WHERE.getKeyword() → "WHERE"
     * DMLKeyword.ORDER_BY.getKeyword() → "ORDER BY"
     * </pre>
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Representación en cadena de la palabra clave DML.
     * Equivalente a getKeyword() para facilitar el uso en concatenaciones.
     * 
     * @return String con la sintaxis SQL de la palabra clave
     */
    @Override
    public String toString() {
        return keyword;
    }
}