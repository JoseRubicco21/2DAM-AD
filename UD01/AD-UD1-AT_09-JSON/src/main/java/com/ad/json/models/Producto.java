package com.ad.json.models;

public class Producto {
    private String nombre;
    private double precio;
    private boolean disponible;
    private String[] categorias;
    private int stock;
    private String descripcion;
    private Proveedor proveedor;

    // Availability should be calculated but we also want to give the option for something to not be available
    // yet be in stock. Such as getting non-released products.
    public Producto(String nombre, double precio, boolean disponible, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
        this.stock = stock;
        this.categorias = new String[]{"n/a","n/a","n/a"};
        this.descripcion = "Sin descripción"; 
        this.proveedor = new Proveedor();

    }

    public Producto(String nombre, double precio, boolean disponible, String[] categorias, int stock,
            String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
        this.categorias = categorias;
        this.stock = stock;
        this.descripcion = descripcion;
        this.proveedor = new Proveedor();
    }


    public Producto(String nombre, double precio, boolean disponible, String[] categorias, int stock,
            String descripcion, Proveedor proveedor) {
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
        this.categorias = categorias;
        this.stock = stock;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String[] getCategorias() {
        return categorias;
    }

    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }

    public int getStock() {
        return stock;
    }

    // Make it so if stock is 0, set disponiblity to false.
    public void setStock(int stock) {
        this.stock = stock;
        if(stock == 0) this.setDisponible(false);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    

        
}
