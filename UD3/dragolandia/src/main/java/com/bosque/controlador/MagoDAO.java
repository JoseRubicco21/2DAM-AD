package com.bosque.controlador;

import java.util.List;

import com.bosque.connection.DBConnection;
import com.bosque.modelos.Mago;

import jakarta.persistence.EntityManager;

public class MagoDAO implements DAO<Mago> {


    @Override
    public Mago getById(int id) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            return session.find(Mago.class, id);
        } catch (Exception e) {
            System.out.println("Error al obtener el mago con id " + id + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Mago entity) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            session.getTransaction().begin();
            session.persist(entity);    
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al guardar el mago: " + e.getMessage());
        }
    }

    @Override
    public void update(Mago entity) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            session.getTransaction().begin();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al actualizar el mago: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            Mago mago = session.find(Mago.class, id);
            if(mago != null){
                session.getTransaction().begin();
                session.remove(mago);
                session.getTransaction().commit();
            } else {
                System.out.println("No se encontró el mago con id " + id + " para eliminar.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el mago con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    public List<Mago> getAll() {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            return session.createQuery("SELECT DISTINCT m FROM magos m LEFT JOIN FETCH m.conjuros", Mago.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener los magos: " + e.getMessage());
            return null;
        }
    }
    
}
