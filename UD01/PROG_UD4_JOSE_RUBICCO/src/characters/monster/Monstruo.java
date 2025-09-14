package characters.monster;

import characters.Personaje;

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
}