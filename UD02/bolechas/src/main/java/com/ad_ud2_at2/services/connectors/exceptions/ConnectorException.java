package com.ad_ud2_at2.services.connectors.exceptions;

import com.ad_ud2_at2.services.exceptions.TemplateException;

/**
 * Excepción específica para errores relacionados con conectores de base de datos.
 * 
 * Esta excepción se lanza cuando ocurren problemas durante el establecimiento
 * o manejo de conexiones a bases de datos, incluyendo:
 * - Errores de configuración del driver
 * - Credenciales incorrectas
 * - Base de datos no disponible
 * - Problemas de red o timeout
 * - Configuración de URL de conexión incorrecta
 * 
 * Extiende de TemplateException y asigna automáticamente el código de error
 * "CODE -SQL0-" para facilitar la identificación y el logging de errores
 * relacionados con conectores.
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see TemplateException
 * @see com.ad_ud2_at2.services.connectors.GenericConnector
 */
public class ConnectorException extends TemplateException {
    
    /**
     * Construye una nueva ConnectorException con el mensaje especificado.
     * 
     * Automáticamente asigna el código de excepción "CODE -SQL0-" para
     * identificar que se trata de un error relacionado con conectores de
     * base de datos.
     * 
     * @param msg El mensaje detallado que describe la causa del error.
     *            Debe ser descriptivo para facilitar el diagnóstico del problema.
     * 
     * @example
     * <pre>
     * throw new ConnectorException("No se pudo conectar a MySQL: Host no encontrado");
     * throw new ConnectorException("Credenciales inválidas para la base de datos");
     * throw new ConnectorException("Driver JDBC no encontrado: com.mysql.cj.jdbc.Driver");
     * </pre>
     */
    public ConnectorException(String msg){
        super(msg);
        setExceptionCode("CODE -SQL0-");
    }
}
