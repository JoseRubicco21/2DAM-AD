package com.ud2_at1.models.generic;

import com.ud2_at1.controllers.crud.CRUD;

public class Database implements CRUD {
    // This is needed as we might have one or more database in our program.
    public String name;

    public Database(String name){
        this.name = name;
    }
}
