package com.ad_ud2_at2.models;

import com.ad_ud2_at2.annotations.Column;
import com.ad_ud2_at2.annotations.Table;

@Table(name = "productos")
public class Producto {

    @Column(name = "id", type = "INT", primaryKey = true, autoIncrement = true)
    private int id;

    @Column(name = "nombre", type = "VARCHAR", length = 100)
    private String nombre;

    @Column(name = "descripcion", type = "TEXT")
    private String descripcion;

    @Column(name = "precio", type = "FLOAT")
    private float precio;

    public Producto() {
    }

    public Producto(String nombre, String descripcion, float precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto producto = (Producto) obj;
        return id == producto.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
