package com.bosque.modelos;

public enum TipoHechizo {
    BOLA_DE_FUEGO("Bola de Fuego"),
    RAYO_CONGELANTE("Rayo Congelante"),
    TORMENTA_ELECTRICA("Tormenta Eléctrica");

    private final String descripcion;

    TipoHechizo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
   
}