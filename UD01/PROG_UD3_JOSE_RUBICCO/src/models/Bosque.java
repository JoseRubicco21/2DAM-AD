package models;

import models.monster.Monstruo;

public class Bosque {
    private String nombre;
    private int nivelPeligro;
    private Monstruo monstruoJefe;
    
    public void mostarJefe(){
        System.out.println(this.monstruoJefe);
    }

    public void cambiarJefe(Monstruo newBoss){
        this.setMonstruoJefe(newBoss);
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

    
}
