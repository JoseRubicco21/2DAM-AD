import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class InfoFicheros {
    
    private File fichero;

    public InfoFicheros(File fichero) {
        this.fichero = fichero;
    }

    public File getFichero() {
        return fichero;
    }

    public void setFichero(File fichero) {
        this.fichero = fichero;
    }

    public void mostrarInformacion() throws IOException, FileNotFoundException{
        if (!this.fichero.exists()) 
            throw new FileNotFoundException("El fichero con la ruta especificada no existe");
        
            System.out.printf("Nombre: %s%n", fichero.getName());
            System.out.printf("Ruta relativa: %s%n", fichero.getPath());
            System.out.printf("Ruta absoluta: %s%n", fichero.getAbsolutePath());
            System.out.printf("Ejecución: %b%nEscritura: %b%nLectura:%b %n",fichero.canExecute(), fichero.canRead(), fichero.canWrite());
            System.out.printf("Espacio total: %s%n",  fichero.getTotalSpace());
            System.out.printf("Es directorio: %s%n", fichero.isDirectory());
            System.out.printf("Es archivo: %s%n", fichero.isFile());
        }
    }

