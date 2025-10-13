package com.ad.json.menu.actions;

import com.ad.json.menu.components.MenuAction;
import com.ad.json.menu.state.MenuResult;

public class UnimplementedMenuAction extends MenuAction {

    @Override
    public MenuResult execute() {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    
}