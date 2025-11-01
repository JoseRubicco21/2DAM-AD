package com.ud2_at1;

import java.util.Scanner;

import com.ud2_at1.models.generic.Database;
import com.ud2_at1.services.database.DatabaseService;
import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.menu.Menu;
import com.ud2_at1.services.menu.exceptions.InvalidInputException;
import com.ud2_at1.services.menu.exceptions.InvalidOptionException;
import com.ud2_at1.services.menu.implementations.MainMenu;
import com.ud2_at1.services.menu.state.MenuState;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Initialize database using service layer
        Database db = DatabaseService.initializeDatabase();
        
        if (db == null) {
            Logger.error("Failed to initialize database. Exiting...");
            sc.close();
            return;
        }
        
        Menu mainMenu = new MainMenu(sc, db);
        mainMenu.setState(MenuState.ACTIVE);

        while (mainMenu.isActive()) {
            try {
                mainMenu.display().choose(sc).execute();
            } catch (InvalidInputException | InvalidOptionException e) {
                e.displayExceptionMessage();
            }
        }
        
        sc.close();
        Logger.info("Application terminated");
    }
}