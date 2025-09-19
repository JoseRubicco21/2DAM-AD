package ejercicio_3;

import java.io.File;

public class FicheroManejoBinario {
    
    // This is not the "main" that's supposed to be in java, but everything is going to be executed on the App.java as
    // an entrypoint
    public void main(){
        FicheroBinario origen = new FicheroBinario(new File("./src/ejercicio_3/bins/origen.bin"));
        FicheroBinario destino = new FicheroBinario(new File("./src/ejercicio_3/bins/destino.bin"));

        origen.escribir("ESTE ES EL TEXTO DE ORIGEN.");
        origen.leer();
        origen.copiar(destino);
        destino.leer();
    }
}
