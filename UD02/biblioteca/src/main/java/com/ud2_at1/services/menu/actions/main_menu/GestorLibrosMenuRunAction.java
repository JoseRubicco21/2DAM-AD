package com.ud2_at1.services.menu.actions.main_menu;

import java.util.Scanner;

import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.menu.Menu;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.exceptions.InvalidInputException;
import com.ud2_at1.services.menu.exceptions.InvalidOptionException;
import com.ud2_at1.services.menu.implementations.GestorLibrosMenu;
import com.ud2_at1.services.menu.state.MenuResult;
import com.ud2_at1.services.menu.state.MenuState;

public class GestorLibrosMenuRunAction extends MenuAction {

    private Scanner scanner;
  
    public GestorLibrosMenuRunAction(Scanner scanner){
        this.scanner = scanner;
    }

    @Override
    public MenuResult execute() {
        try {
            Menu librosMenu = new GestorLibrosMenu(scanner);
            librosMenu.setState(MenuState.ACTIVE);
            
            while (librosMenu.isActive()) {
                try {
                    librosMenu.display().choose(scanner).execute();
                } catch (InvalidInputException | InvalidOptionException e) {
                    e.displayExceptionMessage();
                }
            }
            
            return MenuResult.CONTINUE;
            
        } catch (Exception e) {
            Logger.error("Error en el gestor de libros: " + e.getMessage());
            return MenuResult.CONTINUE;
        }
    }
}
