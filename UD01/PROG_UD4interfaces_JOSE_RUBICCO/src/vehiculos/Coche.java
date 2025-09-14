package vehiculos;

public class Coche extends Vehiculo implements Comparable<Coche> {
    private int numeroDePuertas;

    public Coche() {
        super();
    }

    public Coche(String matricula, int numeroDeRuedas, int añoDeFabricacion, int numeroDePuertas) {
        super();
        setMatricula(matricula);
        setNumeroDeRuedas(numeroDeRuedas);
        setAñoDeFabricacion(añoDeFabricacion);
        this.numeroDePuertas = numeroDePuertas;
    }

    public int getNumeroDePuertas() {
        return numeroDePuertas;
    }

    public void setNumeroDePuertas(int numeroDePuertas) {
        this.numeroDePuertas = numeroDePuertas;
    }

    @Override
    public boolean equals(Object obj) {
        // Check if object is an actual coche
        if(obj instanceof Coche) {
            Coche otroCoche = (Coche) obj; // Cast to coche
            // Compare all attributes as requested.
            if(this.getMatricula().equals(otroCoche.getMatricula()) &&
               this.getNumeroDeRuedas() == otroCoche.getNumeroDeRuedas() &&
               this.getAñoDeFabricacion() == otroCoche.getAñoDeFabricacion() &&
               this.numeroDePuertas == otroCoche.numeroDePuertas) {
                return true;
            } else {
                return false;
            }
    }
        return false;
    }

    @Override
    public int compareTo(Coche otroCoche) {
        // Compare by numeroDePuertas
        return Integer.compare(this.numeroDePuertas, otroCoche.numeroDePuertas);
    }

    @Override
    public String toString() {
        return "Coche [numeroDePuertas=" + numeroDePuertas + super.toString() + "]";
    }

        

}
