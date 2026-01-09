package com.bosque;

import java.util.List;

import com.bosque.controlador.BosqueDAO;
import com.bosque.controlador.DragonDAO;
import com.bosque.controlador.HechizoDAO;
import com.bosque.controlador.MagoDAO;
import com.bosque.controlador.MonstroDAO;
import com.bosque.modelos.Bosque;
import com.bosque.modelos.Dragon;
import com.bosque.modelos.Hechizo;
import com.bosque.modelos.Mago;
import com.bosque.modelos.Monstro;
import com.bosque.modelos.TipoHechizo;
import com.bosque.modelos.TipoMonstro;

public class Main {

    public static void init() {
        Monstro m1 = new Monstro(50, "Gorgo", TipoMonstro.OGRO, 10);
        Monstro m2 = new Monstro(30, "Trolly", TipoMonstro.TROLL, 15);
        Monstro m3 = new Monstro(20, "Fantasmin", TipoMonstro.ESPECTRO, 10);
        Bosque b1 = new Bosque("Bosque Tenebroso", m3);
        Dragon d1 = new Dragon(40, "Draco", 300);
        Hechizo h1 = new Hechizo(TipoHechizo.BOLA_DE_FUEGO, "Una bola de fuego", 1000);
        Hechizo h2 = new Hechizo(TipoHechizo.RAYO_CONGELANTE, "Un rayo congelante", 1000);
        Hechizo h3 = new Hechizo(TipoHechizo.TORMENTA_ELECTRICA, "Una tormenta electrica", 1000);
        Mago mago1 = new Mago(List.of(h1, h2),     75, "Gandalf", 250);
        Mago mago2 = new Mago(List.of(h2, h3), 50, "Merlin", 200);

        // Initialize DAOs and save entities on init.
        DragonDAO dragonDAO = new DragonDAO();
        BosqueDAO bosqueDAO = new BosqueDAO();
        HechizoDAO hechizoDAO = new HechizoDAO();
        MagoDAO magoDAO = new MagoDAO();
        MonstroDAO monstroDAO = new MonstroDAO();

        monstroDAO.save(m1);
        monstroDAO.save(m2);
        monstroDAO.save(m3);
        dragonDAO.save(d1);
        bosqueDAO.save(b1);
        hechizoDAO.save(h1);
        hechizoDAO.save(h2);
        hechizoDAO.save(h3);
        magoDAO.save(mago1);
        magoDAO.save(mago2);


    }

    public static boolean gameState(List<Mago> magos, List<Monstro> monstros) {
        boolean magosVivos = magos.stream().anyMatch(m -> m.getVida() > 0);
        boolean monstrosVivos = monstros.stream().anyMatch(m -> m.getVida() > 0);
        return magosVivos && monstrosVivos;
    }


    public static void main(String[] args) {
        // Populate database with base values
        init();
       // Initialize DAOs and save entities on init.
        DragonDAO dragonDAO = new DragonDAO();
        BosqueDAO bosqueDAO = new BosqueDAO();
        HechizoDAO hechizoDAO = new HechizoDAO();
        MagoDAO magoDAO = new MagoDAO();
        MonstroDAO monstroDAO = new MonstroDAO();
        
        List<Mago> magos = magoDAO.getAll();
        List<Monstro> monstros = monstroDAO.getAll();
        Dragon dragon = dragonDAO.getById(1);


        // Game loop is not well defined. I'm not willing to design a full game here.
        while(gameState(magos, monstros)){
            magos.get(0).lanzarHechizo(hechizoDAO.getById(1), monstros.get(0));
            monstros.get(1).atacar(magos.get(0));
            dragon.exahalarFuego(monstros.get(1));
            dragon.exahalarFuego(monstros.get(2));
            
            magoDAO.update(magos.get(0));
            monstroDAO.update(monstros.get(0));
            monstroDAO.update(monstros.get(1));
            monstroDAO.update(monstros.get(2));
            dragonDAO.update(dragon);
            
            magos.forEach(m -> System.out.println(m));
            monstros.forEach(System.out::println);
            System.out.println(dragon);
        }


    }
}