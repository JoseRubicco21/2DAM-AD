package com.ad_ud2_at2.models;

import java.sql.Date;

import com.ad_ud2_at2.annotations.Column;
import com.ad_ud2_at2.annotations.Table;

@Table(name = "pedidos")
public class Pedido {

    @Column(name = "id", type = "INT", primaryKey = true, autoIncrement = true)
    private int id;

    @Column(name = "fecha", type = "DATE")
    private Date fecha;

    @Column(name = "cantidad", type = "INT")
    private int cantidad;

    @Column(name = "producto_id", type = "INT", foreignKey = true, references = "productos(id)")
    private Producto producto;

    @Column(name = "cliente_dni", type = "VARCHAR", length = 9, foreignKey = true, references = "clientes(dni)")
    private Cliente cliente;

    public Pedido() {
    }

    public Pedido(Date fecha, int cantidad, Producto producto, Cliente cliente) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.producto = producto;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", cantidad=" + cantidad +
                ", producto=" + (producto != null ? producto.getNombre() : "null") +
                ", cliente=" + (cliente != null ? cliente.getNombre() : "null") +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pedido pedido = (Pedido) obj;
        return id == pedido.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
