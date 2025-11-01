package com.ad_ud2_at2.services.menu.components;

import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.actions.UnimplementedMenuAction;

// A class that defines the options of a menu, with their display text and command.
public class MenuOption {
    private String description;
    private MenuAction action;

    // We default to Unimplemented menu action if there's no action defined when building.
    public MenuOption(String description){
        this.description = description;
        this.action = new UnimplementedMenuAction();
    }

    public MenuOption(String description, MenuAction action){
        this.description = description;
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MenuAction getAction() {
        return action;
    }

    public void setAction(MenuAction action) {
        this.action = action;
    }
}
