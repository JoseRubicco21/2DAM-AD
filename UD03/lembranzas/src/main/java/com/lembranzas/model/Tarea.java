package com.lembranzas.model;

/**
 * Modelo que representa una tarea.
 */
public class Tarea {
    private int id;
    private String titulo;
    private String descripcion;
    private boolean completada;
    
    /**
     * Constructor por defecto
     */
    public Tarea() {
    
    }

    /**
     * Constructor con parámetros
     * @param id Identificador único de la tarea
     * @param titulo Título de la tarea
     * @param descripcion Descripción de la tarea
     * @param completada Estado de completitud de la tarea
     */
    public Tarea(int id, String titulo, String descripcion, boolean completada) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.completada = completada;
    }
    
   
    /**
     * Getter del id
     * @return el id de la tarea
     */
    public int getId() {
        return id;
    }

    /**
     * Setter del id
     * @param id el id a establecer
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Getter del título
     * @return el título de la tarea
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Setter del título
     * @param titulo el título a establecer
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Getter de la descripción
     * @return la descripción de la tarea
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Setter de la descripción
     * @param descripcion la descripción a establecer
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Getter del estado de completitud
     * @return true si la tarea está completada, false en caso contrario
     */
    public boolean isCompletada() {
        return completada;
    }

    /**
     * Setter del estado de completitud
     * @param completada el estado a establecer
     */
    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
    
}