package com.lembranzas.services.repository.exceptions;

/**
 * Excepción lanzada cuando una tarea no es encontrada en el repositorio.
 */
public class TareaNotFoundException extends Exception {
    
    /**
     * Constructor de la excepción.
     * @param message el mensaje de la excepción
     */
    public TareaNotFoundException(String message) {
        super(message);
    }
    
}
