package com.ud2_at1.services.menu.actions.gestor_biblioteca;
import com.ud2_at1.controllers.MySQLGeneralController;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.state.MenuResult;

public class CreateBibliotecaMenuAction extends MenuAction {


    private MySQLGeneralController dbController;

    public CreateBibliotecaMenuAction(){
        this.dbController = new MySQLGeneralController();
    }

    @Override
    public MenuResult execute() {
        createDataBaseOperation(true);
        return MenuResult.CONTINUE;
    }
    
    private boolean createDataBaseOperation(boolean wantToDelete){
        boolean result;
        if(wantToDelete){
           result =  dbController.delete(MySQLGeneralController::deleteDatabase);
        }
           result = dbController.create(MySQLGeneralController::createDatabase);
        return result;
        }
}
