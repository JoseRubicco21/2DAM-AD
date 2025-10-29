package com.ud2_at1;


import java.util.Scanner;

import com.ud2_at1.controllers.exceptions.GeneralControllerException;
import com.ud2_at1.dao.generic.DatabaseDAO;
import com.ud2_at1.dao.generic.DatabaseUserDAO;
import com.ud2_at1.models.generic.Database;

import com.ud2_at1.services.loaders.ConfigLoader;
import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.menu.Menu;
import com.ud2_at1.services.menu.exceptions.InvalidInputException;
import com.ud2_at1.services.menu.exceptions.InvalidOptionException;
import com.ud2_at1.services.menu.implementations.MainMenu;
import com.ud2_at1.services.menu.state.MenuState;

public class Main {



    private static void init(){
        ConfigLoader.getInstance();
        Logger.info("Initialized configuration");
        DatabaseUserDAO dbuserDao = new DatabaseUserDAO();
        DatabaseDAO dbDao = new DatabaseDAO();
        try{
        dbDao.init();
        dbuserDao.init();
        } catch(GeneralControllerException e){
            e.displayExceptionMessage();
        }
    }
    public static void main(String[] args) {
        // Initialize the database with a test connection.
        init();
        Scanner sc = new Scanner(System.in);
        Database db = new Database(
            ConfigLoader.get("mysql.dbname "), 
            ConfigLoader.get("mysql.charset"),
            ConfigLoader.get("mysql.charset.collation"));
        
        Menu mainMenu = new MainMenu(sc, db);
        mainMenu.setState(MenuState.ACTIVE);

        init();
        while (mainMenu.isActive()) {
            try {
                mainMenu.display().choose(sc).execute();
            } catch (InvalidInputException | InvalidOptionException e) {
                e.displayExceptionMessage();
            }
        }
    }
}