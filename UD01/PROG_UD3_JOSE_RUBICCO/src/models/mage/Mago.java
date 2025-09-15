package models.mage;
import models.monster.Monstruo;

public class Mago {
    private String nombre;
    private int vida;
    private int nivelMagia;
    private Hechizo hechizoFavorito; 
    
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

    public int getNivelMagia() {
        return nivelMagia;
    }

    public void setNivelMagia(int nivelMagia) {
        this.nivelMagia = nivelMagia;
    }

    public Hechizo getHechizoFavorito() {
        return hechizoFavorito;
    }

    public void setHechizoFavorito(Hechizo hechizoFavorito) {
        this.hechizoFavorito = hechizoFavorito;
    }

    public void lanzarHechizo(Monstruo monster){
        monster.setVida(monster.getVida() - this.getNivelMagia());
    }

    public void aprenderHechizo(Hechizo newSpell){
        this.setHechizoFavorito(newSpell);
    }
}
