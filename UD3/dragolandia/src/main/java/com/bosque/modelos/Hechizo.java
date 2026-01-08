package com.bosque.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name="hechizos")
@Table (name="hechizos")
public class Hechizo {
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="nombre", nullable=false)
    @Enumerated(EnumType.STRING)
    private TipoHechizo nombre;

    @Column(name="descripcion", nullable=true, length=250)
    private String descripcion;
    
    @Column(name="danio", nullable=false)
    private int danio;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public TipoHechizo getNombre() {
        return nombre;
    }
    public void setNombre(TipoHechizo nombre) {
        this.nombre = nombre;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
        Hechizo other = (Hechizo) obj;
        if (id != other.id)
            return false;
        if (nombre != other.nombre)
            return false;
        return true;
    }
    public Hechizo(TipoHechizo nombre, String descripcion, int danio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.danio = danio;
    }
    public Hechizo(int id, TipoHechizo nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public Hechizo() {
    }
    @Override
    public String toString() {
        return "Hechizo [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", danio=" + danio + "]";
    }
    
    
}
