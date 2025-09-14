import vehiculos.Coche;
import vehiculos.Vehiculo;
import vehiculos.ComparaMatricula;
import java.util.Arrays;
import java.util.List;


public class App {
    public static void main(String[] args) throws Exception {
        Vehiculo[] flotaVehiculos = new Coche[3];
        flotaVehiculos[0] = new Coche("1234ABC", 4, 2020, 4);
        flotaVehiculos[1] = new Coche("5678DEF", 4, 2019, 4);
        flotaVehiculos[2] = new Coche("1234ZXF", 4, 2021, 4);   
    
    if (flotaVehiculos[0].equals(flotaVehiculos[2])) {
        System.out.println("El coche 0 y el coche 2 estan repetidos.");
    } else {
        System.out.println("Los vehículos son diferentes.");
    }

    // Based on the comparator implemented in Coche we derive that the result can be 0, positive or negative
    // Thus 0 means they are equal, negative means the first has less doors than the second
    // positive means the first has more doors than the second
    int resultado = ((Coche)flotaVehiculos[0]).compareTo((Coche)flotaVehiculos[1]);
    if (resultado < 0) {
        System.out.println("El coche 0 tiene menos puertas que el coche 1.");
    } else if (resultado > 0) {
        System.out.println("El coche 0 tiene más puertas que el coche 1.");
    } else {
        System.out.println("El coche 0 y el coche 1 tienen el mismo número de puertas.");
    }
    // Convert to a collection to use Comparators
    List<Vehiculo> listaVehiculos = Arrays.asList(flotaVehiculos);
    listaVehiculos.sort(new ComparaMatricula());
    listaVehiculos.forEach(v -> System.out.println(v.toString()));

    }
}
