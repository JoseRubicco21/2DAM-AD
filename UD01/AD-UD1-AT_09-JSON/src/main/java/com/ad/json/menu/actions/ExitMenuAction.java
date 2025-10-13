package com.ad.json.menu.actions;
import com.ad.json.menu.components.MenuAction;
import com.ad.json.menu.state.MenuResult;

public class ExitMenuAction extends MenuAction{

    @Override
    public MenuResult execute() {
        System.out.println("Saliendo del menu...");
        return MenuResult.EXIT;
    }
}
