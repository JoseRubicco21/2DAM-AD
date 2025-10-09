package menu.actions;


import menu.components.MenuAction;
import menu.state.MenuResult;
import models.Clasificacion;

public class LoadClasificationMenuAction extends MenuAction{

        private Clasificacion cls; 

    public LoadClasificationMenuAction(Clasificacion cls){
        this.cls = cls;
    }

    @Override
    public MenuResult execute() {
            Clasificacion.loadClasificacion();
            System.out.println("Clasificacion cargada correctamente.");
            return MenuResult.CONTINUE;
    }
    
}


