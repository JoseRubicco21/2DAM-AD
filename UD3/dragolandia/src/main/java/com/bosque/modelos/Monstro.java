package com.bosque.modelos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
@Entity(name="monstros")
@Table (name="monstros")

public class Monstro {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id; 

    @Column(name="nombre", nullable=false, length=100)
    private String nombre;

    @Column(name="vida", nullable=false)
    private int vida;

    @Column(name="tipo", nullable=false)
    @Enumerated(EnumType.STRING)
    private TipoMonstro tipo;

    @Column(name="fuerza", nullable=false)
    private int fuerza;

    @ManyToMany(mappedBy="monstros")
    private List<Bosque> bosques = new ArrayList<>();

    public Monstro(int fuerza, String nombre, TipoMonstro tipo, int vida) {
        this.fuerza = fuerza;
        this.nombre = nombre;
        this.tipo = tipo;
        this.vida = vida;
    }

    public void atacar(Mago mago) {
        mago.setVida(mago.getVida() - this.fuerza);
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

    public TipoMonstro getTipo() {
        return tipo;
    }

    public void setTipo(TipoMonstro tipo) {
        this.tipo = tipo;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + vida;
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        result = prime * result + fuerza;
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
        Monstro other = (Monstro) obj;
        if (id != other.id)
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (vida != other.vida)
            return false;
        if (tipo != other.tipo)
            return false;
        if (fuerza != other.fuerza)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Monstro [id=" + id + ", nombre=" + nombre + ", vida=" + vida + ", tipo=" + tipo + ", fuerza=" + fuerza
                + "]";
    }

    public Monstro() {
    }

    
    
}
