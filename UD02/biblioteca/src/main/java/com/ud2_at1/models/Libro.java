package com.ud2_at1.models;

import java.sql.Date;

import com.ud2_at1.annotations.Column;
import com.ud2_at1.annotations.Table;

@Table(name = "libros")
public class Libro { 

    @Column(name = "id", type = "INT", primaryKey = true, autoIncrement = true)
    int id; 

    @Column(name = "title", type = "VARCHAR", length = 100)
    String title;

    @Column(name = "author_id", type = "INT", foreignKey = true, references = "autores(id)")
    Autor author;
    
    @Column(name = "publication_date", type = "DATE")
    Date date;

    @Column(name = "isbn", type = "VARCHAR", length = 13, unique = true)
    String ISBN;

    public Libro(String title, Autor author, Date date, String ISBN) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.ISBN = ISBN;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Autor getAuthor() {
        return author;
    }

    public void setAuthor(Autor author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    
}
