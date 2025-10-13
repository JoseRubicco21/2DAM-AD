package com.ad.json.models;

public class Proveedor {
    private String nombre;
    private String telefono;
    
    public Proveedor(){
        this.nombre = "n/a";
        this.telefono = "n/a";
    }

    @Override
    public String toString() {
        return "Proveedor [nombre=" + nombre + ", telefono=" + telefono + "]";
    }
}