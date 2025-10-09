package menu.actions;

import java.util.Scanner;

import menu.components.MenuAction;
import menu.state.MenuResult;
import models.Clasificacion;
import models.Equipo;

public class CreateEquipoMenuAction extends MenuAction {

    private Scanner sc;
    private Clasificacion clasificacion;

    public CreateEquipoMenuAction(Scanner sc, Clasificacion clasificacion){
        this.sc = sc;
        this.clasificacion = clasificacion;
    }

    @Override
    public MenuResult execute() {
        Equipo eq = this.createEquipo(sc);
        this.clasificacion.addEquipo(eq);
        return MenuResult.CONTINUE;    
    }
    


    private Equipo getNameByInput(Scanner sc){
        String name = sc.nextLine();
        Equipo equipo = new Equipo(name);
        return equipo;
    }

    private void getVictoriasByInput(Scanner sc, Equipo eq){
        int victorias = Integer.parseInt(sc.nextLine());
        eq.setVictorias(victorias);
    }

    private void getDerrotasByInput(Scanner sc, Equipo eq){
        int derrotas = Integer.parseInt(sc.nextLine());
        eq.setDerrotas(derrotas);
    }

    private void getPuntosAFavorByInput(Scanner sc, Equipo eq){
        int puntosAFavor = Integer.parseInt(sc.nextLine());
        eq.setPuntosAFavor(puntosAFavor);
    }

    private void getPuntosEnContraByInput(Scanner sc, Equipo eq){
        int puntosEnContra = Integer.parseInt(sc.nextLine());
        eq.setPuntosEnContra(puntosEnContra);
    }

    private Equipo createEquipo(Scanner sc){
        System.out.println("Introduzca el nombre del equipo: ");
        Equipo eq = getNameByInput(sc);

        System.out.println("Introduza las victorias");
        this.getVictoriasByInput(sc, eq);

        System.out.println("Introduzca las derrotas");
        this.getDerrotasByInput(sc, eq);

        System.out.println("Introduzca los puntos a favor");
        this.getPuntosAFavorByInput(sc, eq);

        System.out.println("Introduzca los puntos en contra");
        this.getPuntosEnContraByInput(sc, eq);

        return eq;
    };
}
