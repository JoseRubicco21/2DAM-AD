package com.bosque;

import com.bosque.connection.DBConnection;
import com.bosque.modelos.Monstro;
import com.bosque.modelos.TipoMonstro;

public class Main {
    public static void main(String[] args) {
        DBConnection db = DBConnection.getInstance();
        Monstro monstro = new Monstro(10, "Pepe", TipoMonstro.TROLL, 300);

        db.execute(s -> {
            s.persist(monstro);
            return null;
        });
    }
}