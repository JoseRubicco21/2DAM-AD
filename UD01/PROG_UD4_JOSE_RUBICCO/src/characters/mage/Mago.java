package characters.mage;
import java.util.Scanner;
import characters.Personaje;

public class Mago extends Personaje {

    private int nivelMagia;
    private Hechizo hechizoFavorito;
    
    public Mago() {
        super();
    }

    public Mago(String nombre, int vida, int nivelMagia, Hechizo hechizoFavorito) {
        super(nombre, vida);
        this.nivelMagia = nivelMagia;
        this.hechizoFavorito = hechizoFavorito;
    }

    @Override
    public void atacar(Personaje personaje) {
        System.out.println(this.getNombre() + " lanza un hechizo de " + this.hechizoFavorito + " causando " + this.nivelMagia + " de daño!");
        personaje.takeDamage(this.nivelMagia);
    }

    public void aprenderHechizo(Hechizo nuevoHechizo) {
        this.hechizoFavorito = nuevoHechizo;
    }

    public int getNivelMagia() {
        return nivelMagia;
    }

    public void setNivelMagia(int nivelMagia) {
        this.nivelMagia = nivelMagia;
    }

    public Hechizo getHechizoFavorito() {
        return hechizoFavorito;
    }

    public void setHechizoFavorito(Hechizo hechizoFavorito) {
        this.hechizoFavorito = hechizoFavorito;
    }

    public Mago createMago(Scanner sc){
        Mago m = new Mago();
        System.out.println("Ingrese el nombre del mago:");
        m.setNombre(sc.nextLine());
        System.out.println("Ingrese la vida del mago:");
        m.setVida(Integer.parseInt(sc.nextLine()));
        System.out.println("Ingrese el nivel de magia del mago:");
        m.setNivelMagia(Integer.parseInt(sc.nextLine()));
        System.out.println("Ingrese el hechizo favorito del mago (ADIVINACION, NECROMANCIA, PIROMANCIA, INVOCACION):");
        String hechizo = sc.nextLine();
        m.setHechizoFavorito(Hechizo.valueOf(hechizo));
        return m;    
        }
    }
