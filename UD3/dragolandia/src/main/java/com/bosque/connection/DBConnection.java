package com.bosque.connection;

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

    public void executeVoid(Function<Session, Void> operation){
        Transaction transaction = null;
        try(Session session = this.factory.openSession()){
            transaction = session.beginTransaction();
            operation.apply(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
    
}