package com.ud2_at1.services.menu.actions.gestor_biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ud2_at1.controllers.MySQLGeneralController;
import com.ud2_at1.services.connectors.MySQLConnector;
import com.ud2_at1.services.connectors.exceptions.MySQLConnectorException;
import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.state.MenuResult;

public class CreateBibliotecaMenuAction extends MenuAction {

    private Connection conection;
    private MySQLGeneralController dbController;

    public CreateBibliotecaMenuAction(){
        try {
            this.conection = new MySQLConnector().getConnection();
            this.dbController = new MySQLGeneralController();
        } catch (MySQLConnectorException e) {
            e.displayExceptionMessage();
        }
    }

    @Override
    public MenuResult execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    

}
