package com.ad.json.menu.actions;

import java.util.ArrayList;
import java.util.List;

import com.ad.json.controllers.GestorProductos;
import com.ad.json.menu.components.MenuAction;
import com.ad.json.menu.state.MenuResult;
import com.ad.json.models.Producto;

public class GetProuctsAction extends MenuAction {

    private List<Producto> targetCollection; 

    public GetProuctsAction (List<Producto> targetCollection){
        this.targetCollection = targetCollection;
    }

    @Override
    public MenuResult execute() {
        this.targetCollection =  GestorProductos.readProductos();
        return MenuResult.CONTINUE;
    }
    
}
