package com.ad_ud2_at2.services.connectors;

import java.sql.Connection;

/**
 * Interfaz genérica para conectores de base de datos.
 * Define el contrato que deben cumplir todas las implementaciones de conectores
 * para diferentes tipos de bases de datos (MySQL, PostgreSQL, Oracle, etc.).
 * 
 * Esta interfaz sigue el patrón Strategy, permitiendo intercambiar
 * diferentes implementaciones de conectores sin modificar el código cliente.
 * 
 * Las implementaciones concretas deben manejar:
 * - Configuración específica del driver de base de datos
 * - Gestión de credenciales y parámetros de conexión
 * - Pool de conexiones (si es necesario)
 * - Manejo de excepciones específicas del proveedor
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see SQLConnector
 */
public interface GenericConnector {
    
    /**
     * Establece y retorna una conexión a la base de datos.
     * 
     * Las implementaciones deben:
     * - Cargar el driver apropiado para la base de datos
     * - Establecer la conexión usando los parámetros configurados
     * - Manejar errores de conexión y lanzar excepciones apropiadas
     * - Opcionalmente implementar pooling de conexiones
     * 
     * @return Connection objeto de conexión JDBC activa y lista para usar
     * @throws com.ad_ud2_at2.services.connectors.exceptions.ConnectorException 
     *         Si no se puede establecer la conexión por problemas de configuración,
     *         credenciales incorrectas, base de datos no disponible, etc.
     * @see java.sql.Connection
     * @see java.sql.DriverManager
     */
    public Connection getConnection();
}
