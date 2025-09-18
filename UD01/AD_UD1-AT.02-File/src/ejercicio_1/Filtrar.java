package ejercicio_1;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Filtrar {

    public void filtrar(String ruta, String extension) throws IOException, FileNotFoundException{
        // Create the route
        File dir = new File(ruta);
        if(!dir.exists()) throw new FileNotFoundException("Fichero no encontrado");
        if(dir.isFile()) throw new IOException("El fichero es un archivo, no un directorio");
        // get all the named files in the directory
        ArrayList<File> files = new ArrayList<>(List.of(dir.listFiles()));
        // Create a predicate to use as a function for filtering.
        Predicate<File> DoesNotEndWithExtension = file -> !file.getName().endsWith(extension);
        // Remove if matches with predicate.
        files.removeIf(DoesNotEndWithExtension);
        // Print every name in the files collection with a method reference.
        files.forEach(System.out::println);
    }
}
