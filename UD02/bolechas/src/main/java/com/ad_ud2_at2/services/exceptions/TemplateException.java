package com.ad_ud2_at2.services.exceptions;

import java.util.Date;
import com.ad_ud2_at2.services.logger.Logger;
import com.ad_ud2_at2.services.logger.enums.LogLevel;

/**
 * Excepción base personalizada que proporciona funcionalidades avanzadas de logging y visualización.
 * 
 * Esta clase extiende Exception para crear un sistema de excepciones personalizado que incluye
 * códigos de error, timestamps automáticos, niveles de logging configurables y métodos de
 * visualización formateada. Está diseñada para proporcionar información detallada sobre
 * errores y facilitar el debugging y monitoreo de la aplicación.
 * 
 * Características principales:
 * - Códigos de error personalizables para identificación única
 * - Timestamp automático de cuando ocurrió la excepción
 * - Integración con sistema de logging por niveles
 * - Visualización formateada con bordes ASCII para mejor legibilidad
 * - Múltiples constructores para diferentes escenarios de error
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see Logger
 * @see LogLevel
 * 
 * @example
 * <pre>
 * // Uso básico
 * throw new TemplateException("Error en procesamiento de datos");
 * 
 * // Con código personalizado
 * TemplateException ex = new TemplateException("Base de datos no disponible", LogLevel.CRITICAL);
 * ex.setExceptionCode("DB-001");
 * ex.displayExceptionMessage();
 * 
 * // Con causa subyacente
 * try {
 *     // código que puede fallar
 * } catch (SQLException e) {
 *     throw new TemplateException("Error en consulta SQL", e);
 * }
 * </pre>
 */
public class TemplateException extends Exception {

    /**
     * Serial version UID para serialización.
     * Necesario para mantener compatibilidad durante la serialización/deserialización.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Código de error personalizable que identifica el tipo específico de excepción.
     * Permite categorizar y filtrar errores para análisis y debugging.
     * Valor por defecto: "ERR: -CODE-"
     */
    private String exceptionCode = "ERR: -CODE-";
    
    /**
     * Timestamp de cuando se creó la excepción.
     * Se establece automáticamente en el momento de la construcción.
     */
    private Date exceptionDate;
    
    /**
     * Nivel de logging asociado con esta excepción.
     * Determina la severidad y cómo debe ser tratada por el sistema de logging.
     */
    private LogLevel logLevel;
    
    /**
     * Obtiene el código de error personalizado de la excepción.
     * 
     * @return String con el código de error actual
     */
    public String getExceptionCode() {
        return exceptionCode;
    }

    /**
     * Establece un código de error personalizado para la excepción.
     * 
     * @param exceptionCode El nuevo código de error a asignar
     * 
     * @example
     * <pre>
     * exception.setExceptionCode("DB-001"); // Error de base de datos
     * exception.setExceptionCode("NET-500"); // Error de red
     * exception.setExceptionCode("AUTH-403"); // Error de autenticación
     * </pre>
     */
    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    /**
     * Constructor por defecto que crea una excepción sin mensaje.
     * Inicializa automáticamente la fecha de excepción al momento actual.
     */
    public TemplateException() {
        super();
        this.exceptionDate = new Date();
    }
    
    /**
     * Constructor con mensaje descriptivo del error.
     * Establece automáticamente el nivel de log como ERROR.
     * 
     * @param message Descripción del error ocurrido
     */
    public TemplateException(String message) {
        super(message);
        this.exceptionDate = new Date();
        this.logLevel = LogLevel.ERROR;
    }

    /**
     * Constructor con mensaje y nivel de logging específico.
     * Permite especificar la severidad del error según el contexto.
     * 
     * @param message Descripción del error ocurrido
     * @param level Nivel de severidad para el logging (INFO, WARN, ERROR, etc.)
     * 
     * @example
     * <pre>
     * // Error crítico del sistema
     * new TemplateException("Fallo total del sistema", LogLevel.CRITICAL);
     * 
     * // Advertencia de configuración
     * new TemplateException("Configuración por defecto aplicada", LogLevel.WARN);
     * </pre>
     */
    public TemplateException(String message, LogLevel level){
        super(message);
        this.exceptionDate = new Date();
        this.logLevel = level;
    }

    /**
     * Constructor con mensaje y causa subyacente.
     * Permite encadenar excepciones manteniendo la traza original del error.
     * Establece automáticamente el nivel de log como ERROR.
     * 
     * @param message Descripción del error en el contexto actual
     * @param cause La excepción original que causó este error
     * 
     * @example
     * <pre>
     * try {
     *     connection.executeQuery(sql);
     * } catch (SQLException sqlEx) {
     *     throw new TemplateException("Error ejecutando consulta de usuarios", sqlEx);
     * }
     * </pre>
     */
    public TemplateException(String message, Throwable cause) {
        super(message, cause);
        this.exceptionDate = new Date();
        this.logLevel = LogLevel.ERROR;
    }
    
    /**
     * Muestra la excepción con formato visual mejorado usando un nivel de log específico.
     * 
     * Genera una salida formateada con bordes ASCII que incluye:
     * - Header con timestamp del error
     * - Código de error y mensaje
     * - Bordes decorativos para mejor visibilidad
     * 
     * @param logLevel El nivel de logging a usar para mostrar el mensaje
     * 
     * @example
     * <pre>
     * // Salida generada:
     * *----------------------------------------------*
     * | ERROR Mon Nov 07 10:30:15 CET 2025          |
     * *----------------------------------------------*
     * | DB-001 : Error conectando a base de datos   |
     * *----------------------------------------------*
     * </pre>
     */
    public void displayExceptionMessage(LogLevel logLevel) {
        String baseString = "| " + exceptionCode + " : " + this.getMessage() + " |";
        String errorHeader ="ERROR " + exceptionDate.toString() + " ".repeat(baseString.length()-(39));
        String boundaries = "*" + "-".repeat(baseString.length()-2) + "*";

        Logger.log(logLevel, String.format("%s",boundaries));
        Logger.log(logLevel, String.format("| %s |", errorHeader));
        Logger.log(logLevel, String.format("%s",boundaries));
        Logger.log(logLevel, String.format("%s", baseString));
        Logger.log(logLevel, String.format("%s",boundaries));
    }

    /**
     * Muestra la excepción con formato visual mejorado usando el nivel de log configurado.
     * 
     * Versión simplificada que utiliza el logLevel establecido en la construcción
     * de la excepción. Genera bordes con símbolos '=' para diferenciarlo del método anterior.
     * 
     * @example
     * <pre>
     * TemplateException ex = new TemplateException("Error crítico", LogLevel.ERROR);
     * ex.displayExceptionMessage(); // Usa LogLevel.ERROR automáticamente
     * 
     * // Salida generada:
     * *==============================================*
     * | ERROR Mon Nov 07 10:30:15 CET 2025          |
     * *==============================================*
     * | ERR: -CODE- : Error crítico                 |
     * *==============================================*
     * </pre>
     */
    public void displayExceptionMessage(){
        String baseString = "| " + exceptionCode + " : " + this.getMessage() + " |";
        String errorHeader ="ERROR " + exceptionDate.toString() + " ".repeat(baseString.length()-(39));
        String boundaries = "*" + "=".repeat(baseString.length()-2) + "*";

        Logger.log(this.logLevel, String.format("%s",boundaries));
        Logger.log(this.logLevel, String.format("| %s |", errorHeader));
        Logger.log(this.logLevel, String.format("%s",boundaries));
        Logger.log(this.logLevel, String.format("%s", baseString));
        Logger.log(this.logLevel, String.format("%s",boundaries));
    }
    
    /**
     * Obtiene la fecha y hora cuando se creó la excepción.
     * 
     * @return Date con el timestamp de creación de la excepción
     */
    public Date getExceptionDate() {
        return exceptionDate;
    }
    
    /**
     * Obtiene el nivel de logging configurado para esta excepción.
     * 
     * @return LogLevel que determina la severidad del error
     */
    public LogLevel getLogLevel() {
        return logLevel;
    }
    
    /**
     * Establece el nivel de logging para esta excepción.
     * 
     * @param logLevel El nuevo nivel de severidad a asignar
     */
    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }
}