package com.bosque.modelos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name="bosques")
@Table (name="bosques")
public class Bosque {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="nombre", nullable=false, length=100)
    private String nombre;

    @ManyToOne
    private Monstro monstroJefe;

    @OneToOne(mappedBy="bosque")
    private Dragon dragon;

    @ManyToMany
    @JoinTable(name="bosques_monstros",
    joinColumns= @JoinColumn(name="bosque_id"),
    inverseJoinColumns= @JoinColumn(name="monstro_id")
    )
    private List<Monstro> monstros;


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
    public Monstro getMonstroJefe() {
        return monstroJefe;
    }
    public void setMonstroJefe(Monstro monstroJefe) {
        this.monstroJefe = monstroJefe;
    }
    @Override
    public String toString() {
        return "Bosque [id=" + id + ", nombre=" + nombre + ", monstroJefe=" + monstroJefe + "]";
    }
    public Bosque(int id, String nombre, Monstro monstroJefe) {
        this.id = id;
        this.nombre = nombre;
        this.monstroJefe = monstroJefe;
    }
    public Bosque() {
    }
    
    public Bosque(String nombre, Monstro monstroJefe) {
        this.nombre = nombre;
        this.monstroJefe = monstroJefe;
    }

    public Bosque(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }
    public Dragon getDragon() {
        return dragon;
    }
    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }
    public List<Monstro> getMonstros() {
        return monstros;
    }
    public void setMonstros(List<Monstro> monstros) {
        this.monstros = monstros;
    }

    
    
}
