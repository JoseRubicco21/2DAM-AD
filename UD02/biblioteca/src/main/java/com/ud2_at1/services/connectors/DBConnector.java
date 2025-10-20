package com.ud2_at1.services.connectors;

import java.sql.Connection;

public interface DBConnector {

    public Connection getConnection();
    public Connection getConnectionPool();
}