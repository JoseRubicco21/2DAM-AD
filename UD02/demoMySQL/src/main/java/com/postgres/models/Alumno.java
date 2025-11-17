package com.postgres.models;

import com.postgres.annotations.Table;
import com.postgres.annotations.Column;

@Table(name = "alumnos")
public class Alumno {
    
    @Column(primaryKey = true, autoIncrement = true, name = "id", unique = true, type = "INT") // Add autoIncrement = true
    int id;

    @Column(primaryKey = false, name = "nombre", unique = false, type = "VARCHAR", length = 50)
    String nombre;
    
    @Column(primaryKey = false, name = "email", unique = true, type = "VARCHAR", length = 255)
    String email;

    @Column(primaryKey = false, name = "age", unique = false, type = "INT")
    int age;

    public Alumno(){
        
    }

    public Alumno(int id, String nombre, String email, int age) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.age = age;
    }

    public Alumno(String nombre, String email, int age) {
        this.nombre = nombre;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
