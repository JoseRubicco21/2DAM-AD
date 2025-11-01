package com.ad_ud2_at2.services.menu.actions.main_menu;

import java.util.Scanner;

import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.implementations.GestorPedidosMenu;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.menu.state.MenuState;

public class GestorPedidosMenuRunAction extends MenuAction {

    private Scanner sc;

    public GestorPedidosMenuRunAction(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public MenuResult execute() {
        GestorPedidosMenu gestorMenu = new GestorPedidosMenu(sc);
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