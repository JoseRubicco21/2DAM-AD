import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.NotDirectoryException;

import ejercicio_1.Filtrar;
import ejercicio_1.FiltrarNombre;
import ejercicio_2.ClasificaDirectorios;
import ejercicio_3.FicheroManejoBinario;

import java.io.File;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {
        

        try {
            // Part 1 of the excercise.
            Filtrar filtrar = new Filtrar();
            System.out.println("filtrando con filtrar.fitlrar");
            filtrar.filtrar("./src/ejercicio_1", "java");
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        try {
            // Part 2 of the excercise.
            File dir = new File("./src/ejercicio_1");
            if(!dir.exists()) throw new FileNotFoundException("El archivo no existe.");
            if(dir.isFile()) throw new IOException("El archivo no es un directorio.");
            String[] ficherosFiltrados = dir.list(new FiltrarNombre("Filtrar"));
            System.out.println("Filtrando con el filenameFilter");
            for(int i = 0; i < ficherosFiltrados.length; i++){
                System.out.println(ficherosFiltrados[i]);
            } 
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    
    
        ClasificaDirectorios classifier = new ClasificaDirectorios();

        try {
           classifier.segunExtension("./src/unsorted");
        } catch (NotDirectoryException e){
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    
        FicheroManejoBinario manejadorBinario = new FicheroManejoBinario();
        manejadorBinario.main();
    }
}

