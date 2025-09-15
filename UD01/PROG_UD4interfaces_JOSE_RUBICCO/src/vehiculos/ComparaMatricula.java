package vehiculos;
import java.util.Comparator;


public class ComparaMatricula implements Comparator<Vehiculo> {
    @Override
    public int compare(Vehiculo vehiculo1, Vehiculo vehiculo2) {
        return vehiculo1.getMatricula().compareTo(vehiculo2.getMatricula());
    }
    
}