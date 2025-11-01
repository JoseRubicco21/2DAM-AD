package com.ad_ud2_at2.services.menu.actions.main_menu;

import java.util.Scanner;

import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.implementations.GestorProductosMenu;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.menu.state.MenuState;

public class GestorProductosMenuRunAction extends MenuAction {

    private Scanner sc;

    public GestorProductosMenuRunAction(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public MenuResult execute() {
        GestorProductosMenu gestorMenu = new GestorProductosMenu(sc);
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