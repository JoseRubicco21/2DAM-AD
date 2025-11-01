package com.ad_ud2_at2.services.menu.actions;

import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class ExitMenuAction extends MenuAction {

    @Override
    public MenuResult execute() {
        Logger.info("Saliendo del menú...");
        return MenuResult.EXIT;
    }
}