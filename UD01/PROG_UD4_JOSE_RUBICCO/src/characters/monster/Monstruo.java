package characters.monster;

import characters.Personaje;

public class Monstruo extends Personaje {
    
    private TipoDeMonstruo tipo;

    public Monstruo() {
        super();
    }

    public Monstruo(String nombre, int vida, TipoDeMonstruo tipo) {
        super(nombre, vida);
        this.tipo = tipo;
    }

    @Override
    public void atacar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atacar'");
    }
}