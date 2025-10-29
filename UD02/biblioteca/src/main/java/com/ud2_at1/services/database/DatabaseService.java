package com.ud2_at1.services.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ud2_at1.controllers.intefaces.StatementOperation;
import com.ud2_at1.services.connectors.MySQLConnector;
import com.ud2_at1.services.connectors.exceptions.MySQLConnectorException;

public class DatabaseService {
    
    public static boolean executeStatement(StatementOperation operation) throws MySQLConnectorException {
        boolean result;
        try (Connection connection = MySQLConnector.getConnectionAsRoot();
             PreparedStatement pss = operation.statementOperation(connection)) {
            result = pss.execute();
        } catch (MySQLConnectorException e) {
            e.displayExceptionMessage();
            throw e;
        } catch (SQLException e) {
            throw new MySQLConnectorException(e.getMessage());
        } catch (UnsupportedOperationException e) {
            throw new MySQLConnectorException(e.getMessage());
        }
        return result;
    }
}
