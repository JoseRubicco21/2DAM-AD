import java.util.Scanner;

import models.Bosque;
import models.mage.Hechizo;
import models.mage.Mago;
import models.monster.Monstruo;

public class App {

    public static Mago crearMago(Scanner sc){
        Mago mage = new Mago();
        System.out.println("Ingrese el nombre del mago:");
        mage.setNombre(sc.nextLine());
        System.out.println("Ingrese la vida del mago:");
        mage.setVida(Integer.parseInt(sc.nextLine()));
        System.out.println("Ingrese el nivel de magia del mago:");
        mage.setNivelMagia(Integer.parseInt(sc.nextLine()));
        // He's gonna be a necromancer.
        mage.aprenderHechizo(Hechizo.NECROMANCIA);
        return mage;
    }

    public static Monstruo crearMonstruo(Scanner sc){
        Monstruo monster = new Monstruo();
        System.out.println("Ingrese el nombre del monstruo:");
        monster.setNombre(sc.nextLine());
        System.out.println("Ingrese la vida del monstruo:");
        monster.setVida(Integer.parseInt(sc.nextLine()));
        System.out.println("Ingrese la fuerza del monstruo:");
        monster.setFuerza(Integer.parseInt(sc.nextLine()));
        return monster;
    }

    public static Bosque inicializarBosque(Monstruo boss){
        Bosque forest = new Bosque();
        forest.setNombre("Bosque de la desolacion");
        forest.setNivelPeligro(5);
        forest.setMonstruoJefe(boss);
        return forest;
    }

    public static void main(String[] args) throws Exception {
        // I'm not doing good state management of battle and not implementing complex yet suitable patterns here.
        // This is a very much just do what it is told and nothing else more. Or at least that's what I want it to be.
        // or as close as possible to that.

        Scanner sc = new Scanner(System.in);
        Mago mage = crearMago(sc);
        Monstruo monster = crearMonstruo(sc);
        Bosque forest = inicializarBosque(monster);
        
        do {
      
            System.out.println("Turno del mago:");
            mage.lanzarHechizo(monster);
            System.out.println("El monstruo ahora tiene " + monster.getVida() + " de vida.");
            
            // Check at the end of a turn to see if anyone has been defeated.
            if (monster.getVida() <= 0) {
                System.out.println("El monstruo ha sido derrotado!");
                System.out.println("El mago ha ganado la batalla y domina el bosque!");
                break;
            } else if (mage.getVida() <= 0) {
                System.out.println("El mago ha sido derrotado!");
                System.out.println("El monstruo sigue reinando en el bosque!");
                break;
            }
            
            System.out.println("Turno del monstruo:");
            monster.atacar(mage);
            System.out.println("El mago ahora tiene " + mage.getVida() + " de vida.");

       
        } while (mage.getVida() > 0 && monster.getVida() > 0);

    }
}
