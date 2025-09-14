package characters;

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
    public void atacar() {
        System.out.println(this.getNombre() + " lanza un hechizo!");
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

    
}
