package menu.actions;

import menu.components.MenuAction;
import menu.state.MenuResult;
import models.Clasificacion;

public class ShowEquipoAction extends MenuAction{

    private Clasificacion clasificacion;

    public ShowEquipoAction (Clasificacion cls){
        this.clasificacion = cls;
    }

    @Override
    public MenuResult execute() {
        System.out.println(this.clasificacion);
        return MenuResult.CONTINUE;
    }

    
}
