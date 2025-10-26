package com.ud2_at1;

import java.util.Scanner;

import com.ud2_at1.services.connectors.MySQLConnector;
import com.ud2_at1.services.connectors.exceptions.MySQLConnectorException;
import com.ud2_at1.services.loaders.ConfigLoader;
import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.menu.Menu;
import com.ud2_at1.services.menu.exceptions.InvalidInputException;
import com.ud2_at1.services.menu.exceptions.InvalidOptionException;
import com.ud2_at1.services.menu.implementations.MainMenu;
import com.ud2_at1.services.menu.state.MenuState;

public class Main {

    MySQLConnector connection;
    private static void init(){
        ConfigLoader.getInstance();
        Logger.info("Initialized configuration");
    }
    public static void main(String[] args) {
        // Initialize the database with a test connection.
        Scanner sc = new Scanner(System.in);
       
        Menu mainMenu = new MainMenu(sc);
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