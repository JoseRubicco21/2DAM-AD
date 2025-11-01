package com.ad_ud2_at2.services.menu.actions.main_menu;

import java.util.Scanner;

import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.implementations.GestorClientesMenu;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.menu.state.MenuState;

public class GestorClientesMenuRunAction extends MenuAction {

    private Scanner sc;

    public GestorClientesMenuRunAction(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public MenuResult execute() {
        GestorClientesMenu gestorMenu = new GestorClientesMenu(sc);
        gestorMenu.setState(MenuState.ACTIVE);
        
        while (gestorMenu.isActive()) {
            try {
                gestorMenu.display().choose(sc).execute();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        return MenuResult.CONTINUE;
    }
}