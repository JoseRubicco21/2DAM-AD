package com.ad.json.models;

public class Proveedor {
    private String nombre;
    private String telefono;

    public Proveedor() {
        this.nombre = "n/a";
        this.telefono = "n/a";
    }

    public Proveedor(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Proveedor [nombre=" + nombre + ", telefono=" + telefono + "]";
    }
}