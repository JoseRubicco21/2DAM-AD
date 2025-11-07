package com.ad_ud2_at2.models;

import java.sql.Date;

import com.ad_ud2_at2.annotations.Column;
import com.ad_ud2_at2.annotations.Table;

/**
 * Entidad que representa un pedido en el sistema.
 * 
 * Esta clase modela los pedidos realizados por clientes, estableciendo
 * relaciones con las entidades Cliente y Producto. Cada pedido contiene
 * información sobre la fecha, cantidad solicitada y las entidades relacionadas.
 * 
 * Utiliza anotaciones personalizadas para mapear la clase con la tabla 'pedidos'
 * de la base de datos, incluyendo relaciones de clave foránea con las tablas
 * 'clientes' y 'productos'.
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see com.ad_ud2_at2.annotations.Table
 * @see com.ad_ud2_at2.annotations.Column
 * @see Cliente
 * @see Producto
 */
@Table(name = "pedidos")
public class Pedido {

    /**
     * Identificador único del pedido (clave primaria).
     * Se genera automáticamente por la base de datos mediante AUTO_INCREMENT.
     */
    @Column(name = "id", type = "INT", primaryKey = true, autoIncrement = true)
    private int id;

    /**
     * Fecha en que se realizó el pedido.
     * Almacena solo la fecha sin información de hora.
     */
    @Column(name = "fecha", type = "DATE")
    private Date fecha;

    /**
     * Cantidad de productos solicitados en el pedido.
     * Debe ser un valor positivo mayor que cero.
     */
    @Column(name = "cantidad", type = "INT")
    private int cantidad;

    /**
     * Producto asociado al pedido (relación muchos a uno).
     * Establece una clave foránea hacia la tabla productos.
     */
    @Column(name = "producto_id", type = "INT", foreignKey = true, references = "productos(id)")
    private Producto producto;

    /**
     * Cliente que realizó el pedido (relación muchos a uno).
     * Establece una clave foránea hacia la tabla clientes.
     */
    @Column(name = "cliente_dni", type = "VARCHAR", length = 9, foreignKey = true, references = "clientes(dni)")
    private Cliente cliente;

    /**
     * Constructor por defecto.
     * Crea una instancia de Pedido sin inicializar los campos.
     * Necesario para frameworks ORM y serialización.
     */
    public Pedido() {
    }

    /**
     * Constructor con parámetros para crear un pedido completo.
     * El ID se asigna automáticamente por la base de datos.
     * 
     * @param fecha La fecha del pedido. No debe ser null
     * @param cantidad La cantidad de productos solicitados. Debe ser mayor que 0
     * @param producto El producto asociado al pedido. No debe ser null
     * @param cliente El cliente que realiza el pedido. No debe ser null
     */
    public Pedido(Date fecha, int cantidad, Producto producto, Cliente cliente) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.producto = producto;
        this.cliente = cliente;
    }

    /**
     * Obtiene el ID del pedido.
     * 
     * @return El ID del pedido, 0 si no se ha establecido aún
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del pedido.
     * Normalmente no se usa directamente ya que el ID es auto-generado.
     * 
     * @param id El ID a establecer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha del pedido.
     * 
     * @return La fecha del pedido, puede ser null si no se ha establecido
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del pedido.
     * 
     * @param fecha La fecha a establecer. No debe ser null para pedidos válidos
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la cantidad de productos en el pedido.
     * 
     * @return La cantidad solicitada, 0 si no se ha establecido
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de productos en el pedido.
     * 
     * @param cantidad La cantidad a establecer. Debe ser mayor que 0 para pedidos válidos
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el producto asociado al pedido.
     * 
     * @return El producto del pedido, puede ser null si no se ha establecido
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto asociado al pedido.
     * 
     * @param producto El producto a establecer. No debe ser null para pedidos válidos
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Obtiene el cliente que realizó el pedido.
     * 
     * @return El cliente del pedido, puede ser null si no se ha establecido
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Establece el cliente que realizó el pedido.
     * 
     * @param cliente El cliente a establecer. No debe ser null para pedidos válidos
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Representación en cadena del objeto Pedido.
     * Incluye información básica del pedido y los nombres de las entidades relacionadas
     * para evitar referencias circulares en el toString.
     * 
     * @return String con el formato "Pedido{id=..., fecha=..., cantidad=..., producto='...', cliente='...'}"
     */
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

    /**
     * Compara dos objetos Pedido para determinar si son iguales.
     * La igualdad se basa únicamente en el ID, ya que es la clave primaria.
     * 
     * @param obj El objeto a comparar con esta instancia
     * @return true si los objetos tienen el mismo ID, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pedido pedido = (Pedido) obj;
        return id == pedido.id;
    }

    /**
     * Calcula el código hash del objeto Pedido.
     * Basado en el ID para mantener consistencia con equals().
     * 
     * @return El código hash del ID
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
