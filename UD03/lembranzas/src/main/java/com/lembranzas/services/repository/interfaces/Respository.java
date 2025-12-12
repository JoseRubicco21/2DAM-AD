package com.lembranzas.services.repository.interfaces;

import java.util.List;

import com.lembranzas.services.repository.exceptions.TareaNotFoundException;
/**
 * Repositorio genérico para operaciones CRUD.
 *
 * @param <T> el tipo de entidad que maneja el repositorio
 */
public interface Respository<T> {
    /**
     * Obtiene todas las entidades del repositorio.
     * @return lista de todas las entidades
     */
    List<T> obtenerTodas();
    /**
     * Obtiene una entidad por su ID.
     * @param id el ID de la entidad
     * @return la entidad encontrada
     * @throws TareaNotFoundException si la entidad no es encontrada
     */
    T obtenerPorId(int id) throws TareaNotFoundException;

    /**
     * Agrega una nueva entidad al repositorio.
     * @param entidad la entidad a agregar
     */
    void agregar(T entidad);

    /**
     * Elimina una entidad del repositorio por su ID.
     * @param id el ID de la entidad a eliminar
     * @return true si la entidad fue eliminada, false en caso contrario
     * @throws TareaNotFoundException si la entidad no es encontrada
     */
    boolean eliminar(int id) throws TareaNotFoundException;

    /**
     * Actualiza una entidad existente en el repositorio.
     * @param id el ID de la entidad a actualizar
     * @param entidad la entidad con los datos actualizados
     * @throws TareaNotFoundException si la entidad no es encontrada
     */
    void actualizar(int id, T entidad) throws TareaNotFoundException;

    /**
     * Marca una entidad como completada por su ID.
     * @param id el ID de la entidad a marcar como completada
     * @throws TareaNotFoundException si la entidad no es encontrada
     */
    void marcarComoCompletada(int id) throws TareaNotFoundException;
    
} 
