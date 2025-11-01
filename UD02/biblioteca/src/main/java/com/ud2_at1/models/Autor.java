package com.ud2_at1.models;

import com.ud2_at1.annotations.Column;
import com.ud2_at1.annotations.Table;

@Table(name = "autores")
public class Autor {

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "id", type = "INT", primaryKey = true, autoIncrement = true)
    int id;

    @Column(name = "first_name", type = "VARCHAR", length = 100)
    String firstName;

    @Column(name = "last_name", type = "VARCHAR", length = 100)
    String lastName;

    public Autor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
