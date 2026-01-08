package com.bosque.controlador;

import java.util.List;

import org.hibernate.Session;

import com.bosque.connection.DBConnection;
import com.bosque.modelos.Mago;

public class MagoDAO implements DAO<Mago> {


    @Override
    public Mago getById(int id) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(Session session = dbConnection.getFactory().openSession()){
            return session.find(Mago.class, id);
        } catch (Exception e) {
            System.out.println("Error al obtener el mago con id " + id + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Mago entity) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(Session session = dbConnection.getFactory().openSession()){
            session.beginTransaction();
            session.persist(entity);    
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al guardar el mago: " + e.getMessage());
        }
    }

    @Override
    public void update(Mago entity) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(Session session = dbConnection.getFactory().openSession()){
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al actualizar el mago: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(Session session = dbConnection.getFactory().openSession()){
            Mago mago = session.find(Mago.class, id);
            if(mago != null){
                session.beginTransaction();
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
        try(Session session = dbConnection.getFactory().openSession()){
            return session.createQuery("SELECT * FROM Mago", Mago.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener los magos: " + e.getMessage());
            return null;
        }
    }
    
}
