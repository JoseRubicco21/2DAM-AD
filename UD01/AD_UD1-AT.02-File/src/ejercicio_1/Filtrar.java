package ejercicio_1;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.io.File;

public class Filtrar {

    public void filtrar(String ruta, String extension){
        // Create the route
        File dir = new File(ruta);

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
