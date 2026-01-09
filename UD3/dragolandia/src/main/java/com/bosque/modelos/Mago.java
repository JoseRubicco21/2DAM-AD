package com.bosque.modelos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity(name="magos")
@Table (name="magos")
public class Mago {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="nombre", nullable=false, length=100)
    private String nombre;

    @Column(name="vida", nullable=false)
    private int vida;

    @Column(name="nivel_magia", nullable=false)
    private int nivelMagia;

    @ManyToMany
    @JoinTable(
        name = "magos_hechizos",
        joinColumns = @JoinColumn(name = "mago_id"),
        inverseJoinColumns = @JoinColumn(name = "hechizo_id")
    )
    private List<Hechizo> conjuros;

    public Mago(List<Hechizo> conjuros, int id, int nivelMagia, String nombre, int vida) {
        this.conjuros = conjuros;
        this.id = id;
        this.nivelMagia = nivelMagia;
        this.nombre = nombre;
        this.vida = vida;
    }

    public Mago(List<Hechizo> conjuros, int nivelMagia, String nombre, int vida) {
        this.conjuros = conjuros;
        this.nivelMagia = nivelMagia;
        this.nombre = nombre;
        this.vida = vida;
    }
    

    public void lanzarHechizo(Hechizo hechizo) {

    }
    
    public void lanzarHechizo(Hechizo hechizo, Monstro objetivo) {
        // This is not a good pattern and it is not good architecture but i do not care anymore.
        switch (hechizo.getId()) {
            case 1:
                // Bola de fuego
                objetivo.setVida(objetivo.getVida() - this.nivelMagia);
                break;
            case 2:
                // rayo
                objetivo.setVida(1);
                break;
            case 3:
                // Bola de nieve
                objetivo.setVida(0);
                break;
            default:
                this.setVida(this.getVida() - 1);
                break;
        }
    }

    public void lanzarHechizo(Monstro monster){
        monster.setVida(monster.getVida() - this.nivelMagia);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

    public int getNivelMagia() {
        return nivelMagia;
    }

    public void setNivelMagia(int nivelMagia) {
        this.nivelMagia = nivelMagia;
    }

    public List<Hechizo> getConjuros() {
        return conjuros;
    }

    public void setConjuros(List<Hechizo> conjuros) {
        this.conjuros = conjuros;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + vida;
        result = prime * result + nivelMagia;
        result = prime * result + ((conjuros == null) ? 0 : conjuros.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Mago other = (Mago) obj;
        if (id != other.id)
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (vida != other.vida)
            return false;
        if (nivelMagia != other.nivelMagia)
            return false;
        if (conjuros == null) {
            if (other.conjuros != null)
                return false;
        } else if (!conjuros.equals(other.conjuros))
            return false;
        return true;
    }


    public Mago() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mago{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", vida=").append(vida);
        sb.append(", nivelMagia=").append(nivelMagia);
        sb.append(", conjuros=").append(conjuros);
        sb.append('}');
        return sb.toString();
    }

    
}
