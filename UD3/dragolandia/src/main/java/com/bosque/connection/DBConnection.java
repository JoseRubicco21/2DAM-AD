package com.bosque.connection;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class DBConnection {
    
    private static DBConnection instance = null;
    //private SessionFactory factory;
    private EntityManagerFactory factory;
    
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    private DBConnection() {
        //this.factory = new Configuration().configure().buildSessionFactory();
        this.factory = Persistence.createEntityManagerFactory("dragolandiaServizo");
    }

    public EntityManagerFactory getFactory() {
        return this.factory;
    }
    
}