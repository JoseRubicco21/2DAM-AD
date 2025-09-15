package characters;

import java.util.Comparator;

public class OrdernarPorVida implements Comparator<Personaje> {

    @Override
    public int compare(Personaje p1, Personaje p2) {
        return Integer.compare(p1.getVida(), p2.getVida());
    }
    
}
