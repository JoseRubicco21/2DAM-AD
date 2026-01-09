package com.bosque.controlador;

import java.util.List;

import com.bosque.connection.DBConnection;
import com.bosque.modelos.Hechizo;

import jakarta.persistence.EntityManager;

public class HechizoDAO implements  DAO<Hechizo> {


    @Override
    public Hechizo getById(int id) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            return session.find(Hechizo.class, id);
        } catch (Exception e) {
            System.out.println("Error al obtener el hechizo con id " + id + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Hechizo entity) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            session.getTransaction().begin();
            session.persist(entity);    
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al guardar el hechizo: " + e.getMessage());
        }
    }

    @Override
    public void update(Hechizo entity) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            session.getTransaction().begin();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al actualizar el hechizo: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            Hechizo hechizo = session.find(Hechizo.class, id);
            if(hechizo != null){
                session.getTransaction().begin();
                session.remove(hechizo);
                session.getTransaction().commit();
            } else {
                System.out.println("No se encontró el hechizo con id " + id + " para eliminar.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el hechizo con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    public List<Hechizo> getAll() {
        DBConnection dbConnection = DBConnection.getInstance();
        try(EntityManager session = dbConnection.getFactory().createEntityManager()){
            return session.createQuery("SELECT h FROM hechizos h", Hechizo.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener los hechizos: " + e.getMessage());
            return null;
        }
    }
    
}
