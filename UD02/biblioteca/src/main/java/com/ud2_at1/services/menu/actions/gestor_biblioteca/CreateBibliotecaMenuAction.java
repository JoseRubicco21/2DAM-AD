package com.ud2_at1.services.menu.actions.gestor_biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.ud2_at1.services.connectors.MySQLConnector;
import com.ud2_at1.services.connectors.exceptions.MySQLConnectorException;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.state.MenuResult;

public class CreateBibliotecaMenuAction extends MenuAction {

    private Connection conection;

    public CreateBibliotecaMenuAction(){
        try {
            this.conection = new MySQLConnector().getConnection();
        } catch (MySQLConnectorException e) {
            e.displayExceptionMessage();
        }
    }

    @Override
    public MenuResult execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
    private createDatabase(){
        PreparedStatement pss = this.conection.prepareStatement("CREATE DATABASE IF NOT EXIST");
    }
}
