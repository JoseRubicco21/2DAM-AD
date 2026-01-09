package com.bosque.controlador;

import java.util.List;

import com.bosque.connection.DBConnection;
import com.bosque.modelos.Dragon;

import jakarta.persistence.EntityManager;

public class DragonDAO implements DAO<Dragon> {



    @Override
    public Dragon getById(int id) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            return session.find(Dragon.class, id);
        } catch (Exception e) {
            System.out.println("Error al obtener el dragon con id " + id + ": " +
                e.getMessage());
            return null;   
        }
    }

    @Override
    public void save(Dragon entity) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            session.getTransaction().begin();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al guardar el dragon: " + e.getMessage());
        }
    }

    @Override
    public void update(Dragon entity) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            session.getTransaction().begin();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al actualizar el dragon: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            Dragon dragon = session.find(Dragon.class, id);
            if(dragon != null){
                session.getTransaction().begin();
                session.remove(dragon);
                session.getTransaction().commit();
            } else {
                System.out.println("No se encontró el dragon con id " + id + " para eliminar.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el dragon con id " + id + ": " + e.getMessage());
        }   
    }

    @Override
    public List<Dragon> getAll() {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            return session.createQuery("SELECT d FROM dragones d", Dragon.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener los dragones: " + e.getMessage());
            return null;
        }
    }
    
}
