package com.postgres.services.menu.actions;

import com.postgres.services.logger.Logger;
import com.postgres.services.menu.state.MenuResult;

public class UnimplementedMenuAction extends MenuAction {

    @Override
    public MenuResult execute() {
        Logger.warning("Esta acción no ha sido implementada todavía.");
        return MenuResult.CONTINUE;
    }
}