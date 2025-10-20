package com.exercise.services.menu.actions;
import com.exercise.services.menu.components.MenuAction;
import com.exercise.services.menu.state.MenuResult;

public class ExitMenuAction extends MenuAction{

    @Override
    public MenuResult execute() {
        System.out.println("Saliendo del menu...");
        return MenuResult.EXIT;
    }
}
