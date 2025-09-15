import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        
        File f = new File("./src/test.txt");
        InfoFicheros info = new InfoFicheros(f);
        
        try{
            info.mostrarInformacion();
        } catch (FileNotFoundException e){
            System.err.println(e.getMessage());
        } catch (IOException e){
            System.err.println(e.getMessage());
        }


        ManejoFicheros controlador = new ManejoFicheros();
        try {
            System.out.printf("%n");
            System.out.println("Después de Crear");
            controlador.crearDirectorio("./src/subdir");
            controlador.crearFichero("./src/test2.txt");
            controlador.listarDirectorio("./src");
            System.out.printf("%n%n");
            System.out.println("Después de Borrar");
            controlador.borrarDirectorio("./src/subdir");
            controlador.borrarDirectorio("./src/test2.txt");
            controlador.listarDirectorio("./src");

        } catch (FileNotFoundException e){
            System.err.println(e.getMessage());
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
