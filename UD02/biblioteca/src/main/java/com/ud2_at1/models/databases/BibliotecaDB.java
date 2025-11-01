package com.ud2_at1.models.databases;

import com.ud2_at1.models.generic.Database;
import com.ud2_at1.models.generic.DatabaseUser;

public class BibliotecaDB extends Database {

    public BibliotecaDB(String name, String charset, String collationType, DatabaseUser owner) {
        super(name, charset, collationType, owner);
    }
    
}
