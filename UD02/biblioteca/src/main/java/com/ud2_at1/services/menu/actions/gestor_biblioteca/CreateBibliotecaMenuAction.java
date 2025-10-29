package com.ud2_at1.services.menu.actions.gestor_biblioteca;
import com.ud2_at1.services.logger.Logger;
import com.mysql.cj.log.Log;
import com.ud2_at1.controllers.MySQLGeneralController;
import com.ud2_at1.dao.generic.DatabaseDAO;
import com.ud2_at1.dao.generic.DatabaseUserDAO;
import com.ud2_at1.models.generic.Database;
import com.ud2_at1.services.database.DatabaseService;
import com.ud2_at1.services.menu.components.MenuAction;
import com.ud2_at1.services.menu.state.MenuResult;

public class CreateBibliotecaMenuAction extends MenuAction {



    private DatabaseDAO databaseDAO = new DatabaseDAO();

    public CreateBibliotecaMenuAction(){
    }

    @Override
    public MenuResult execute() {
        createDataBaseOperation(true);
        return MenuResult.CONTINUE;
    }
    
    private boolean createDataBaseOperation(boolean wantToDelete){
        boolean result;
        if(wantToDelete){
            Logger.info("Eliminando base de datos...");
            databaseDAO.deleteDatabase();
            Logger.success("Base de datos eliminada correctamente.");
        }
        
        Logger.info("Creando base de datos...");
        databaseDAO.createDatabase();
        Logger.success("Base de datos creada correctamente.");
        result = true;
        return result;
        }
}
