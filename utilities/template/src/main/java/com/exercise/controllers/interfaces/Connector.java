package com.exercise.controllers.interfaces;
import java.sql.Connection;

public interface Connector {
    public void init();
    public Connection getConnection();
}
