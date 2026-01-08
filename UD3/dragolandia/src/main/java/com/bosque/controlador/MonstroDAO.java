package com.bosque.controlador;

import java.util.List;

import org.hibernate.Session;

import com.bosque.connection.DBConnection;
import com.bosque.modelos.Monstro;

public class MonstroDAO implements DAO<Monstro> {


    @Override
    public Monstro getById(int id) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(Session session = dbConnection.getFactory().openSession()){
            return session.find(Monstro.class, id);
        } catch (Exception e) {
            System.out.println("Error al obtener el monstro con id " + id + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Monstro entity) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(Session session = dbConnection.getFactory().openSession()){
            session.beginTransaction();
            session.persist(entity);    
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al guardar el monstro: " + e.getMessage());  
        }
    }

    @Override
    public void update(Monstro entity) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(Session session = dbConnection.getFactory().openSession()){
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al actualizar el monstro: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(Session session = dbConnection.getFactory().openSession()){
            Monstro monstro = session.find(Monstro.class, id);
            if(monstro != null){
                session.beginTransaction();
                session.remove(monstro);
                session.getTransaction().commit();
            } else {
                System.out.println("No se encontró el monstro con id " + id + " para eliminar.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el monstro con id " + id + ": " + e.getMessage());
        }   
    }

    @Override
    public List<Monstro> getAll() {
        DBConnection dbConnection = DBConnection.getInstance();
        try(Session session = dbConnection.getFactory().openSession()){
            return session.createQuery("SELECT * FROM Monstro", Monstro.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener los monstruos: " + e.getMessage());
            return null;
        }
    }
    
}
