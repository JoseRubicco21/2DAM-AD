import characters.Personaje;
import characters.mage.Hechizo;
import characters.mage.Mago;
import characters.monster.Monstruo;
import characters.monster.TipoDeMonstruo;
import characters.OrdernarPorVida;
import stages.Bosque;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void simularBatalla(Bosque bosque, Mago mago, Monstruo monstruo) {
        System.out.println("¡Comienza la batalla entre " + mago.getNombre() + " y " + monstruo.getNombre() + "!");
        while (mago.getVida() > 0 && monstruo.getVida() > 0) {
            mago.atacar(monstruo);
            if (monstruo.getVida() > 0) {
                monstruo.atacar(mago);
            }
            System.out.println(mago.getNombre() + " tiene " + mago.getVida() + " de vida restante.");
            System.out.println(monstruo.getNombre() + " tiene " + monstruo.getVida() + " de vida restante.");
        }
        if (mago.getVida() > 0) {
            System.out.println(mago.getNombre() + " ha ganado la batalla!");
        } else {
            System.out.println(monstruo.getNombre() + " ha ganado la batalla!");
        }
    }
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        ArrayList<Mago> magos = new ArrayList<>();
        ArrayList<Monstruo> monstruos = new ArrayList<>();    
        ArrayList<Personaje> personajes = new ArrayList<>();
        // Crear algunos magos de ejemplo
        Mago mago1 = new Mago("Gandalf", 100, 3, Hechizo.PIROMANCIA);
        Mago mago2 = new Mago("Merlin", 80, 4, Hechizo.INVOCACION);
        Mago mago3 = new Mago().createMago(sc);
        magos.add(mago1);
        magos.add(mago2);
        magos.add(mago3);

        // Crear un monstruo de ejemplo
        Monstruo monstruo1 = new Monstruo("Bob el temible", 30, TipoDeMonstruo.OGRO, 2);
        Monstruo monstruo2 = new Monstruo("Kevin, Destructor de mundos", 50, TipoDeMonstruo.TROLL, 3);
        Monstruo monstruo3 = new Monstruo().creaMonstruo(sc);
        monstruos.add(monstruo1);
        monstruos.add(monstruo2);
        monstruos.add(monstruo3);

        // We add all characters to a single list so we can display at the end.
        personajes.addAll(magos);
        personajes.addAll(monstruos);

        Bosque bosque = new Bosque("Bosque Tenebroso", 5, monstruo2);
        
        simularBatalla(bosque, mago1, monstruo2);

        System.out.println("Personajes ordenados por vida:");
        personajes.sort(new OrdernarPorVida().reversed());
        personajes.forEach(p -> System.out.println(p.getNombre() + " - Vida: " + p.getVida()));
    }
}
