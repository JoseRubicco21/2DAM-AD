package com.bosque.controlador;

import java.util.List;

import org.hibernate.Session;

import com.bosque.connection.DBConnection;
import com.bosque.modelos.Bosque;

public class BosqueDAO implements DAO<Bosque> {


    @Override
    public Bosque getById(int id) {
       DBConnection dbConnection = DBConnection.getInstance();
       try(Session session = dbConnection.getFactory().openSession()){
           return session.find(Bosque.class, id);
       } catch (Exception e) {
           System.out.println("Error al obtener el bosque con id " + id + ": " + e.getMessage());
           return null;
       }
    }

    @Override
    public void save(Bosque entity) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(Session session = dbConnection.getFactory().openSession()){
            session.beginTransaction();
            session.persist(entity);    
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al guardar el bosque: " + e.getMessage());
        }
    }

    @Override
    public void update(Bosque entity) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(Session session = dbConnection.getFactory().openSession()){
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al actualizar el bosque: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(Session session = dbConnection.getFactory().openSession()){
            Bosque bosque = session.find(Bosque.class, id);
            if(bosque != null){
                session.beginTransaction();
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
        try(var session = dbConnection.getFactory().openSession()){
            return session.createQuery("SELECT * FROM Bosque", Bosque.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener los bosques: " + e.getMessage());
            return null;
        }   
    }
    
}
