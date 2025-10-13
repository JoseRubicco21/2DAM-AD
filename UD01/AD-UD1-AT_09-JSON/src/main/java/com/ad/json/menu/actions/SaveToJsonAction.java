package com.ad.json.menu.actions;

import com.ad.json.menu.components.MenuAction;
import com.ad.json.menu.state.MenuResult;
import com.ad.json.models.Producto;

import java.util.List;

import com.ad.json.controllers.GestorProductos;
public class SaveToJsonAction extends MenuAction{

    private List<Producto> prods;

    public  SaveToJsonAction (List<Producto> prods){
        this.prods  = prods;
    }

    @Override
    public MenuResult execute() {
        GestorProductos.saveToJson(prods);
        return MenuResult.CONTINUE;
    }
    
}
