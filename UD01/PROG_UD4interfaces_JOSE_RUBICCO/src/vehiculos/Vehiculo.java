package vehiculos;

public class Vehiculo {
    private String matricula;
    private int numeroDeRuedas;
    private int añoDeFabricacion;
    

    public Vehiculo() {
    }

    public Vehiculo(String matricula, int numeroDeRuedas, int añoDeFabricacion) {
        this.matricula = matricula;
        this.numeroDeRuedas = numeroDeRuedas;
        this.añoDeFabricacion = añoDeFabricacion;
    }

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public int getNumeroDeRuedas() {
        return numeroDeRuedas;
    }
    public void setNumeroDeRuedas(int numeroDeRuedas) {
        this.numeroDeRuedas = numeroDeRuedas;
    }
    public int getAñoDeFabricacion() {
        return añoDeFabricacion;
    }
    public void setAñoDeFabricacion(int añoDeFabricacion) {
        this.añoDeFabricacion = añoDeFabricacion;
    }

    @Override
    public String toString() {
        return "Vehiculo [matricula=" + matricula + ", numeroDeRuedas=" + numeroDeRuedas + ", añoDeFabricacion="
                + añoDeFabricacion + "]";
    }

    
}
