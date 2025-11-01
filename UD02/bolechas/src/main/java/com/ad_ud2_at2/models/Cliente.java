package com.ad_ud2_at2.models;

import com.ad_ud2_at2.annotations.Column;
import com.ad_ud2_at2.annotations.Table;

@Table(name = "clientes")
public class Cliente {

    @Column(name = "dni", type = "VARCHAR", length = 9, primaryKey = true)
    private String dni;

    @Column(name = "nombre", type = "VARCHAR", length = 100)
    private String nombre;

    public Cliente() {
    }

    public Cliente(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return dni != null ? dni.equals(cliente.dni) : cliente.dni == null;
    }

    @Override
    public int hashCode() {
        return dni != null ? dni.hashCode() : 0;
    }
}
