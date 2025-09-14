import characters.mage.Hechizo;
import characters.mage.Mago;
import characters.monster.Monstruo;
import characters.monster.TipoDeMonstruo;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        ArrayList<Mago> magos = new ArrayList<>();
        ArrayList<Monstruo> monstruos = new ArrayList<>();    
        
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
    
    }
}
