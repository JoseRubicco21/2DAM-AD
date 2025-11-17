package com.postgres.services.menu.actions;

import com.postgres.services.logger.Logger;
import com.postgres.services.menu.state.MenuResult;

public class ExitMenuAction extends MenuAction {

    @Override
    public MenuResult execute() {
        Logger.info("Saliendo del menú...");
        return MenuResult.EXIT;
    }
}