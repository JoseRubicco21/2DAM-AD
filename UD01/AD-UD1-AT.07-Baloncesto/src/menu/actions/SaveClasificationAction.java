package menu.actions;

import java.io.IOException;

import menu.components.MenuAction;
import menu.state.MenuResult;
import models.Clasificacion;

public class SaveClasificationAction extends MenuAction {

    private Clasificacion cls; 

    public SaveClasificationAction(Clasificacion cls){
        this.cls = cls;
    }

    @Override
    public MenuResult execute() {
        try {
            Clasificacion.saveClasificacion(cls.getEquipos());
            System.out.println("Clasificacion guardada correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return MenuResult.CONTINUE;
    }
    
}
