package com.ad_ud2_at2.services.menu.actions;

import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class UnimplementedMenuAction extends MenuAction {

    @Override
    public MenuResult execute() {
        Logger.warning("Esta acción no ha sido implementada todavía.");
        return MenuResult.CONTINUE;
    }
}