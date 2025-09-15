import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;


public class ManejoFicheros {
    

    
    public void crearFichero(String fichero) throws IOException{
        File f = new File(fichero);
        if(f.exists()) throw new IOException("Error creando el fichero");
        f.createNewFile();
    }

    public void borrarFichero(String fichero) throws IOException, FileNotFoundException{
        File f = new File(fichero);
        if(!f.exists()) throw new FileNotFoundException("Error intentando borrar el archivo " + f.getName() + ", no existe.");
        f.delete();
    }
    
    public void crearDirectorio(String ruta) throws IOException, FileAlreadyExistsException{
        File d = new File(ruta);
        if(d.isDirectory()) throw new IOException();
        d.mkdir();
    }

    public void borrarDirectorio(String ruta) throws IOException{
        File d = new File(ruta);
        if(!d.exists()) throw new IOException("Error intentando borrar el directorio. No existe.");
        d.delete();
    }

    public void listarDirectorio(String ruta) throws IOException {
        File d = new File(ruta);
        if(!d.exists()) throw new FileNotFoundException("El diretorio especificado no existe.");
        if(!d.isDirectory()) throw new IOException("La ruta especificada no es un directorio");
        
        // Time to do magic yay

        Arrays.asList(d.listFiles()).forEach((file) -> {System.out.printf("Archivo: %s, Tipo:%s%n", file.getName(), file.isDirectory() ? "Directorio" : "Archivo");});
        
    }
}
