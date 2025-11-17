package com.postgres.services.database.query_builder.enums;

/**
 * Enumeración que define las palabras clave específicas del DDL (Data Definition Language).
 * 
 * Esta enum proporciona una abstracción tipo-segura para las palabras clave utilizadas
 * en la definición y modificación de estructuras de base de datos. El DDL incluye
 * comandos para crear, modificar y eliminar objetos de base de datos como tablas,
 * índices, restricciones y bases de datos.
 * 
 * Facilita la construcción programática de sentencias DDL y reduce errores de sintaxis
 * al centralizar la definición de palabras clave específicas para operaciones de esquema.
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see com.ad_ud2_at2.services.database.query_builder.dialects.SQLDialect
 */
public enum DDLKeyword {
    
    /**
     * Palabra clave CREATE para crear objetos de base de datos.
     * Utilizada para crear tablas, bases de datos, índices, usuarios, etc.
     * 
     * @example
     * <pre>
     * CREATE TABLE usuarios (id INT PRIMARY KEY);
     * CREATE DATABASE tienda;
     * CREATE INDEX idx_nombre ON usuarios(nombre);
     * </pre>
     */
    CREATE("CREATE"),
    
    /**
     * Palabra clave DROP para eliminar objetos de base de datos.
     * Utilizada para eliminar tablas, bases de datos, índices, usuarios, etc.
     * 
     * @example
     * <pre>
     * DROP TABLE usuarios;
     * DROP DATABASE temporal;
     * DROP INDEX idx_nombre;
     * </pre>
     */
    DROP("DROP"),
    
    /**
     * Palabra clave ALTER para modificar objetos existentes.
     * Utilizada para modificar estructura de tablas, añadir/eliminar columnas, etc.
     * 
     * @example
     * <pre>
     * ALTER TABLE usuarios ADD COLUMN telefono VARCHAR(15);
     * ALTER TABLE productos DROP COLUMN descripcion_antigua;
     * </pre>
     */
    ALTER("ALTER"),
    
    /**
     * Palabra clave TABLE para especificar operaciones sobre tablas.
     * Utilizada en combinación con CREATE, DROP, ALTER para operaciones de tabla.
     * 
     * @example
     * <pre>
     * CREATE TABLE productos (...);
     * DROP TABLE usuarios;
     * ALTER TABLE pedidos ...;
     * </pre>
     */
    TABLE("TABLE"),
    
    /**
     * Palabra clave DATABASE para operaciones de base de datos.
     * Utilizada para crear, eliminar y seleccionar bases de datos.
     * 
     * @example
     * <pre>
     * CREATE DATABASE tienda;
     * DROP DATABASE test;
     * USE DATABASE produccion;
     * </pre>
     */
    DATABASE("DATABASE"),
    
    /**
     * Palabra clave INDEX para operaciones de índices.
     * Utilizada para crear y eliminar índices que mejoran el rendimiento de consultas.
     * 
     * @example
     * <pre>
     * CREATE INDEX idx_email ON usuarios(email);
     * DROP INDEX idx_nombre ON usuarios;
     * </pre>
     */
    INDEX("INDEX"),
    
    /**
     * Palabra clave CONSTRAINT para definir restricciones.
     * Utilizada para crear restricciones de integridad en tablas.
     * 
     * @example
     * <pre>
     * ALTER TABLE usuarios ADD CONSTRAINT uk_email UNIQUE(email);
     * ALTER TABLE pedidos ADD CONSTRAINT fk_usuario FOREIGN KEY(usuario_id) REFERENCES usuarios(id);
     * </pre>
     */
    CONSTRAINT("CONSTRAINT"),
    
    /**
     * Palabra clave ADD para añadir elementos.
     * Utilizada en ALTER TABLE para añadir columnas, restricciones, índices.
     * 
     * @example
     * <pre>
     * ALTER TABLE usuarios ADD COLUMN fecha_registro DATE;
     * ALTER TABLE productos ADD CONSTRAINT pk_id PRIMARY KEY(id);
     * </pre>
     */
    ADD("ADD"),
    
    /**
     * Palabra clave COLUMN para especificar operaciones sobre columnas.
     * Utilizada en ALTER TABLE para operaciones específicas de columnas.
     * 
     * @example
     * <pre>
     * ALTER TABLE usuarios ADD COLUMN telefono VARCHAR(15);
     * ALTER TABLE productos DROP COLUMN stock_minimo;
     * </pre>
     */
    COLUMN("COLUMN"),
    
    /**
     * Palabra clave RENAME para renombrar objetos.
     * Utilizada para cambiar nombres de tablas, columnas u otros objetos.
     * 
     * @example
     * <pre>
     * ALTER TABLE usuarios_temp RENAME TO usuarios;
     * ALTER TABLE productos RENAME COLUMN descripcion TO descripcion_completa;
     * </pre>
     */
    RENAME("RENAME"),
    
    /**
     * Palabra clave PRIMARY para definir claves primarias.
     * Utilizada en la definición de restricciones de clave primaria.
     * 
     * @example
     * <pre>
     * CREATE TABLE usuarios (id INT PRIMARY KEY);
     * ALTER TABLE productos ADD PRIMARY KEY(id);
     * </pre>
     */
    PRIMARY("PRIMARY"),
    
    /**
     * Palabra clave FOREIGN para definir claves foráneas.
     * Utilizada en la definición de restricciones de clave foránea.
     * 
     * @example
     * <pre>
     * ALTER TABLE pedidos ADD FOREIGN KEY(usuario_id) REFERENCES usuarios(id);
     * CREATE TABLE detalles (pedido_id INT, FOREIGN KEY(pedido_id) REFERENCES pedidos(id));
     * </pre>
     */
    FOREIGN("FOREIGN"),
    
    /**
     * Palabra clave KEY para especificar tipos de clave.
     * Utilizada en combinación con PRIMARY, FOREIGN para definir claves.
     * 
     * @example
     * <pre>
     * id INT PRIMARY KEY
     * usuario_id INT, FOREIGN KEY(usuario_id) REFERENCES usuarios(id)
     * </pre>
     */
    KEY("KEY"),
    
    /**
     * Palabra clave UNIQUE para restricciones de unicidad.
     * Asegura que los valores en una columna o combinación de columnas sean únicos.
     * 
     * @example
     * <pre>
     * email VARCHAR(255) UNIQUE
     * ALTER TABLE usuarios ADD CONSTRAINT uk_email UNIQUE(email);
     * </pre>
     */
    UNIQUE("UNIQUE"),
    
    /**
     * Palabra clave REFERENCES para especificar tablas referenciadas.
     * Utilizada en definiciones de clave foránea para indicar la tabla padre.
     * 
     * @example
     * <pre>
     * FOREIGN KEY(usuario_id) REFERENCES usuarios(id)
     * categoria_id INT REFERENCES categorias(id)
     * </pre>
     */
    REFERENCES("REFERENCES"),
    
    /**
     * Palabra clave CHECK para restricciones de validación.
     * Define condiciones que deben cumplirse para los valores de una columna.
     * 
     * @example
     * <pre>
     * edad INT CHECK(edad >= 0 AND edad <= 120)
     * precio DECIMAL(10,2) CHECK(precio > 0)
     * </pre>
     */
    CHECK("CHECK"),
    
    /**
     * Palabra clave USE para seleccionar una base de datos.
     * Establece la base de datos activa para las operaciones siguientes.
     * 
     * @example
     * <pre>
     * USE tienda;
     * USE DATABASE produccion;
     * </pre>
     */
    USE("USE"),
    
    /**
     * Palabra clave DATABASES para operaciones sobre múltiples bases de datos.
     * Utilizada en comandos como SHOW DATABASES.
     * 
     * @example
     * <pre>
     * SHOW DATABASES;
     * FLUSH PRIVILEGES ON ALL DATABASES;
     * </pre>
     */
    DATABASES("DATABASES"),
    
    /**
     * Palabra clave IDENTIFIED para especificar credenciales.
     * Utilizada en la creación de usuarios para establecer métodos de autenticación.
     * 
     * @example
     * <pre>
     * CREATE USER 'usuario'@'localhost' IDENTIFIED BY 'password';
     * ALTER USER 'admin'@'%' IDENTIFIED BY 'nueva_password';
     * </pre>
     */
    IDENTIFIED("IDENTIFIED"),
    
    /**
     * Palabra clave GRANT para otorgar permisos.
     * Utilizada para asignar privilegios a usuarios sobre objetos de base de datos.
     * 
     * @example
     * <pre>
     * GRANT SELECT, INSERT ON tienda.* TO 'usuario'@'localhost';
     * GRANT ALL PRIVILEGES ON database.* TO 'admin'@'%';
     * </pre>
     */
    GRANT("GRANT"),
    
    /**
     * Palabra clave FLUSH para refrescar cachés y privilegios.
     * Utilizada para aplicar cambios en permisos y limpiar cachés del sistema.
     * 
     * @example
     * <pre>
     * FLUSH PRIVILEGES;
     * FLUSH TABLES;
     * </pre>
     */
    FLUSH("FLUSH"),
    
    /**
     * Palabra clave NULL para valores nulos.
     * Utilizada en definiciones de columnas y restricciones.
     * 
     * @example
     * <pre>
     * descripcion TEXT NULL
     * fecha_eliminacion DATE DEFAULT NULL
     * </pre>
     */
    NULL("NULL"),
    
    /**
     * Palabra clave DEFAULT para valores por defecto.
     * Especifica el valor que tomará una columna si no se proporciona uno explícitamente.
     * 
     * @example
     * <pre>
     * activo BOOLEAN DEFAULT TRUE
     * fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP
     * </pre>
     */
    DEFAULT("DEFAULT"),
    
    /**
     * Palabra clave IF para condiciones en DDL.
     * Utilizada en construcciones condicionales como IF EXISTS, IF NOT EXISTS.
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
     * Utilizada en combinación con IF para verificar existencia de objetos.
     * 
     * @example
     * <pre>
     * DROP TABLE IF EXISTS temporal;
     * CREATE INDEX IF NOT EXISTS idx_email ON usuarios(email);
     * </pre>
     */
    EXISTS("EXISTS"),
    
    /**
     * Palabra clave BY para especificar criterios.
     * Utilizada en contextos como IDENTIFIED BY para passwords.
     * 
     * @example
     * <pre>
     * CREATE USER 'usuario'@'localhost' IDENTIFIED BY 'password';
     * ORDER BY fecha_creacion DESC
     * </pre>
     */
    BY("BY");

    /**
     * Representación en cadena de la palabra clave DDL.
     * Contiene la sintaxis exacta como aparece en las sentencias DDL estándar.
     */
    private final String keyword;

    /**
     * Constructor privado para inicializar cada constante de la enumeración.
     * 
     * @param keyword La representación en cadena de la palabra clave DDL
     */
    DDLKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Obtiene la representación en cadena de la palabra clave DDL.
     * 
     * @return String con la sintaxis SQL de la palabra clave
     * 
     * @example
     * <pre>
     * DDLKeyword.CREATE.getKeyword() → "CREATE"
     * DDLKeyword.ALTER.getKeyword() → "ALTER"
     * DDLKeyword.PRIMARY.getKeyword() → "PRIMARY"
     * </pre>
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Representación en cadena de la palabra clave DDL.
     * Equivalente a getKeyword() para facilitar el uso en concatenaciones.
     * 
     * @return String con la sintaxis SQL de la palabra clave
     */
    @Override
    public String toString() {
        return keyword;
    }
}