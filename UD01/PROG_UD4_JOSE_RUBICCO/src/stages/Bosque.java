package stages;
import characters.monster.Monstruo;

public class Bosque {
    
    private String nombre;
    private int nivelPeligro;
    private Monstruo monstruoJefe;

    public Bosque(String nombre, int nivelPeligro, Monstruo monstruoJefe) {
        this.nombre = nombre;
        this.nivelPeligro = nivelPeligro;
        this.monstruoJefe = monstruoJefe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivelPeligro() {
        return nivelPeligro;
    }

    public void setNivelPeligro(int nivelPeligro) {
        this.nivelPeligro = nivelPeligro;
    }

    public Monstruo getMonstruoJefe() {
        return monstruoJefe;
    }

    public void setMonstruoJefe(Monstruo monstruoJefe) {
        this.monstruoJefe = monstruoJefe;
    }

    public void mostrarJefe(){
        System.out.println("El jefe del bosque " + this.nombre + " es " + this.monstruoJefe.getNombre() + " con vida " + this.monstruoJefe.getVida() + ", tipo " + this.monstruoJefe.getTipo() + " y fuerza " + this.monstruoJefe.getFuerza() + ".");
    }   

    public void cambiarJefe(Monstruo nuevoJefe){
        this.setMonstruoJefe(nuevoJefe);
    }

}
