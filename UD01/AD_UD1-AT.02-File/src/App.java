import java.io.FilenameFilter;

import ejercicio_1.Filtrar;
import ejercicio_1.FiltrarNombre;
import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        

        // Part 1 of the excercise.
        Filtrar filtrar = new Filtrar();
        System.out.println("filtrando con filtrar.fitlrar");
        filtrar.filtrar("./src/ejercicio_1", "java");

        // Part 2 of the excercise.
        File dir = new File("./src/ejercicio_1");
        String[] ficherosFiltrados = dir.list(new FiltrarNombre("Filtrar"));
        System.out.println("Filtrando con el filenameFilter");
        for(int i = 0; i < ficherosFiltrados.length; i++){
            System.out.println(ficherosFiltrados[i]);
        }
    }
}
