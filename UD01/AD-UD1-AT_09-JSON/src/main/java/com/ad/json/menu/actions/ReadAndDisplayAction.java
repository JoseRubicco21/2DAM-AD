package com.ad.json.menu.actions;

import com.ad.json.controllers.GestorProductos;
import com.ad.json.menu.components.MenuAction;
import com.ad.json.menu.state.MenuResult;

public class ReadAndDisplayAction extends MenuAction{

    public ReadAndDisplayAction(){

    }

    @Override
    public MenuResult execute() {
        GestorProductos.readAndDisplayJson();
        return MenuResult.CONTINUE;
    }
    
}
