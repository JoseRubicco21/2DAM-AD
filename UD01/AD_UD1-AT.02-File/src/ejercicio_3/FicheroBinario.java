package ejercicio_3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FicheroBinario {
    
    private File fichero;

    public FicheroBinario(){

    }

    public FicheroBinario(File fichero) {
        this.fichero = fichero;
    }


    public File getFichero() {
        return fichero;
    }

    public void setFichero(File fichero) {
        this.fichero = fichero;
    }

    
    public void escribir(String texto){
        try (FileOutputStream fo = new FileOutputStream(this.fichero)){
            fo.write(texto.getBytes());;
            fo.flush();
        } catch (IOException e){
            System.out.println(e.getMessage());
        } 
    }
    
    public void leer(){
        try (FileInputStream fi = new FileInputStream(this.fichero)){
         int i = 0;
         while((i = fi.read())!= -1){
            System.out.print((char)i);
         }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void copiar(FicheroBinario ficheroDestino){
        try(FileOutputStream fo = new FileOutputStream(ficheroDestino.getFichero()); FileInputStream fi = new FileInputStream(this.fichero)){
            byte[] bytes = fi.readAllBytes();
            fo.write(bytes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
