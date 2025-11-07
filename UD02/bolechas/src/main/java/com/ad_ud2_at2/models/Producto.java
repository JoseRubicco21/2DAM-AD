package com.ad_ud2_at2.models;

import com.ad_ud2_at2.annotations.Column;
import com.ad_ud2_at2.annotations.Table;

/**
 * Entidad que representa un producto en el sistema.
 * 
 * Esta clase modela los productos disponibles en el catálogo que pueden
 * ser solicitados por los clientes a través de pedidos. Contiene información
 * básica del producto como nombre, descripción y precio.
 * 
 * Utiliza anotaciones personalizadas para mapear la clase con la tabla 'productos'
 * de la base de datos, definiendo estructura y restricciones de cada campo.
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see com.ad_ud2_at2.annotations.Table
 * @see com.ad_ud2_at2.annotations.Column
 * @see Pedido
 */
@Table(name = "productos")
public class Producto {

    /**
     * Identificador único del producto (clave primaria).
     * Se genera automáticamente por la base de datos mediante AUTO_INCREMENT.
     */
    @Column(name = "id", type = "INT", primaryKey = true, autoIncrement = true)
    private int id;

    /**
     * Nombre del producto.
     * Identificación principal del producto con máximo 100 caracteres.
     * Utilizado para búsquedas y visualización en el catálogo.
     */
    @Column(name = "nombre", type = "VARCHAR", length = 100)
    private String nombre;

    /**
     * Descripción detallada del producto.
     * Campo de texto largo para información descriptiva del producto,
     * características, especificaciones técnicas, etc.
     */
    @Column(name = "descripcion", type = "TEXT")
    private String descripcion;

    /**
     * Precio del producto en la moneda del sistema.
     * Valor decimal que representa el costo unitario del producto.
     * Debe ser un valor positivo para productos válidos.
     */
    @Column(name = "precio", type = "FLOAT")
    private float precio;

    /**
     * Constructor por defecto.
     * Crea una instancia de Producto sin inicializar los campos.
     * Necesario para frameworks ORM y serialización.
     */
    public Producto() {
    }

    /**
     * Constructor con parámetros para crear un producto completo.
     * El ID se asigna automáticamente por la base de datos.
     * 
     * @param nombre El nombre del producto. No debe ser null o vacío
     * @param descripcion La descripción del producto. Puede ser null
     * @param precio El precio del producto. Debe ser mayor que 0
     */
    public Producto(String nombre, String descripcion, float precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    /**
     * Obtiene el ID del producto.
     * 
     * @return El ID del producto, 0 si no se ha establecido aún
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del producto.
     * Normalmente no se usa directamente ya que el ID es auto-generado.
     * 
     * @param id El ID a establecer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return El nombre del producto, puede ser null si no se ha establecido
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * 
     * @param nombre El nombre a establecer. No debe ser null o vacío para productos válidos
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del producto.
     * 
     * @return La descripción del producto, puede ser null si no se ha establecido
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del producto.
     * 
     * @param descripcion La descripción a establecer. Puede ser null
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio del producto.
     * 
     * @return El precio del producto, 0.0f si no se ha establecido
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * 
     * @param precio El precio a establecer. Debe ser mayor que 0 para productos válidos
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * Representación en cadena del objeto Producto.
     * Incluye todos los campos para facilitar debugging y logging.
     * 
     * @return String con el formato "Producto{id=..., nombre='...', descripcion='...', precio=...}"
     */
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }

    /**
     * Compara dos objetos Producto para determinar si son iguales.
     * La igualdad se basa únicamente en el ID, ya que es la clave primaria.
     * 
     * @param obj El objeto a comparar con esta instancia
     * @return true si los objetos tienen el mismo ID, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto producto = (Producto) obj;
        return id == producto.id;
    }

    /**
     * Calcula el código hash del objeto Producto.
     * Basado en el ID para mantener consistencia con equals().
     * 
     * @return El código hash del ID
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
