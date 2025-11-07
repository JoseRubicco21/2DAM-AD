package com.ad_ud2_at2.dao;

import java.util.List;

/**
 * Dao genérico.
 * Esta clase define los métodos que deben implementar las clases que quieran
 * ser un Dao.
 * La T es el tipo de objeto que se va a manejar y la K es el tipo de clave
 * primaria.
 * @param <T> Tipo de entidad que manejará el DAO
 * @param <K> Tipo de la clave primaria de la entidad
 */
public interface Dao<T, K> {

  /**
   * Obtiene una entidad por su ID
   * @param id Identificador de la entidad
   * @return La entidad encontrada o null si no existe
   */
  T get(K id);
  
  /**
   * Obtiene todas las entidades de la tabla
   * @return Lista con todas las entidades
   */
  List<T> getAll();
  
  /**
   * Guarda una nueva entidad en la base de datos
   * @param objeto Entidad a guardar
   * @return true si se guardó correctamente, false en caso contrario
   */
  boolean save(T objeto);
  
  /**
   * Elimina una entidad de la base de datos
   * @param obj Entidad a eliminar
   * @return true si se eliminó correctamente, false en caso contrario
   */
  boolean delete(T obj);
  
  /**
   * Elimina todas las entidades de la tabla
   * @return true si se eliminaron correctamente, false en caso contrario
   */
  boolean deleteAll();
  
  /**
   * Elimina una entidad por su ID
   * @param id Identificador de la entidad a eliminar
   * @return true si se eliminó correctamente, false en caso contrario
   */
  boolean deleteById(K id);
  
  /**
   * Actualiza una entidad existente
   * @param obj Entidad con los datos actualizados
   */
  void update(T obj);
}
