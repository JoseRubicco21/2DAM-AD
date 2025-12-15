package com.bosque.modelos;

public enum TipoMonstro {
    OGRO("Ogro"),
    TROLL("Troll"),
    ESPECTRO("Espectro");

    private String descripcion;

    private TipoMonstro(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}