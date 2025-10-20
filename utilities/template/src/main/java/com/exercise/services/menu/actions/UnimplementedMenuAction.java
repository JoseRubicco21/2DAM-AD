package com.exercise.services.menu.actions;

import java.io.File;
import java.util.Scanner;

import com.exercise.services.menu.components.MenuAction;
import com.exercise.services.menu.state.MenuResult;

public class UnimplementedMenuAction extends MenuAction {

    @Override
    public MenuResult execute() {
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    
}