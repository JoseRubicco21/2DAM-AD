package com.bosque.controlador;

import java.util.List;

import com.bosque.connection.DBConnection;
import com.bosque.modelos.Bosque;

import jakarta.persistence.EntityManager;

public class BosqueDAO implements DAO<Bosque> {


    @Override
    public Bosque getById(int id) {
       DBConnection dbConnection = DBConnection.getInstance();
       try(EntityManager session = dbConnection.getFactory().createEntityManager()){
           return session.find(Bosque.class, id);
       } catch (Exception e) {
           System.out.println("Error al obtener el bosque con id " + id + ": " + e.getMessage());
           return null;
       }
    }

    @Override
    public void save(Bosque entity) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            session.getTransaction().begin();
            session.persist(entity);    
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al guardar el bosque: " + e.getMessage());
        }
    }

    @Override
    public void update(Bosque entity) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            session.getTransaction().begin();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al actualizar el bosque: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            Bosque bosque = session.find(Bosque.class, id);
            if(bosque != null){
                session.getTransaction().begin();
                session.remove(bosque);
                session.getTransaction().commit();
            } else {
                System.out.println("No se encontró el bosque con id " + id + " para eliminar.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el bosque con id " + id + ": " + e.getMessage());
        }   
    }

    @Override
    public List<Bosque> getAll() {
        DBConnection  dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            return session.createQuery("SELECT b FROM bosques b", Bosque.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener los bosques: " + e.getMessage());
            return null;
        }   
    }
    
}
