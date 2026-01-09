package com.bosque.modelos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name="dragones")
@Table (name="dragones")
public class Dragon {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="nombre", nullable=false, length=100)
    private String nombre;

    @Column(name="intensidad_fuego", nullable=false)
    private int intensidadFuego;

    @Column(name="resistencia", nullable=false)
    private int resistencia;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="bosque_id", referencedColumnName="id")
    private Bosque bosque;

    public Dragon(int intensidadFuego, String nombre, int resistencia) {
        this.intensidadFuego = intensidadFuego;
        this.nombre = nombre;
        this.resistencia = resistencia;
    }

    public void exahalarFuego(Monstro monstro) {
        monstro.setVida(monstro.getVida() - this.intensidadFuego);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIntensidadFuego() {
        return intensidadFuego;
    }

    public void setIntensidadFuego(int intensidadFuego) {
        this.intensidadFuego = intensidadFuego;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + intensidadFuego;
        result = prime * result + resistencia;
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
        Dragon other = (Dragon) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (intensidadFuego != other.intensidadFuego)
            return false;
        if (resistencia != other.resistencia)
            return false;
        return true;
    }

    public Dragon(String nombre, int intensidadFuego, int resistencia) {
        this.nombre = nombre;
        this.intensidadFuego = intensidadFuego;
        this.resistencia = resistencia;
    }

    public Dragon() {
    }

    @Override
    public String toString() {
        return "Dragon [id=" + id + ", nombre=" + nombre + ", intensidadFuego=" + intensidadFuego + ", resistencia="
                + resistencia + ", bosque=" + bosque + "]";
    }

    
    
}
