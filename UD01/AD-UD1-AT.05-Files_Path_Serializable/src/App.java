import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import ejercicio_2.Persona;
import ejercicio_2.PersonaController;

import java.nio.file.Files;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
       Path dir = Paths.get("src",  "ejercicio_1", "dir");
       if(!Files.exists(dir)) Files.createDirectory(dir);
       
       Path origen = Paths.get("src",  "ejercicio_1", "dir", "origen.txt");
       if(!Files.exists(origen)) Files.createFile(origen);

       List<String> lines = new ArrayList<>(List.of("Primera linea\n", "Segunda linea\n", "Tercera linea\n"));

       lines.forEach(l -> {
        try{
            Files.writeString(origen, l, StandardOpenOption.APPEND);
        } catch (IOException e){
            System.err.println(e.getMessage());
        }});

        try {
            CopiaFichero cp = new CopiaFichero(origen);
            cp.copiar();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }

        PersonaController pc = new PersonaController();

        Persona p1 = new Persona("bobby", 20, 1);
        Persona p2 = new Persona("juan", 40, 3);
        Persona p3 = new Persona("pepe", 30, 24);

        pc.guardarPersonas(p1,p2,p3);
        ArrayList<Persona> ps = pc.leerPersonas(pc.getPersonaFile());
        ps.forEach(System.out::println);
    }
}
