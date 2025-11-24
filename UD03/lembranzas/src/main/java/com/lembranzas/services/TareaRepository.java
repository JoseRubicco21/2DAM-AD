package com.lembranzas.services;

import com.lembranzas.model.Tarea;
import com.lembranzas.services.repository.exceptions.TareaNotFoundException;
import com.lembranzas.services.repository.interfaces.Respository;

import java.util.List;

/**
 * Repositorio para manejar las tareas.
 */
public class TareaRepository implements Respository<Tarea> {

    /**
     * Lista de tareas almacenadas en el repositorio.
     */
    private List<Tarea> tareas;

    /**
     * Instancia única del repositorio (patrón Singleton).
     */
    private static TareaRepository instancia;

    /**
     * Obtiene la instancia única del repositorio de tareas.
     * @return la instancia del TareaRepository
     */
    public static TareaRepository getInstancia() {
        if (instancia == null) {
            synchronized (TareaRepository.class) {
                if (instancia == null) {
                    instancia = new TareaRepository();
                }
            }
        }
        return instancia;
    }

    /**
     * Obtiene todas las tareas del repositorio.
     */
    @Override
    public List<Tarea> obtenerTodas() {
        return tareas;
    }

    /**
     * Obtiene una tarea por su ID.
     */
    @Override
    public Tarea obtenerPorId(int id) throws TareaNotFoundException {
        Tarea tareaEncontrada;
        tareaEncontrada = this.getTareas().get(id);
        if(tareaEncontrada == null){
            throw new TareaNotFoundException("La tarea no ha sido encontrada");
        }
        return tareaEncontrada;
    }

    /**
     * Agrega una nueva tarea al repositorio.
     */
    @Override
    public void agregar(Tarea entidad) {
        this.tareas.add(entidad);
    }

    /**
     * Elimina una tarea por su ID.
     */
    @Override
    public boolean eliminar(int id) throws TareaNotFoundException {
        Tarea t = this.tareas.get(id);
        if (t == null) {
            throw new TareaNotFoundException("La tarea no ha sido encontrada");
        }
        return this.tareas.remove(t);
    }

    /**
     * Actualiza una tarea existente.
     */
    @Override
    public void actualizar(int id, Tarea entidad) throws TareaNotFoundException {
        Tarea selectedTarea = this.tareas.get(id);
        if(selectedTarea == null){
            throw new TareaNotFoundException("La tarea no ha sido encontrada");
        } else {
            selectedTarea.setTitulo(entidad.getTitulo());
            selectedTarea.setDescripcion(entidad.getDescripcion());
            selectedTarea.setCompletada(entidad.isCompletada());
        }
    }

    /**
     * Marca una tarea como completada.
     */
    @Override
    public void marcarComoCompletada(int id) throws TareaNotFoundException {
        Tarea selectedTarea = this.tareas.get(id);
        if(selectedTarea == null){
            throw new TareaNotFoundException("La tarea no ha sido encontrada");
        } else {
            selectedTarea.setCompletada(true);
        }
    }

    /**
     * Obtiene la lista de tareas.
     * @return la lista de tareas
     */
    public List<Tarea> getTareas() {
        return tareas;
    }


    /**
     * Establece la lista de tareas.
     * @param tareas la lista de tareas a establecer
     */
    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

}
