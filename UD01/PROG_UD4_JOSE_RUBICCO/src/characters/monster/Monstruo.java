package characters.monster;

import characters.Personaje;
import java.util.Scanner;

public class Monstruo extends Personaje {
    
    private TipoDeMonstruo tipo;
    private int fuerza;

    public Monstruo() {
        super();
    }

    public Monstruo(String nombre, int vida, TipoDeMonstruo tipo, int fuerza) {
        super(nombre, vida);
        this.tipo = tipo;
        this.fuerza = fuerza;
    }

    @Override
    public void atacar(Personaje personaje) {
        System.out.println(this.getNombre() + " ataca con fuerza " + this.fuerza + "!");
        personaje.takeDamage(this.fuerza);
    }


    public TipoDeMonstruo getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeMonstruo tipo) {
        this.tipo = tipo;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public Monstruo creaMonstruo(Scanner sc){
        Monstruo m = new Monstruo();
        System.out.println("Ingrese el nombre del monstruo:");
        m.setNombre(sc.nextLine());
        System.out.println("Ingrese la vida del monstruo:");
        m.setVida(Integer.parseInt(sc.nextLine()));
        System.out.println("Ingrese el tipo de monstruo (OGRO, TROLL, ESPECTRO):");
        String tipo = sc.nextLine();
        m.setTipo(TipoDeMonstruo.valueOf(tipo));
        System.out.println("Ingrese la fuerza del monstruo:");
        m.setFuerza(Integer.parseInt(sc.nextLine()));
        return m;    
    }
}