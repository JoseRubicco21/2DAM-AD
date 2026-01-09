package com.bosque.controlador;

import java.util.List;

import com.bosque.connection.DBConnection;
import com.bosque.modelos.Monstro;

import jakarta.persistence.EntityManager;

public class MonstroDAO implements DAO<Monstro> {


    @Override
    public Monstro getById(int id) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            return session.find(Monstro.class, id);
        } catch (Exception e) {
            System.out.println("Error al obtener el monstro con id " + id + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Monstro entity) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            session.getTransaction().begin();
            session.persist(entity);    
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al guardar el monstro: " + e.getMessage());  
        }
    }

    @Override
    public void update(Monstro entity) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            session.getTransaction().begin();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al actualizar el monstro: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            Monstro monstro = session.find(Monstro.class, id);
            if(monstro != null){
                session.getTransaction().begin();
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
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            return session.createQuery("SELECT m FROM monstros m", Monstro.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener los monstros: " + e.getMessage());
            return null;
        }
    }
    
}
