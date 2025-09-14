package characters;

public abstract class Personaje {
    
    protected String nombre;
    protected int vida;

    public Personaje(String nombre, int vida) {
        this.nombre = nombre;
        this.vida = vida;
    }

    public Personaje() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = (vida < 0) ? 0 : vida;
    }

    public abstract void atacar();

    public void takeDamage(int damage){
        this.setVida(this.getVida() - damage);
    }
}
