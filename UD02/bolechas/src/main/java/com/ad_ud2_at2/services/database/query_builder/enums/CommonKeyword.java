package com.ad_ud2_at2.services.database.query_builder.enums;

/**
 * Enumeración que define palabras clave y símbolos SQL comunes.
 * 
 * Esta enum proporciona una abstracción tipo-segura para las palabras clave,
 * operadores y símbolos más utilizados en la construcción de consultas SQL.
 * Facilita la generación de SQL consistente y reduce errores de sintaxis
 * al centralizar la definición de elementos comunes del lenguaje SQL.
 * 
 * Incluye palabras clave para DDL, DML, operadores de comparación,
 * símbolos especiales y elementos de sintaxis SQL estándar.
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see com.ad_ud2_at2.services.database.query_builder.dialects.SQLDialect
 */
public enum CommonKeyword {
    
    /**
     * Palabra clave IF para estructuras condicionales.
     * Utilizada en construcciones como IF EXISTS, IF NOT EXISTS.
     * 
     * @example
     * <pre>
     * DROP TABLE IF EXISTS usuarios;
     * CREATE DATABASE IF NOT EXISTS tienda;
     * </pre>
     */
    IF("IF"),
    
    /**
     * Palabra clave EXISTS para verificar existencia.
     * Utilizada en subconsultas y verificaciones de existencia de objetos.
     * 
     * @example
     * <pre>
     * SELECT * FROM usuarios WHERE EXISTS (SELECT 1 FROM pedidos WHERE usuario_id = usuarios.id);
     * DROP TABLE IF EXISTS productos;
     * </pre>
     */
    EXISTS("EXISTS"),
    
    /**
     * Palabra clave NOT para negación lógica.
     * Utilizada para negar condiciones, restricciones y operadores.
     * 
     * @example
     * <pre>
     * SELECT * FROM usuarios WHERE NOT activo;
     * ALTER TABLE productos ADD CONSTRAINT precio_positivo CHECK (precio NOT NULL);
     * </pre>
     */
    NOT("NOT"),
    
    /**
     * Palabra clave ON para especificar condiciones de JOIN y triggers.
     * Utilizada en cláusulas JOIN y definiciones de triggers.
     * 
     * @example
     * <pre>
     * SELECT * FROM usuarios u JOIN pedidos p ON u.id = p.usuario_id;
     * CREATE TRIGGER actualizar_fecha ON usuarios FOR UPDATE;
     * </pre>
     */
    ON("ON"),
    
    /**
     * Palabra clave ALL para operaciones sobre todos los elementos.
     * Utilizada en subconsultas, permisos y operaciones masivas.
     * 
     * @example
     * <pre>
     * SELECT * FROM productos WHERE precio > ALL (SELECT precio FROM ofertas);
     * GRANT ALL PRIVILEGES ON database.* TO 'usuario'@'localhost';
     * </pre>
     */
    ALL("ALL"),
    
    /**
     * Palabra clave TO para especificar destinos y rangos.
     * Utilizada en comandos GRANT, BACKUP TO, y otras operaciones.
     * 
     * @example
     * <pre>
     * GRANT SELECT ON tabla TO 'usuario'@'localhost';
     * BACKUP DATABASE TO '/ruta/backup.sql';
     * </pre>
     */
    TO("TO"),
    
    /**
     * Palabra clave BY para especificar criterios de ordenación y agrupación.
     * Utilizada en cláusulas ORDER BY, GROUP BY.
     * 
     * @example
     * <pre>
     * SELECT * FROM usuarios ORDER BY nombre;
     * SELECT categoria, COUNT(*) FROM productos GROUP BY categoria;
     * </pre>
     */
    BY("BY"),
    
    /**
     * Palabra clave ASC para ordenación ascendente.
     * Especifica que los resultados deben ordenarse de menor a mayor.
     * 
     * @example
     * <pre>
     * SELECT * FROM productos ORDER BY precio ASC;
     * SELECT nombre FROM usuarios ORDER BY fecha_registro ASC;
     * </pre>
     */
    ASC("ASC"),
    
    /**
     * Palabra clave DESC para ordenación descendente.
     * Especifica que los resultados deben ordenarse de mayor a menor.
     * 
     * @example
     * <pre>
     * SELECT * FROM productos ORDER BY precio DESC;
     * SELECT * FROM ventas ORDER BY fecha DESC LIMIT 10;
     * </pre>
     */
    DESC("DESC"),
    
    /**
     * Palabra clave CASCADE para operaciones en cascada.
     * Utilizada en restricciones de clave foránea y eliminaciones.
     * 
     * @example
     * <pre>
     * ALTER TABLE pedidos ADD FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE;
     * DROP TABLE usuarios CASCADE;
     * </pre>
     */
    CASCADE("CASCADE"),
    
    /**
     * Operador de igualdad (=).
     * Utilizado para comparaciones de igualdad en WHERE, JOIN y asignaciones.
     * 
     * @example
     * <pre>
     * SELECT * FROM usuarios WHERE activo = TRUE;
     * UPDATE productos SET stock = 0 WHERE categoria = 'descontinuado';
     * </pre>
     */
    EQUALS("="),
    
    /**
     * Símbolo coma (,) para separar elementos.
     * Utilizado para separar columnas, valores, parámetros en listas.
     * 
     * @example
     * <pre>
     * SELECT nombre, email, telefono FROM usuarios;
     * INSERT INTO productos (nombre, precio, stock) VALUES ('Laptop', 999.99, 10);
     * </pre>
     */
    COMMA(","),
    
    /**
     * Símbolo punto y coma (;) para terminar sentencias.
     * Utilizado para finalizar comandos SQL y separar múltiples sentencias.
     * 
     * @example
     * <pre>
     * SELECT * FROM usuarios;
     * INSERT INTO productos (nombre) VALUES ('Producto1'); SELECT LAST_INSERT_ID();
     * </pre>
     */
    SEMICOLON(";"),
    
    /**
     * Símbolo asterisco (*) para seleccionar todas las columnas.
     * Utilizado en SELECT para obtener todos los campos de una tabla.
     * 
     * @example
     * <pre>
     * SELECT * FROM usuarios;
     * SELECT COUNT(*) FROM pedidos;
     * </pre>
     */
    ASTERISK("*"),
    
    /**
     * Símbolo porcentaje (%) para comodines en LIKE.
     * Utilizado como wildcard para coincidir con cualquier secuencia de caracteres.
     * 
     * @example
     * <pre>
     * SELECT * FROM usuarios WHERE nombre LIKE 'Juan%';
     * SELECT * FROM productos WHERE descripcion LIKE '%laptop%';
     * </pre>
     */
    PERCENT("%"),
    
    /**
     * Símbolo arroba (@) para variables y hosts.
     * Utilizado en variables de sesión y especificación de hosts en MySQL.
     * 
     * @example
     * <pre>
     * SET @variable = 'valor';
     * GRANT SELECT ON database.* TO 'usuario'@'localhost';
     * </pre>
     */
    AT_SIGN("@"),
    
    /**
     * Palabra clave DATABASE para operaciones de base de datos.
     * Utilizada en comandos CREATE, DROP, USE DATABASE.
     * 
     * @example
     * <pre>
     * CREATE DATABASE tienda;
     * DROP DATABASE IF EXISTS test;
     * USE DATABASE produccion;
     * </pre>
     */
    DATABASE("DATABASE"),
    
    /**
     * Palabra clave TABLE para operaciones de tabla.
     * Utilizada en comandos CREATE, DROP, ALTER TABLE.
     * 
     * @example
     * <pre>
     * CREATE TABLE usuarios (id INT PRIMARY KEY);
     * DROP TABLE productos;
     * ALTER TABLE pedidos ADD COLUMN fecha_entrega DATE;
     * </pre>
     */
    TABLE("TABLE"),
    
    /**
     * Palabra clave USER para gestión de usuarios.
     * Utilizada en comandos CREATE, DROP USER y gestión de permisos.
     * 
     * @example
     * <pre>
     * CREATE USER 'nuevo_usuario'@'localhost' IDENTIFIED BY 'password';
     * DROP USER 'usuario_viejo'@'localhost';
     * </pre>
     */
    USER("USER"),
    
    /**
     * Palabra clave PRIVILEGES para gestión de permisos.
     * Utilizada en comandos GRANT, REVOKE para asignar o revocar privilegios.
     * 
     * @example
     * <pre>
     * GRANT ALL PRIVILEGES ON database.* TO 'usuario'@'localhost';
     * REVOKE DELETE PRIVILEGES ON tabla FROM 'usuario'@'localhost';
     * </pre>
     */
    PRIVILEGES("PRIVILEGES"),
    
    /**
     * Palabra clave SHOW para mostrar información del sistema.
     * Utilizada para obtener información sobre bases de datos, tablas, usuarios, etc.
     * 
     * @example
     * <pre>
     * SHOW DATABASES;
     * SHOW TABLES;
     * SHOW COLUMNS FROM usuarios;
     * </pre>
     */
    SHOW("SHOW");

    /**
     * Representación en cadena de la palabra clave o símbolo SQL.
     * Contiene la sintaxis exacta como aparece en las sentencias SQL estándar.
     */
    private final String keyword;

    /**
     * Constructor privado para inicializar cada constante de la enumeración.
     * 
     * @param keyword La representación en cadena de la palabra clave SQL
     */
    CommonKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Obtiene la representación en cadena de la palabra clave.
     * 
     * @return String con la sintaxis SQL de la palabra clave
     * 
     * @example
     * <pre>
     * CommonKeyword.SELECT.getKeyword() → "SELECT"
     * CommonKeyword.EQUALS.getKeyword() → "="
     * CommonKeyword.ASTERISK.getKeyword() → "*"
     * </pre>
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Representación en cadena de la palabra clave.
     * Equivalente a getKeyword() para facilitar el uso en concatenaciones.
     * 
     * @return String con la sintaxis SQL de la palabra clave
     */
    @Override
    public String toString() {
        return keyword;
    }
}