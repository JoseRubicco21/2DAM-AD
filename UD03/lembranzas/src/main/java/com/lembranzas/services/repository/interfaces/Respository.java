package com.lembranzas.services.repository.interfaces;

import java.util.List;

import com.lembranzas.services.repository.exceptions.TareaNotFoundException;
/**
 * Repositorio genérico para operaciones CRUD.
 *
 * @param <T> el tipo de entidad que maneja el repositorio
 */
public interface Respository<T> {
    List<T> obtenerTodas();
    T obtenerPorId(int id) throws TareaNotFoundException;
    void agregar(T entidad);
    boolean eliminar(int id) throws TareaNotFoundException;
    void actualizar(int id, T entidad) throws TareaNotFoundException;
    void marcarComoCompletada(int id) throws TareaNotFoundException;
    
} 
