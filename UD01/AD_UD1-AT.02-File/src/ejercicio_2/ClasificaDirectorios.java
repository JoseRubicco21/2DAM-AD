package ejercicio_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;

public class ClasificaDirectorios {
    
    public ClasificaDirectorios(){

    }

    public void segunExtension(String ruta) throws NotDirectoryException, FileNotFoundException{
        File dir = new File(ruta);
        if(!dir.exists()) throw new FileNotFoundException("La ruta especificada no contiene ningún directorio ni archivo.");
        if(!dir.isDirectory()) throw new NotDirectoryException("La ruta no es un directorio");

        
    }
}
