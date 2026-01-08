package com.bosque.connection;

import java.util.function.Consumer;
import java.util.function.Function;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class DBConnection {
    
    private static DBConnection instance = null;
    private SessionFactory factory;
    
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    private DBConnection() {
        this.factory = new Configuration().configure().buildSessionFactory();
        
    }

    public SessionFactory getFactory() {
        return this.factory;
    }

    public <R> R execute(Function<Session, R> operation){
        Transaction transaction = null;
        try(Session session = this.factory.openSession()){
            transaction = session.beginTransaction();
            R result = operation.apply(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void executeTx(Consumer<Session> operation) {
        Transaction transaction = null;
        try (Session session = this.factory.openSession()) {
            transaction = session.beginTransaction();
            operation.accept(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
    
}