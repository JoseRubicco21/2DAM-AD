package com.ud2_at1.models;

import java.sql.Date;

public class Libro {
    String title;
    Autor author;
    Date date;
    String ISBN;

    public Libro(String title, Autor author, Date date, String ISBN) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.ISBN = ISBN;
    }
}
