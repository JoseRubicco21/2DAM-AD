
package models.monster;
import models.mage.Mago;

public class Monstruo {
    private String nombre;
    private int vida;
    private TipoMonstruo tipo;    
    private int fuerza;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
        if(this.vida < 0) this.vida = 0;
    }

    public TipoMonstruo getTipo() {
        return tipo;
    }

    public void setTipo(TipoMonstruo tipo) {
        this.tipo = tipo;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public void atacar(Mago mage){
        mage.setVida(mage.getVida() - this.getFuerza());
    }

    public void recibirDaño(int damage){
        this.setVida(this.getVida() - damage);
    }

    @Override
    public String toString() {
        return "Monstruo [nombre=" + nombre + ", vida=" + vida + ", tipo=" + tipo + ", fuerza=" + fuerza + "]";
    }

    
}
