package ejercicio_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClasificaDirectorios {
    
    public ClasificaDirectorios(){

    }

    private String getExtension(File f){
        String name = f.getName();
        int lastDot = name.lastIndexOf('.');
        if (lastDot > 0 && lastDot < name.length() - 1) {
            return name.substring(lastDot + 1);
        }
        return "no_extension"; // For files without extension
    }


    public void segunExtension(String ruta) throws NotDirectoryException, FileNotFoundException{
        File dir = new File(ruta);
        if(!dir.exists()) throw new FileNotFoundException("La ruta especificada no contiene ningún directorio ni archivo.");
        if(!dir.isDirectory()) throw new NotDirectoryException("La ruta no es un directorio");

        // Create a list of files
        ArrayList<File> allFiles = new ArrayList<>(List.of(dir.listFiles()));
        
        // Filter to get only files (not directories)
        ArrayList<File> files = allFiles.stream()
                .filter(f -> f.isFile()) // Only keep files, exclude directories
                .collect(Collectors.toCollection(ArrayList::new));

        // Get the extensions for all file presents and format them in such a way that ends with only the extensions.
        ArrayList<String> fileExtensions = files.stream() // Make the array of files as Stream
                                        .map(file -> this.getExtension(file).toUpperCase()) // Get extension and uppercase it
                                        .distinct() // Get everything that is distinct
                                        .collect(Collectors.toCollection(ArrayList::new)); // Re-create an arrayList via collecting the stream.
        // For each extension create a new folder in the route + specified extension. 
        fileExtensions.forEach(extension -> {
             File subdir = new File(ruta + "/" + extension);
             subdir.mkdir();
            });

        files.forEach((file) -> file.renameTo(new File(ruta + "/" + this.getExtension(file).toUpperCase() + "/" + file.getName())));
        
    }
}
