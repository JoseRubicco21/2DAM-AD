package com.bosque.controlador;

import java.util.List;

public interface DAO<T> {
    public List<T> getAll();
    public T getById(int id);
    public void save(T entity);
    public void update(T entity);
    public void delete(int id);
}
