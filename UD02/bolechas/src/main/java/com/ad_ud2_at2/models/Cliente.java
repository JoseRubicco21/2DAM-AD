package com.ad_ud2_at2.models;

import com.ad_ud2_at2.annotations.Column;
import com.ad_ud2_at2.annotations.Table;

/**
 * Entidad que representa un cliente en el sistema.
 * 
 * Esta clase modela a los clientes que pueden realizar pedidos en el sistema.
 * Utiliza anotaciones personalizadas para mapear la clase con la tabla 'clientes'
 * de la base de datos.
 * 
 * La clave primaria es el DNI del cliente, lo que implica que cada cliente
 * debe tener un DNI único en el sistema.
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see com.ad_ud2_at2.annotations.Table
 * @see com.ad_ud2_at2.annotations.Column
 */
@Table(name = "clientes")
public class Cliente {

    /**
     * DNI del cliente, actúa como clave primaria.
     * Debe ser único en el sistema y tener máximo 9 caracteres.
     */
    @Column(name = "dni", type = "VARCHAR", length = 9, primaryKey = true)
    private String dni;

    /**
     * Nombre completo del cliente.
     * Puede contener hasta 100 caracteres.
     */
    @Column(name = "nombre", type = "VARCHAR", length = 100)
    private String nombre;

    /**
     * Constructor por defecto.
     * Crea una instancia de Cliente sin inicializar los campos.
     * Necesario para frameworks ORM y serialización.
     */
    public Cliente() {
    }

    /**
     * Constructor con parámetros para crear un cliente completo.
     * 
     * @param dni El DNI del cliente (clave primaria). No debe ser null
     * @param nombre El nombre completo del cliente. Puede ser null
     */
    public Cliente(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    /**
     * Obtiene el DNI del cliente.
     * 
     * @return El DNI del cliente, puede ser null si no se ha establecido
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del cliente.
     * 
     * @param dni El DNI a establecer. Debe ser único en el sistema
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el nombre del cliente.
     * 
     * @return El nombre del cliente, puede ser null si no se ha establecido
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     * 
     * @param nombre El nombre a establecer. Puede ser null
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Representación en cadena del objeto Cliente.
     * Útil para logging y debugging.
     * 
     * @return String con el formato "Cliente{dni='...', nombre='...'}"
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    /**
     * Compara dos objetos Cliente para determinar si son iguales.
     * La igualdad se basa únicamente en el DNI, ya que es la clave primaria.
     * 
     * @param obj El objeto a comparar con esta instancia
     * @return true si los objetos tienen el mismo DNI, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return dni != null ? dni.equals(cliente.dni) : cliente.dni == null;
    }

    /**
     * Calcula el código hash del objeto Cliente.
     * Basado en el DNI para mantener consistencia con equals().
     * 
     * @return El código hash del DNI, o 0 si el DNI es null
     */
    @Override
    public int hashCode() {
        return dni != null ? dni.hashCode() : 0;
    }
}
