import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;

public class CopiaFichero {
    private Path file;

    public CopiaFichero(Path file){
        this.file = file;
    }   

    public void copiar() throws IOException{
        Path copy = file.resolveSibling("copia.txt");
        Path backup = Paths.get(file.getParent().getParent().toString(), "backup");
        Path bakcupFile = Paths.get(backup.toString(), "copia.txt");
        Files.copy(file, copy, StandardCopyOption.REPLACE_EXISTING);
        if(!Files.exists(backup)) Files.createDirectories(backup);
        Files.move(copy, bakcupFile, StandardCopyOption.ATOMIC_MOVE);
    }
}
