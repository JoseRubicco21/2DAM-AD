package com.ud2_at1.services.menu.actions.gestor_biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.MysqlConnection;
import com.ud2_at1.controllers.MySQLGeneralController;
import com.ud2_at1.services.connectors.MySQLConnector;
import com.ud2_at1.services.connectors.exceptions.MySQLConnectorException;
import com.ud2_at1.services.loaders.ConfigLoader;
import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.state.MenuResult;

public class CreateBibliotecaMenuAction extends MenuAction {


    private MySQLGeneralController dbController;

    public CreateBibliotecaMenuAction(){
        this.dbController = new MySQLGeneralController();
    }

    @Override
    public MenuResult execute() {
        dbController.create(this::createBiblioteca);
        dbController.read(this::showDatabase);
        return MenuResult.CONTINUE;
    }
    
    private boolean createBiblioteca() {
        boolean result;
        try {
            Connection connection = new MySQLConnector().getConnection();
            PreparedStatement pss = connection.prepareStatement("CREATE DATABASE IF NOT EXIST ?;");
            pss.setString(0, ConfigLoader.get("mysql.dbname"));
            result = pss.execute();
            Logger.success("Se ha creado la base de datos correctamente");
        } catch (MySQLConnectorException e) {
            e.displayExceptionMessage();
            result = false;
        } catch (SQLException e){
            Logger.error(e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean showDatabase(){
        boolean result;
        try {
            Connection connection = new MySQLConnector().getConnection();
            PreparedStatement pss = connection.prepareStatement("SHOW DATABASES;");
            ResultSet resultSet = pss.executeQuery();
            while (resultSet.next()) {
                Logger.info(resultSet.getString(0));
            }
            result = pss.execute();
        } catch (MySQLConnectorException e){
            e.displayExceptionMessage();
            result = false;
        } 
        catch (SQLException e ){
            Logger.error(e.getMessage());
            result = false;
        }
        return result;
    }
}
