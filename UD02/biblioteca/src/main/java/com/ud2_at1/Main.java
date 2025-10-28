package com.ud2_at1;

import java.sql.DatabaseMetaData;
import java.util.Scanner;

import com.ud2_at1.controllers.MySQLGeneralController;
import com.ud2_at1.controllers.exceptions.GeneralControllerException;
import com.ud2_at1.models.generic.Database;
import com.ud2_at1.services.connectors.MySQLConnector;
import com.ud2_at1.services.loaders.ConfigLoader;
import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.menu.Menu;
import com.ud2_at1.services.menu.exceptions.InvalidInputException;
import com.ud2_at1.services.menu.exceptions.InvalidOptionException;
import com.ud2_at1.services.menu.implementations.MainMenu;
import com.ud2_at1.services.menu.state.MenuState;

public class Main {

    MySQLConnector connection;
    MySQLGeneralController generalController;

    private void init(){
        ConfigLoader.getInstance();
        Database db = new Database(
            ConfigLoader.get("mysql.dbname "), 
            ConfigLoader.get("mysql.charset"),
            ConfigLoader.get("mysql.charset.collation"));
            generalController = new MySQLGeneralController(db);

        Logger.info("Initialized configuration");
        try {
            generalController.init();
        } catch (GeneralControllerException e) {
            e.displayExceptionMessage();
        }
    }
    public static void main(String[] args) {
        // Initialize the database with a test connection.
        Scanner sc = new Scanner(System.in);
       
        Menu mainMenu = new MainMenu(sc);
        mainMenu.setState(MenuState.ACTIVE);
       
        init()
        while (mainMenu.isActive()) {
            try {
                mainMenu.display().choose(sc).execute();
            } catch (InvalidInputException | InvalidOptionException e) {
                e.displayExceptionMessage();
            }
        }
    }
}