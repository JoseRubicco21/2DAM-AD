package com.ud2_at1.services.menu.actions.gestor_biblioteca;
import com.ud2_at1.services.logger.Logger;

import java.util.Scanner;

import com.ud2_at1.dao.generic.DatabaseDAO;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.state.MenuResult;

public class CreateBibliotecaMenuAction extends MenuAction {

    private Scanner scanner;

    public CreateBibliotecaMenuAction(Scanner scanner){
        this.scanner = scanner;
    }

    @Override
    public MenuResult execute() {
        return MenuResult.CONTINUE;
    }

}
