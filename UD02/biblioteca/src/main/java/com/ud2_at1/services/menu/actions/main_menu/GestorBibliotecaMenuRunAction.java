package com.ud2_at1.services.menu.actions.main_menu;

import java.util.Scanner;

import com.ud2_at1.models.generic.Database;
import com.ud2_at1.services.menu.Menu;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.exceptions.InvalidInputException;
import com.ud2_at1.services.menu.exceptions.InvalidOptionException;
import com.ud2_at1.services.menu.implementations.GestorBibliotecaMenu;
import com.ud2_at1.services.menu.state.MenuResult;
import com.ud2_at1.services.menu.state.MenuState;

public class GestorBibliotecaMenuRunAction extends MenuAction {

    private Scanner sc;
    private Menu menu;
    public GestorBibliotecaMenuRunAction(Scanner sc){
        this.sc = sc;
        this.menu = new GestorBibliotecaMenu(sc);
        this.menu.setState(MenuState.ACTIVE);
    }

    @Override
    public MenuResult execute() {
        while (this.menu.isActive()) {
            try {
                this.menu.display().choose(sc).execute();
            } catch (InvalidInputException | InvalidOptionException e) {
                e.displayExceptionMessage();
            }
        }
        return MenuResult.CONTINUE;
    }
    
}
