package com.ud2_at1.controllers.intefaces;

import java.sql.Connection;
import java.sql.PreparedStatement;


@FunctionalInterface
public interface StatementOperation {
    public PreparedStatement statementOperation(Connection connection);
}
